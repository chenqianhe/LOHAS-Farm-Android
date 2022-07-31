package com.example.lohasfarm.logic.database

import androidx.room.*
import com.example.lohasfarm.DATABASE_VERSION


@Entity(tableName = "FarmInfo")
class FarmInfo {

    @PrimaryKey
    @ColumnInfo(name = "land_uid")
    var landUid: String = ""

    @ColumnInfo(name = "land_name")
    var landName: String? = ""

    @ColumnInfo(name = "land_profile_photo")
    var landProfilePhoto: String = "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0"

    @ColumnInfo(name = "mine")
    var isMine: Boolean = false

}


@Dao
abstract class FarmInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(farm: FarmInfo)

    @Delete
    abstract fun delete(farm: FarmInfo)

    @Update
    abstract fun update(farm: FarmInfo)

    @Query("select * from FarmInfo where land_uid = :landUid")
    abstract fun quertByUid(landUid: String): FarmInfo

    @Query("select * from FarmInfo")
    abstract fun queryAll(): List<FarmInfo>

    @Query("delete from FarmInfo")
    abstract fun deleteAll()

}


@Database(entities = [FarmInfo::class], version = DATABASE_VERSION)
abstract class FarmInfoDatabase : RoomDatabase(){

    abstract fun farmInfoDao(): FarmInfoDao

}