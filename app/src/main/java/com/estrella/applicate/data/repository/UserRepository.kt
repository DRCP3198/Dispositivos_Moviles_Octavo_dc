package com.estrella.applicate.data.repository

import com.estrella.applicate.data.entities.Users

class UserRepository {

    fun getUserList(): List<Users> {
        /* NOTA:
           Puedo crear una instancia de Usuario, con cualquier constructor que tenga la clase,
           y luego añadir información de otras variables que no estaban en el constructor.*/
        val miUsuario = Users("Roberto", "1234", "admin")
        miUsuario.lastName = "Manrique"
        miUsuario.userId = 22


        return listOf<Users>(
            Users("admin", "admin", 1,),
            Users("j", "j", 2, "Juan Carlos", "Estrella"),
            Users("maria", "maria", 3),
            miUsuario,
            Users("jose", "1234", 20,"jose", "ramirez")
        )
    }
}