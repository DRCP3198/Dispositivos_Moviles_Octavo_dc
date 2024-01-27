package com.examenp.dillan.dispositivosm_recyclewie_dc.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.examenp.dillan.dispositivosm_recyclewie_dc.R
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.entities.Users
import com.examenp.dillan.dispositivosm_recyclewie_dc.databinding.ActivityMainBinding
import com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters.UsersAdapter
import com.examenp.dillan.dispositivosm_recyclewie_dc.ui.adapters.UsersAdapterDiffUtil
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {


    private var userList: MutableList<Users> = ArrayList<Users>()

    //Para que se note los camnios agrego una variable count
    private var count: Int = 1
    private lateinit var binding: ActivityMainBinding
    private var usersAdapter = UsersAdapter({deleteUser(it)},{selectUser(it)})
    //Me creo otro adaptador para el diffUtil
    private var userDiffAdapter= UsersAdapterDiffUtil({deleteUserDiffUtil(it)},{selectUser(it)})

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecycleView()
        initListeners()
    }

    fun initRecycleView() {
        binding.rvUsers.adapter =userDiffAdapter
        binding.rvUsers.layoutManager =
            LinearLayoutManager(
                this,
                LinearLayoutManager.VERTICAL,
                false
            )

    }

    fun initListeners() {
        binding.btnInsert.setOnClickListener {
            val usuarios = Users(
                1,
                "Bayron $count",
                "Su profe",
                "https://static.vecteezy.com/system/resources/previews/022/149/526/non_2x/closeup-of-male-teacher-with-beard-glasses-books-and-apple-vector.jpg"
            )
            count++
            //insertUsers(usuarios)
            insertUsersDiffUtil(usuarios)
        }

    }

    private fun insertUsersDiffUtil(usuarios: Users) {
        userList.add(usuarios)
        userDiffAdapter.submitList(userList.toList())


    }
    private fun insertUsers(usuarios: Users) {
        userList.add(usuarios)
        usersAdapter.listUsers = userList
        usersAdapter.notifyItemInserted(userList.size)
    }

    private fun deleteUser(position: Int){
        userList.removeAt(position)
        usersAdapter.listUsers=userList
        usersAdapter.notifyItemRemoved(position)

    }

    private fun deleteUserDiffUtil(position: Int){
        userList.removeAt(position)
        userDiffAdapter.submitList(userList.toList())

    }

    //Me muestra el usuario seleccionado
    private fun selectUser(user: Users){
        Snackbar.make(this,
            binding.btnInsert,
            user.name, Snackbar.LENGTH_LONG)
            .show()
        //Si esta info quiero mandar en otra activity debo usarl los Intents

//        val i= Intent(this, llegada)//Nececito un punto de partida y uno de llegada "Un activity"
//        i.putExtra("Usuario id", user.id)
//        startActivity(i)
    }
}