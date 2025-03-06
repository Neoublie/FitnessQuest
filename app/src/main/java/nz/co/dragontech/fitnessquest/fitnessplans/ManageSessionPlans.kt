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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nz.co.dragontech.fitnessquest.common.Header
import nz.co.dragontech.fitnessquest.data.AppDatabase
import nz.co.dragontech.fitnessquest.data.sessions.SessionPlan
import nz.co.dragontech.fitnessquest.fitnessplans.ui.theme.FitnessQuestTheme

class ManageSessionPlans : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessQuestTheme {
                Scaffold(
                    topBar = {
                        Header(title = "Manage Session Plans", onMenuClick = { /* Handle menu click */ })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    ManageSessionPlansContent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ManageSessionPlansContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var sessionPlans by remember { mutableStateOf(listOf<SessionPlan>()) }

    LaunchedEffect(Unit) {
        val db = Room.databaseBuilder(
            context,
            AppDatabase::class.java, "fitness-quest-database"
        ).build()

        CoroutineScope(Dispatchers.IO).launch {
            sessionPlans = db.sessionPlanDao().getAll()
        }
    }

    Box(modifier = modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(sessionPlans) { sessionPlan ->
                SessionPlanRow(sessionPlan)
            }
        }
    }
}

@Composable
fun SessionPlanRow(sessionPlan: SessionPlan) {
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)) {
        Text(text = sessionPlan.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = sessionPlan.exercises.joinToString(", "), style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
fun ManageSessionPlansPreview() {
    FitnessQuestTheme {
        ManageSessionPlansContent()
    }
}