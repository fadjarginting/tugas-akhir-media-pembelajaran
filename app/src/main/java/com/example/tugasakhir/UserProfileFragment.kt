package com.example.tugasakhir

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button

class UserProfileFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_user_profile, container, false)

        val btn_back_p: Button = view.findViewById(R.id.btn_back_p)
        btn_back_p.setOnClickListener {
            // Pindah ke laman kedua saat tombol diklik
            val intent = Intent(requireActivity(), MainActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
