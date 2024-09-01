package com.smkkth.tes_layouting.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smkkth.tes_layouting.R
import com.smkkth.tes_layouting.databinding.FragmentInputBinding

class FragmentInput : Fragment() {

    private lateinit var binding: FragmentInputBinding
    var sharePref: SharedPreferences? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInputBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnsave.setOnClickListener {
            val dataPlayer1 = binding.player1.text.toString()
            val dataPlayer2 = binding.player2.text.toString()

            if (dataPlayer1.isEmpty() || dataPlayer2.isEmpty()) {
                Toast.makeText(requireContext(), "Mohon mengisi semua nama player", Toast.LENGTH_SHORT).show()
            } else {
                sharePref = activity?.getSharedPreferences("player", Context.MODE_PRIVATE)
                val editor = sharePref?.edit()
                editor?.putString("KeyPlayer1", dataPlayer1)
                editor?.putString("KeyPlayer2", dataPlayer2)
                editor?.apply()
                parentFragmentManager.beginTransaction().replace(R.id.Fragment_Container, FragmentCount()).commit()
            }

        }


    }


}