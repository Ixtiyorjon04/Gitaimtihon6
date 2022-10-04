package uz.gita.gitaimtihon6samandar.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.gitaimtihon6samandar.data.room.daos.GroupDao
import uz.gita.gitaimtihon6samandar.data.room.daos.StudentDao
import uz.gita.gitaimtihon6samandar.data.room.daos.UsersDao
import uz.gita.gitaimtihon6samandar.data.room.entities.GroupEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.StudentEntity
import uz.gita.gitaimtihon6samandar.data.room.entities.UserEntity

@Database(entities = [UserEntity::class,GroupEntity::class,StudentEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {
    companion object{
        private var appDatabase :AppDatabase?=null

        fun init(context: Context){
            if (appDatabase==null){
                appDatabase=Room.databaseBuilder(context,AppDatabase::class.java,"appDatabase")
                    .allowMainThreadQueries()
                    .build()
            }
        }
        fun getAppDatabase()= appDatabase!!
    }
  abstract  fun getUserDao():UsersDao
  abstract  fun getGroupDao():GroupDao
  abstract  fun getStudentDao():StudentDao

}