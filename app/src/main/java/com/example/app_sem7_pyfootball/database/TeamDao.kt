package com.example.app_sem7_pyfootball.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.app_sem7_pyfootball.models.Team

@Dao
interface TeamDao {
    @Query("SELECT * FROM teams")
    fun getAll(): List<Team>

    @Insert
    fun insert(vararg teams: Team)

    @Delete
    fun delete(vararg teams: Team)

    @Update
    fun update(vararg teams: Team)
}