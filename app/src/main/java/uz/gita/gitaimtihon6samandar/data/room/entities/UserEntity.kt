package uz.gita.gitaimtihon6samandar.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    val name:String,
    val password:String
    )