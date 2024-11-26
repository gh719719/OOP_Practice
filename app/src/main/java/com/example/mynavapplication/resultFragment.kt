package com.example.mynavapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentResultOwner
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.mynavapplication.databinding.FragmentResultBinding
import com.example.mynavapplication.viewmodel.MbtiViewModel

class resultFragment : Fragment() {
    // viewModel을 사용하는 2가지 방법

    // viewModels()를 사용할 때: viewModel의 lifecycle이 자신을 소유하고 있는 fragment의 lifecycle에 종속될 때
    // val viewModel: MbtiViewModel by viewModels() -> viewModel을 초기화할 viewModels()에 위임, viewModels() 함수가 viewModel을 적절히 초기화
    val viewModel: MbtiViewModel by activityViewModels()    // activity에 물려있는 뷰모델, activiy lifecycle과 동일

    var binding: FragmentResultBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.mbti.observe(viewLifecycleOwner) { // 뷰모델 안에 있는 mbti라는 라이브 데이터를 observe(볼) 거야 이 fragment의 viewLifecycle에 해당하는 만큼
            binding?.txtResult?.text = viewModel.mbti.value // 뷰모델의 mbti의 실제값을 저 text에 넣을거야
        }
        binding?.btnReexamine?.setOnClickListener {
            findNavController().navigate(R.id.action_resultFragment_to_examineFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}