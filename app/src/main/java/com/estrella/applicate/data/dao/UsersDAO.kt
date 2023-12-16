package com.estrella.applicate.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.estrella.applicate.data.entities.Users

@Dao
interface UsersDAO {

    @Query("SElECT * FROM Users")
    fun getAllUsers() : List<Users>

    @Query("SElECT * FROM Users WHERE userId = :userId ")
    fun getOneUser(userId: Int): Users

    @Insert
    fun insertUser(users: List<Users>)  // Inserta 1 o varios usuarios.

    @Update
     fun uddateUsers(users: List<Users>) //
}