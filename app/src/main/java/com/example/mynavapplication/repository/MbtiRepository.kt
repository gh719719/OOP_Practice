package com.example.mynavapplication.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database

// Firebase data 연결
class MbtiRepository {
    val database = Firebase.database
    val userRef = database.getReference("user")

    fun observeMbti(mbti: MutableLiveData<String>) {
        // value가 바뀔 때마다
        userRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                mbti.postValue(snapshot.value.toString())    // 리포지토리에서는 이렇게 쓴다.(postValue) UI thread에도 실행 안되니까 백그라운드에서 실행되게끔
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    fun postMbti(newValue: String) {
        userRef.setValue(newValue)
    }
}