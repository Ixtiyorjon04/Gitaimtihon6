package uz.gita.gitaimtihon6samandar.data.room.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity

@Dao
interface UsersDao {
    @Query("select * from UserEntity")
    fun getAll():List<UserEntity>

    @Insert
    fun insert(entity: UserEntity)

    @Delete
    fun delete(entity: UserEntity)

    @Update

    fun update(entity: UserEntity)

}