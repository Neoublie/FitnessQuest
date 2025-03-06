package nz.co.dragontech.fitnessquest

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nz.co.dragontech.fitnessquest.common.Header
import nz.co.dragontech.fitnessquest.fitnessplans.CreateSessionPlan
import nz.co.dragontech.fitnessquest.fitnessplans.ManageSessionPlans
import nz.co.dragontech.fitnessquest.ui.theme.FitnessQuestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessQuestTheme {
                Scaffold(
                    topBar = {
                        Header(title = "Fitness Quest", onMenuClick = { /* Handle menu click */ })
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    MainContent(
                        modifier = Modifier.padding(innerPadding),
                        onCreateSessionPlanClick = {
                            val intent = Intent(this, CreateSessionPlan::class.java)
                            startActivity(intent)
                        },
                        onManageSessionPlansClick = {
                            val intent = Intent(this, ManageSessionPlans::class.java)
                            startActivity(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    onCreateSessionPlanClick: () -> Unit,
    onManageSessionPlansClick: () -> Unit) {
    Column(modifier = modifier) {
        Greeting(name = "Android")
        Button(onClick = onCreateSessionPlanClick) {
            Text(text = "Create Session Plan")
        }
        Button(onClick = onManageSessionPlansClick) {
            Text(text = "Manage Session Plans")
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FitnessQuestTheme {
        MainContent(
            onCreateSessionPlanClick = {},
            onManageSessionPlansClick = {}
        )
    }
}