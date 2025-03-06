package nz.co.dragontech.fitnessquest.data

data class Exercise(
    val name: String,
    val description: String,
    val category: String,
    val measurement: String
)

val exercises = listOf(
    Exercise(
        name = "Running",
        description = "A cardiovascular exercise that involves running at a steady pace.",
        category = "Aerobic",
        measurement = "minutes"
    ),
    Exercise(
        name = "Jumping Jacks",
        description = "A full-body workout that increases heart rate and burns calories.",
        category = "Aerobic",
        measurement = "minutes"
    ),
    Exercise(
        name = "Bench Press",
        description = "A strength training exercise that targets the chest, shoulders, and triceps.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Squats",
        description = "A lower body exercise that targets the quadriceps, hamstrings, and glutes.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Deadlifts",
        description = "A compound exercise that targets the back, glutes, and hamstrings.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Lat Pulldown",
        description = "A machine-assisted exercise that targets the latissimus dorsi muscles in the back.",
        category = "Machine Assisted",
        measurement = "reps"
    ),
    Exercise(
        name = "Leg Press",
        description = "A machine-assisted exercise that targets the quadriceps, hamstrings, and glutes.",
        category = "Machine Assisted",
        measurement = "reps"
    ),
    Exercise(
        name = "Cycling",
        description = "A cardiovascular exercise performed on a stationary bike or outdoor bicycle.",
        category = "Aerobic",
        measurement = "minutes"
    ),
    Exercise(
        name = "Bicep Curls",
        description = "A strength training exercise that targets the biceps.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Tricep Dips",
        description = "A bodyweight exercise that targets the triceps.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Rowing",
        description = "A full-body cardiovascular exercise performed on a rowing machine.",
        category = "Aerobic",
        measurement = "minutes"
    ),
    Exercise(
        name = "Shoulder Press",
        description = "A strength training exercise that targets the shoulders.",
        category = "Free Weights",
        measurement = "reps"
    ),
    Exercise(
        name = "Leg Curl",
        description = "A machine-assisted exercise that targets the hamstrings.",
        category = "Machine Assisted",
        measurement = "reps"
    ),
    Exercise(
        name = "Elliptical Trainer",
        description = "A cardiovascular exercise performed on an elliptical machine.",
        category = "Aerobic",
        measurement = "minutes"
    ),
    Exercise(
        name = "Pull-Ups",
        description = "A bodyweight exercise that targets the back and biceps.",
        category = "Free Weights",
        measurement = "reps"
    )
)