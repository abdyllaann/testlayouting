package com.smkkth.tes_layouting.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smkkth.tes_layouting.R
import com.smkkth.tes_layouting.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnyt.setOnClickListener {
            val urlyt = "https://www.youtube.com"
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(urlyt)
                startActivity(it)
            }
        }
        binding.btnwa.setOnClickListener {
            val urlwa = "https://wa.me/6289648453133"
            Intent(Intent.ACTION_VIEW).also {
                it.data = Uri.parse(urlwa)
                startActivity(it)
            }
        }

        return view
    }

}