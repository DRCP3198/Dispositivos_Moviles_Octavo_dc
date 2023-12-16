package com.estrella.applicate.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    // Constructor primario (CP)
    val userName: String? = null,
    val password: String? = null,
) {

    @PrimaryKey(autoGenerate = true)
    var userId: Int = -1

    var firstName: String? = "Usuario No registrado"
    var lastName: String? = "Usuario No registrado"
    var profile: String? = ""

    /* CONSTRUCTORES Sobrecargados:
     En el paréntesis del this() van los elementos que tiene el constructor primario, y en llaves
     los elementos que tiene el constructor como parámetro y que no están en el C. Primario. */
    constructor(userName: String?, password: String?, userId: Int) : this(
        userName,
        password
    ) {
        this.userId = userId
    }

    constructor(userName: String?, password: String?, profile: String) : this(
        userName,
        password
    ) {
        this.profile = profile
    }

    constructor(userName: String?, password: String?, userId: Int, firstName: String, lastName: String) : this(
        userName,
        password
    ) {
        this.userId = userId
        this.firstName = firstName
        this.lastName = lastName
    }
}


