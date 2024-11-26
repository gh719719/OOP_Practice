package com.example.mynavapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mynavapplication.databinding.FragmentExamineBinding
import com.example.mynavapplication.viewmodel.MbtiViewModel

class ExamineFragment : Fragment() {

    val viewModel: MbtiViewModel by activityViewModels()
    var binding: FragmentExamineBinding? = null

    // Examine Fragment의 lifecycle이 시작되면 1번으로 호출
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentExamineBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    fun examineMBTI(): String {
        binding?.let {
            val ieStr = if(it.chkE.isChecked) "E" else "I"
            val snStr = if(it.chkN.isChecked) "N" else "S"
            val tfStr = if(it.chkF.isChecked) "F" else "T"
            val jpStr = if(it.chkJ.isChecked) "J" else "P"

            return ieStr + snStr + tfStr + jpStr
        }
        return ""
    }

    // 2번째로 호출
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 뷰모델의 mbti를 계속 보고 있을 것이다.
        // Livedata의 값이 변경될 때마다 UI 업데이트
        viewModel.mbti.observe(viewLifecycleOwner){
            // data를 분석하는 것은 뷰모델에 넣는 것이 더 좋다.
            binding?.chkE?.isChecked = viewModel.isE
            binding?.chkN?.isChecked = viewModel.isN
            binding?.chkF?.isChecked = viewModel.isF
            binding?.chkJ?.isChecked = viewModel.isJ
        }

        binding?.chkE?.setOnClickListener {
            // 사용자가 chkE버튼을 누르면 뷰모델의 setE함수 호출
            // 뷰모델의 setE함수 동작 파라미터 true, modifyMbti함수 동작 파라미터 0, E
            // ISTP에서 ESTP로 변경
            viewModel.setE(binding?.chkE?.isChecked ?: false)
        }
        binding?.chkN?.setOnClickListener {
            viewModel.setN(binding?.chkN?.isChecked ?: false)
        }
        binding?.chkF?.setOnClickListener {
            viewModel.setF(binding?.chkF?.isChecked ?: false)
        }
        binding?.chkJ?.setOnClickListener {
            viewModel.setJ(binding?.chkJ?.isChecked ?: false)
        }


        binding?.btnResult?.setOnClickListener {
            val result = examineMBTI()
            val bundle = bundleOf("MBTI" to result)
            findNavController().navigate(R.id.action_examineFragment_to_resultFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}