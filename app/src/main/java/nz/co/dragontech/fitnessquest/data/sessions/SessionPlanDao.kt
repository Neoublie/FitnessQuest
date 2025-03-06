package nz.co.dragontech.fitnessquest.data.sessions

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SessionPlanDao {
    @Insert
    suspend fun insert(sessionPlan: SessionPlan)

    @Query("SELECT * FROM SessionPlan")
    suspend fun getAll(): List<SessionPlan>

}