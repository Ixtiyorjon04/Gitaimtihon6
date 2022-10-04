package uz.gita.gitaimtihon6samandar.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity

@Dao
interface GroupDao {
    @Query("select * from GroupEntity where userId=:id order by tartibi")
    fun getAll(id:Int):List<GroupEntity>
    @Insert
    fun insert(entity: GroupEntity)

    @Delete
    fun delete(entity: GroupEntity)

    @Update

    fun update(entity: GroupEntity)
}