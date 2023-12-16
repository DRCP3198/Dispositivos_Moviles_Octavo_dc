package com.estrella.applicate.core

import android.app.Application
import com.estrella.applicate.data.repository.DBConnection
import com.estrella.applicate.data.repository.DBRepository
import com.estrella.applicate.logic.usercases.LoginUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// Clase donde voy a tener el control de la aplicación, en general, no solo de un activity
class Applicate : Application() {

    override fun onCreate() {
        super.onCreate()
        con = DBConnection().getConnection(applicationContext)
        //Necesito de un ambiente
        //necesito de la funcion launch
        GlobalScope.launch(Dispatchers.IO){


            LoginUserCase(con).insertUsers()
        }

    }

    // Companion Object -> atributos y método estáticos en java.
    companion object{
        private lateinit var con : DBRepository

        fun getConnectionDB() : DBRepository?{
            return  con
        }
    }

}