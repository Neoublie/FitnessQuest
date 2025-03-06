package nz.co.dragontech.fitnessquest.data.sessions

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SessionPlan(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val exercises: List<String>
)