package com.example.mynavapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mynavapplication.repository.MbtiRepository

const val UNCHECKED_MBTI = "ISTP"

// data를 기본적으로 가지고 있다.
class MbtiViewModel: ViewModel() {
    // 외부에서 참고할 때는 함부로 바꿀 수 없게끔 세팅, 내부적으로는 바꿀 수 있지만 밖에 공개할 때는 바꿀 수 없는 형태로 일종의 패턴이다.
    private val _mbti = MutableLiveData<String>(UNCHECKED_MBTI)
    val mbti: LiveData<String> get() = _mbti

    // Firebase의 database를 뷰모델과 연결
    private val repository = MbtiRepository()
    init {
        repository.observeMbti(_mbti)
    }

    private fun modifyMbti(index: Int, newValue: Char){
        val newMbti = _mbti.value?.let {
            val chArr = it.toCharArray()
            chArr[index] = newValue
            String(chArr)
        } ?: UNCHECKED_MBTI

        // 바뀐걸 데이터베이스에 업데이트
        repository.postMbti(newMbti)
    }

    // MBTI의 value를 보다가 E이면 true, I면 false로 변경
    val isE get() = _mbti.value?.get(0) == 'E'     // 라이브데이터는 .value를 해야 안의 데이터를 본다.
    val isN get() = _mbti.value?.get(1) == 'N'
    val isF get() = _mbti.value?.get(2) == 'F'
    val isJ get() = _mbti.value?.get(3) == 'J'

    fun setE(newValue: Boolean) {
        modifyMbti(0, if(newValue) 'E' else 'I')
    }
    fun setN(newValue: Boolean) {
        modifyMbti(1, if(newValue) 'N' else 'S')
    }
    fun setF(newValue: Boolean) {
        modifyMbti(2, if(newValue) 'F' else 'T')
    }
    fun setJ(newValue: Boolean) {
        modifyMbti(3, if(newValue) 'J' else 'P')
    }
}