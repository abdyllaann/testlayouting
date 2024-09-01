package com.smkkth.tes_layouting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.smkkth.tes_layouting.databinding.ActivityMainBinding
import com.smkkth.tes_layouting.fragment.FragmentCount
import com.smkkth.tes_layouting.fragment.FragmentInput
import com.smkkth.tes_layouting.fragment.ProfileFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        goToFrag(FragmentCount())

        binding.btnFrag2.setOnClickListener {
            goToFrag(FragmentCount())
        }
        binding.btnFrag3.setOnClickListener {
            goToFrag(ProfileFragment())
        }


    }

    private fun goToFrag(fragment: Fragment){
        fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.Fragment_Container, fragment).commit()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Peringatan")
        builder.setMessage("Ingin Keluar aplikasi?")
        builder.setPositiveButton("Ya"){
            dialog, which ->
            dialog.dismiss()
            finish()
        }
        builder.setNegativeButton("Tidak"){
            dialog, which ->dialog.dismiss()
        }
        val dialog = builder.create()
        dialog.show()
    }
}