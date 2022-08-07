package com.chenqianhe.lohasfarm.logic.database

import androidx.room.*
import com.chenqianhe.lohasfarm.DATABASE_VERSION


@Entity(tableName = "PlantIntroInfo", primaryKeys = ["land_uid", "goods_uid"])
class PlantIntroInfo {
    var goods_name: String = ""
    var goods_uid: String = ""
    var land_uid: String = ""
    var plant_root_url: String = ""
    var plant_state: String = ""
    var plant_day: Int = 0
    var plant_total_day: Int = Int.MAX_VALUE
}


@Dao
abstract class PlantIntroInfoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(plant: PlantIntroInfo)

    @Delete
    abstract fun delete(plant: PlantIntroInfo)

    @Update
    abstract fun update(plant: PlantIntroInfo)

    @Query("select * from PlantIntroInfo where goods_uid = :goodsUid")
    abstract fun quertByUid(goodsUid: String): PlantIntroInfo

    @Query("select * from PlantIntroInfo")
    abstract fun queryAll(): List<PlantIntroInfo>

    @Query("delete from PlantIntroInfo")
    abstract fun deleteAll()

}


@Database(entities = [PlantIntroInfo::class], version = DATABASE_VERSION)
abstract class PlantIntroInfoDatabase : RoomDatabase(){

    abstract fun PlantIntroInfoDao(): PlantIntroInfoDao

}