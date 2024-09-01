package com.smkkth.tes_layouting.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.smkkth.tes_layouting.R
import com.smkkth.tes_layouting.databinding.FragmentCountBinding


class FragmentCount : Fragment() {
    var sharePref: SharedPreferences? = null
    var player1skor = 0
    var player2skor = 0
    private lateinit var binding: FragmentCountBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentCountBinding.inflate(inflater, container, false)
        val view = binding.root



        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.player1.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.Fragment_Container, FragmentInput()).commit()
        }
        binding.player2.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.Fragment_Container, FragmentInput()).commit()
        }
        binding.btnreset.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Peringatan!!")
            builder.setMessage("Ingin mereset data?")
            builder.setPositiveButton("Nama dan Skor"){
                dialog, which->
                val editor = sharePref?.edit()
                editor?.remove("KeyPlayer1")
                editor?.remove("KeyPlayer2")
                editor?.apply()
                onResume()
                player1skor = 0
                binding.count1.text = player1skor.toString()
                player2skor = 0
                binding.count2.text = player2skor.toString()
            }
            builder.setNegativeButton("Skor"){
                dialog, which ->
                player1skor = 0
                binding.count1.text = player1skor.toString()
                player2skor = 0
                binding.count2.text = player2skor.toString()
            }
            builder.setNeutralButton("Batal"){
                dialog, which ->
                dialog.dismiss()
            }
            builder.show()

        }


    }

    override fun onResume() {
        super.onResume()
        sharePref = activity?.getSharedPreferences("player", Context.MODE_PRIVATE)
        val nama1 = sharePref?.getString("KeyPlayer1", "Player 1")
        val nama2 = sharePref?.getString("KeyPlayer2", "Player 2")

        binding.player1.text = nama1
        binding.player2.text = nama2


        if (player1skor == 21 || player2skor == 21){
            val builder = AlertDialog.Builder(requireContext())
            builder.setTitle("Pertandingan selesai")
            builder.setMessage("Ingin memulai ulang dari awal?")
            builder.setPositiveButton("ya"){
                    dialog, which ->
                dialog.dismiss()
            }
        } else{
            binding.count1.setOnClickListener {
                player1skor++
                binding.count1.text = player1skor.toString()
            }
            binding.count2.setOnClickListener {
                player2skor++
                binding.count2.text = player2skor.toString()

            }
        }

        }
}