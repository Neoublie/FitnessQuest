package nz.co.dragontech.fitnessquest.data.exercises

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExerciseDao {
    @Insert
    suspend fun insert(exercise: Exercise)

    @Query("SELECT * FROM Exercise")
    suspend fun getAll(): List<Exercise>
}