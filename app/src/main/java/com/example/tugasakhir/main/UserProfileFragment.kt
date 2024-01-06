package com.example.tugasakhir.main

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.tugasakhir.R
import com.example.tugasakhir.login.LoginActivity
import com.example.tugasakhir.model.UserLogin
import com.google.gson.Gson

class UserProfileFragment : Fragment() {

    private lateinit var tvNama: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvRole: TextView
    private lateinit var btnLogout: Button
    private lateinit var tvTimer: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_user_profile, container, false)

        tvNama = view.findViewById(R.id.tvp_nama)
        tvEmail = view.findViewById(R.id.tvp_email)
        tvRole = view.findViewById(R.id.tvp_role)
        btnLogout = view.findViewById(R.id.btnLogout)
        tvTimer = view.findViewById(R.id.tvp_waktu)

        getData()
        btnMain()
        setTimer()
        return view
    }

    private fun setTimer() {

    }

    private fun btnMain() {
        btnLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            activity?.finish()
        }
    }
    private fun getData() {
        val data = activity?.intent?.getStringExtra("extra")
        val dataUser = Gson().fromJson<UserLogin>(data, UserLogin::class.java)

        tvNama.text = dataUser.name
        tvEmail.text = dataUser.email
        tvRole.text = dataUser.role
    }
}
