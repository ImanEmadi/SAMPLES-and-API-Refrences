package com.what.ever
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.unit.dp
import com.nova.todo.ui.theme.NovaTODOTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            NovaTODOTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }
}


@Composable
fun Screen() {
    val theInputs = mapOf("Germany" to "DE", "Norway" to "NW", "Netherlands" to "NE")
    var (selectedLand, setLand) = remember { mutableStateOf<String?>(null) }

    Column(modifier = Modifier.fillMaxSize()) {
        Row {
            MyRadioGroup(
                options = theInputs,
                currentSelection = selectedLand,
                onOptionSelect = setLand
            )
        }
        if (selectedLand is String)
            Row {
                Text(text = "You have selected: $selectedLand", color = Color(0xFFDDD1D1))
            }
    }

}

@Composable
fun <V> MyRadioGroup(
    options: Map<String, V>,
    currentSelection: V?,
    onOptionSelect: ((selection: V) -> Unit) = { _ -> Unit }
) {
    options.forEach(action = { (label, value) ->
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = { onOptionSelect(value) })
                .padding(horizontal = 10.dp)
        ) {
            RadioButton(
                selected = currentSelection === value,
                onClick = null
            )
            Text(text = label)
        }
    })
}
