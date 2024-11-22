package com.example.mynavapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mynavapplication.databinding.FragmentEntryBinding

class EntryFragment : Fragment() {
    var binding: FragmentEntryBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEntryBinding.inflate(inflater)
        // Inflate the layout for this fragment
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // aboutFragment로 이동하는 버튼
        // findNavController은 여기에다가 해야 안전함
        binding?.btnAbout?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_aboutFragment)
        }
        binding?.btnExamine?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_examineFragment)
        }
        binding?.btnSettings?.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_settingsFragment)
        }
    }

    // 메모리 누수 방지
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}