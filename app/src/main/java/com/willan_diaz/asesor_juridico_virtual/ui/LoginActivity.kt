package com.willan_diaz.asesor_juridico_virtual.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.willan_diaz.asesor_juridico_virtual.R
import com.willan_diaz.asesor_juridico_virtual.databinding.ActivityLoginBinding
import com.willan_diaz.asesor_juridico_virtual.ui.core.Constants

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding  //Enlace entre variable y layout [}
    // en la variable binding está TODO el layout
    //Ser inyecta por propiedad hay por constructor y por propiedad las inyecciones de dependecias
    private val singIn:SingIn= SingIn()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  //recupera una instancia anterior.

        //BINDING
        binding = ActivityLoginBinding.inflate(layoutInflater) // Inflador por defecto de la clase. Inflar = convertir de texto (XML) a objeto.(a partir de esto ya se puede usar el Listener, etc.)
        setContentView(binding.root)

        var editTextUsuario = findViewById<EditText>(R.id.editTextUsuario1)
        var btnLogin = findViewById<Button>(R.id.btnLogin)
    }


    override fun onStart() {
        super.onStart()
        initControls()


    }

    //DATA CLASS
    fun initControls(){
        binding.btnLogin.setOnClickListener {
            var usuario = binding.editTextUsuario1.text.toString()
            var contra = binding.txtContrasena.text.toString()

            singIn.checkUserAndPassword(usuario,contra)

            if(!singIn.checkUserAndPassword(usuario,contra))
                Snackbar.make(
                    binding.btnLogin,
                    "Usuario o contraseña incorrecta",
                    Snackbar.LENGTH_LONG).show()
            else{

                val intentImplicito=Intent()
                intentImplicito.action= Intent.ACTION_SEND
                //Es la forma de enviar valores por el intent
                intentImplicito.putExtra(Intent.EXTRA_TEXT,
                    "Mi primera chamba")
                intentImplicito.type="text/plain"
                startActivity(intentImplicito)

                //envio de donde me voy y hacia donde voy a llegar
                val intentExplicito=Intent(
                    this, MainActivity::class.java)
                intentExplicito.putExtra(Constants.TEXT_VARIABLE,"Mi segunda chamba")
                startActivity(intentExplicito)
            }
        }
    }

    //VERSION ANTERIOR Control de contraseña
    /*if (u == "dillan" && c == "1234" ){
*//*
                Toast.makeText(this, "USUARIO VALIDO", Toast.LENGTH_LONG).show()
*//*
                Snackbar.make(binding.btnLogin, "USUARIO VALIDO", Snackbar.LENGTH_SHORT).show()
            }else{
*//*
                Toast.makeText(this, "USUARIO INVALIDO!!", Toast.LENGTH_LONG).show()
*//*
                Snackbar.make(binding.btnLogin, "USUARIO INVALIDO!!!", Snackbar.LENGTH_SHORT).show()

            }*/

    override fun onDestroy() {
        super.onDestroy()
    }
}