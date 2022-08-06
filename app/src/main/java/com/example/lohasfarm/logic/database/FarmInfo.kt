package com.example.lohasfarm.logic.database

import androidx.room.*
import com.example.lohasfarm.DATABASE_VERSION


@Entity(tableName = "FarmInfo")
class FarmInfo {

    @PrimaryKey
    @ColumnInfo(name = "land_uid")
    var landUid: String = ""

    @ColumnInfo(name = "land_name")
    var landName: String = ""

    @ColumnInfo(name = "land_profile_photo")
    var landProfilePhoto: String = "https://mmbiz.qpic.cn/mmbiz/icTdbqWNOwNRna42FI242Lcia07jQodd2FJGIYQfG0LAJGFxM4FbnQP6yfMxBgJ0F3YRqJCJ1aPAK2dQagdusBZg/0"

    @ColumnInfo(name = "is_mine")
    var isMine: Boolean = false

    @ColumnInfo(name = "land_planted_area")
    var landPlantedArea: Int = 0

    @ColumnInfo(name = "land_total_area")
    var landTotalArea: Int = Int.MAX_VALUE

    @ColumnInfo(name = "land_soil_type")
    var landSoilType: String = "黑土地"

    @ColumnInfo(name = "land_lease_term")
    var landLeaseTerm: Int = 0

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