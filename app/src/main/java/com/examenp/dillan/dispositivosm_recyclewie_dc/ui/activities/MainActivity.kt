package com.examenp.dillan.dispositivosm_recyclewie_dc.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.dillan.dispositivosm_recyclewie_dc.R
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.topanime.TopAnimes

import com.examenp.dillan.dispositivosm_recyclewie_dc.databinding.ActivityMainBinding
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase.JikanGetTopAnimesUserCaseNuevo
import com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters.UsersAdapter
import com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {


    private var userList: MutableList<FullInfoAnimeLG> = ArrayList()

    private lateinit var binding: ActivityMainBinding

    //Me creo otro adaptador para el diffUtil
    private var userDiffAdapter =
        UsersAdapterDiffUtil({ deleteUserDiffUtil(it) }, { selectAnime(it) })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()

    }

    fun initRecycleView() {
        binding.rvUsers.adapter = userDiffAdapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )
        loadDataRecycleView()
    }

    //Carga de Informacion Recycle View
    private fun loadDataRecycleView(){
        //TODO("Llamar a la API de Jikan")
        lifecycleScope.launch(Dispatchers.Main) {
            binding.progresBar.visibility = View.VISIBLE
            //Hago el llamado intermedio entre dos estados, el llamado a mi logica a la base de datos
            //se hace dentro de una IO
            val respuesta = withContext(Dispatchers.IO) {
                JikanGetTopAnimesUserCaseNuevo().invoke()
            }
            respuesta.onSuccess { listAnime ->
                userList.addAll(listAnime)
                //Cuando inicio el Recycle view es cuando debo poner la insercion
                insertUsersDiffUtil(userList)
            }
            respuesta.onFailure { exeption ->
                Snackbar.make(
                    this@MainActivity,
                    binding.rvUsers,
                    exeption.message.toString(),
                    Snackbar.LENGTH_LONG
                ).show()

            }
            binding.progresBar.visibility = View.GONE
        }

    }

    private fun insertUsersDiffUtil(animes: List<FullInfoAnimeLG>) {
        userList.addAll(animes)
        userDiffAdapter.submitList(userList.toList())
    }

    private fun deleteUserDiffUtil(position: Int) {
        userList.removeAt(position)
        userDiffAdapter.submitList(userList.toList())

    }

    //Me muestra el usuario seleccionado
    private fun selectAnime(animes: FullInfoAnimeLG) {
        Snackbar.make(
            this,
            binding.rvUsers,
            animes.name, Snackbar.LENGTH_LONG
        )
            .show()
        //Si esta info quiero mandar en otra activity debo usarl los Intents

//        val i= Intent(this, llegada)//Nececito un punto de partida y uno de llegada "Un activity"
//        i.putExtra("Usuario id", user.id)
//        startActivity(i)
    }
}