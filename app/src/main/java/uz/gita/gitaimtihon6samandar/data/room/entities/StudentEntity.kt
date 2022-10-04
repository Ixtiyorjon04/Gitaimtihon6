package uz.gita.gitaimtihon6samandar.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var name:String,
    var surname:String,
    var groupId:Int,
    var tartibi:Int
)
