package com.estrella.applicate.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.creative.ipfyandroid.Ipfy
import com.creative.ipfyandroid.IpfyClass
import com.estrella.applicate.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding   // OJO: Si estoy en la clase LOGIN, es LOGINActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Colocar la IP actual
        Ipfy.init(this)
        Ipfy.init(this, IpfyClass.IPv4)
        Ipfy.init(this, IpfyClass.UniversalIP)
        ipAddress()
    }

    private fun ipAddress() {
        Ipfy.getInstance().getPublicIpObserver()
            .observe(this, { ipData -> binding.txtIp.text = ipData.currentIpAddress })
    }

}
