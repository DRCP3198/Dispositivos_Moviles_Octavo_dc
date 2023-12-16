package com.estrella.applicate.logic.usercases

import android.content.Context
import com.estrella.applicate.data.entities.Users
import com.estrella.applicate.data.repository.DBConnection
import com.estrella.applicate.data.repository.DBRepository
import com.estrella.applicate.data.repository.UserRepository

class LoginUserCase(val connection: DBRepository) {

    fun checkLogin(user: String, password: String): Int {
        var ret = -1
        val users = UserRepository().getUserList()

        val lstUsers = users.filter {
            it.userName == user && it.password == password
        }

        if (lstUsers.isNotEmpty()) {
            ret = lstUsers.first().userId
        }

        /* LOGS
        Log.d(Constants.TAG, lstUsers.toString())
        Log.d(Constants.TAG, lstUsers.first().userId.toString())*/

        return ret
    }

    suspend fun getUserName1(userId: Int): Users =
        connection.getUsersDAO().getOneUser(userId)


   suspend fun getUserName(userId: Int): Users =
        UserRepository().getUserList().first {
            it.userId == userId
        }

   suspend fun insertUsers() =
        if (connection.getUsersDAO().getAllUsers().isEmpty()){
            connection.getUsersDAO().insertUser(
            UserRepository().getUserList())
        }else{

        }
    suspend fun getAllUsers():List<Users> =
         connection.getUsersDAO().getAllUsers()
    }
















// 0. Opción usando Filter{it}

/*  // 1. Opción usando Contains

    private var baseDatos = UserRepository()  //Instancia de UserRepository.

    fun checkLogin(user: String, password: String): Boolean {
        val listaUsuarios = baseDatos.getUserList();
        return listaUsuarios.contains(Users(user, password))
    }*/

/*  2. Opción con forEach{it}
fun checkLogin(user: String, password: String): Boolean {
    val listaUsuarios = baseDatos.getUserList();

    /*forEach {it }
    * Ese "it" significa que es el elemento actual de la iteración*//*
    listaUsuarios.forEach {
        if (it.userName == user && it.password == password) {
            return true
        }
    }
    return false
}*/*/

/*  3. Opción Comun de Verificación.
    private var baseDatos = UserRepository()  //Instancia de UserRepository.

    fun checkLogin(user: String, password: String): Boolean {
        for (usuario in baseDatos.getUserList()) {
            if (usuario.userName == user && usuario.password == password) {
                return true
            }
        }
        return false
    }*/