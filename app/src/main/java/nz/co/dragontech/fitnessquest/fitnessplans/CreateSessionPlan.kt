package nz.co.dragontech.fitnessquest.fitnessplans

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nz.co.dragontech.fitnessquest.common.Header
import nz.co.dragontech.fitnessquest.data.Exercise
import nz.co.dragontech.fitnessquest.data.exercises
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

    Column(modifier = modifier.padding(16.dp)) {
        TextField(
            value = sessionName,
            onValueChange = { sessionName = it },
            label = { Text("Session Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            groupedExercises.forEach { (category, exercises) ->
                item {
                    Text(text = category, style = MaterialTheme.typography.headlineSmall)
                }
                items(exercises) { exercise ->
                    ExerciseRow(exercise)
                }
            }
        }
    }
}

@Composable
fun ExerciseRow(exercise: Exercise) {
    var isChecked by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { isChecked = it }
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(text = exercise.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = exercise.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateSessionPlanPreview() {
    FitnessQuestTheme {
        CreateSessionPlanContent()
    }
}