package nz.co.dragontech.fitnessquest.data.exercises

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Exercise(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val category: String, // TODO: Change to category object. eg. Strength, Cardio, Flexibility
    val target: String, // TODO: Change to target object, eg. Upper Body, Lower Body, Core
    val equipment: String, // TODO: Change to equipment object, eg. Dumbbell, Barbell, Bodyweight
    val measurement: String, // TODO: Change to enum, eg. Time, Distance, Weight, Repetitions
)