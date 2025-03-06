package nz.co.dragontech.fitnessquest.fitnessplans

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.dragontech.fitnessquest.common.Header
import nz.co.dragontech.fitnessquest.common.FooterMenu
import nz.co.dragontech.fitnessquest.data.AppDatabase
import nz.co.dragontech.fitnessquest.data.Exercise
import nz.co.dragontech.fitnessquest.data.exercises
import nz.co.dragontech.fitnessquest.data.sessions.SessionPlan
import nz.co.dragontech.fitnessquest.fitnessplans.ui.theme.FitnessQuestTheme

class CreateSessionPlan : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessQuestTheme {
                Scaffold(
                    topBar = {
                        Header(title = "Create Session Plan", onMenuClick = { /* Handle menu click */ })
                    },
                    bottomBar = {
                        FooterMenu(onBackClick = { finish() })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    CreateSessionPlanContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CreateSessionPlanContent(modifier: Modifier = Modifier) {
    var sessionName by remember { mutableStateOf("") }
    val groupedExercises = exercises.groupBy { it.category }
    val selectedExercises = remember { mutableStateListOf<Exercise>() }
    val context = LocalContext.current

    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        Column(modifier = Modifier.fillMaxSize()) {
            TextField(
                value = sessionName,
                onValueChange = { sessionName = it },
                label = { Text("Session Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                groupedExercises.forEach { (category, exercises) ->
                    item {
                        Text(text = category, style = MaterialTheme.typography.headlineSmall)
                    }
                    items(exercises) { exercise ->
                        ExerciseRow(exercise, selectedExercises)
                    }
                }
            }
        }
        Button(
            onClick = { saveSessionPlan(context, sessionName, selectedExercises) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
        ) {
            Text(text = "Save Session Plan")
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise, selectedExercises: MutableList<Exercise>) {
    var isChecked by remember { mutableStateOf(false) }
    var measurement by remember { mutableStateOf("")}
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                if (isChecked) {
                    selectedExercises.add(exercise)
                } else {
                    selectedExercises.remove(exercise)
                }
            }
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = exercise.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = exercise.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

fun saveSessionPlan(context: Context, name: String, exercises: List<Exercise>) {
    val db = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "fitness-quest-database"
    ).build()

    val sessionPlan = SessionPlan(
        name = name,
        exercises = exercises.map { it.name }
    )

    CoroutineScope(Dispatchers.IO).launch {
        db.sessionPlanDao().insert(sessionPlan)
    }
}

@Preview(showBackground = true)
@Composable
fun CreateSessionPlanPreview() {
    FitnessQuestTheme {
        CreateSessionPlanContent()
    }
}