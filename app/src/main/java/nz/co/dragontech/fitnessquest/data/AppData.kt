package nz.co.dragontech.fitnessquest.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import nz.co.dragontech.fitnessquest.data.sessions.SessionPlan
import nz.co.dragontech.fitnessquest.data.sessions.SessionPlanDao

@Database(entities = [SessionPlan::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sessionPlanDao(): SessionPlanDao
}

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return value.joinToString(separator = ",")
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        return value.split(",")
    }
}