package com.estrella.applicate.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.estrella.applicate.core.Applicate
import com.estrella.applicate.databinding.LoginActivityBinding
import com.estrella.applicate.ui.core.Constants
import com.estrella.applicate.logic.usercases.LoginUserCase
import com.google.android.material.snackbar.Snackbar


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: LoginActivityBinding   // OJO: Si estoy en la clase LOGIN, es LOGINActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)               // Recupera una instancia anterior.

        // BINDING (Bind = unir)
        // Inflar = convertir de texto (XML) a objeto.(a partir de esto ya se puede usar el Listener, etc.)
        binding =
            LoginActivityBinding.inflate(layoutInflater) // "layoutInflater" Inflador por defecto de la clase.
        setContentView(binding.root)

    }

    override fun onStart() {
        super.onStart()
        initListeners()
    }

    fun initListeners() {
        binding.btnLogin.setOnClickListener {

            val check =
                LoginUserCase(Applicate.getConnectionDB()!!).checkLogin(  //**************************************************************************
                    binding.editTextUsuario.text.toString(),
                    binding.editTxtContrasenia.text.toString()
                )

            if (check > 0) {
                var intent =
                    Intent(this, PrincipalActivity::class.java)  // me voy a principalActivity
                intent.putExtra(
                    Constants.USER_ID,
                    check
                )   // agregar datos adicionales (pares clave-valor) que puedo pasar de una actividad a otra.

                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.btnLogin,
                    "USUARIO o PASSWORD INCORRECTO",
                    Snackbar.LENGTH_SHORT
                ).show()
            }

        }

    }


    override fun onDestroy() {
        super.onDestroy()
    }

}