package uz.gita.gitaimtihon6samandar.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroupEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    var name:String,
    var tartibi:Int,
    var userId:Int
)