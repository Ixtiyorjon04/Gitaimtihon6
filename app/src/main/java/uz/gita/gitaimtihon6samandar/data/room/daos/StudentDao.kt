package uz.gita.gitaimtihon6samandar.data.room.daos

import androidx.room.*
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity


@Dao
interface StudentDao {
    @Query("select * from StudentEntity where groupId=:id")
    fun getAll(id:Int):List<StudentEntity>
    @Insert
    fun insert(entity: StudentEntity)

    @Delete
    fun delete(entity: StudentEntity)

    @Update

    fun update(entity: StudentEntity)
}