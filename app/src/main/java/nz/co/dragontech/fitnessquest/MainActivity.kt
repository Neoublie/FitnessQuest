package nz.co.dragontech.fitnessquest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import nz.co.dragontech.fitnessquest.common.Header
import nz.co.dragontech.fitnessquest.common.FooterMenu
import nz.co.dragontech.fitnessquest.fitnessplans.CreateSessionPlan
import nz.co.dragontech.fitnessquest.fitnessplans.ManageSessionPlans
import nz.co.dragontech.fitnessquest.ui.theme.FitnessQuestTheme

class MainActivity : ComponentActivity() {
    private var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FitnessQuestTheme {
                Scaffold(
                    topBar = {
                        Header(title = "Fitness Quest", onMenuClick = { /* Handle menu click */ })
                    },
                    bottomBar = {
                        val context = LocalContext.current
                        FooterMenu(
                            onBackClick = {
                                if (backPressedTime+ 2000 > System.currentTimeMillis()) {
                                    finish()
                                } else {
                                    val toast = Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT)
                                    toast.setGravity(android.view.Gravity.BOTTOM or android.view.Gravity.CENTER_HORIZONTAL, 0, 300)
                                    toast.show()
                                }
                                backPressedTime = System.currentTimeMillis()
                            },
                            buttonText = "Exit"
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .windowInsetsPadding(
                            WindowInsets.safeContent.only(WindowInsetsSides.Bottom)
                        )
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