package kyo.hen.kookbook.features.Creation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import kyo.hen.kookbook.features.Detail.CardView

@Composable
fun CreationView(modifier: Modifier = Modifier) {

    val recipeName = remember { mutableStateOf("") }
    val timeToCook = remember { mutableStateOf("15") }

    Column(modifier.fillMaxSize()) {
        LazyColumn(Modifier.weight(1f), horizontalAlignment = Alignment.CenterHorizontally) {
            item {
                RecipeName(recipeName)
                TimeToCook(timeToCook)
            }

            item {
                Instructions()
            }
        }

        SubmitButton {

        }
    }
}

@Composable
fun RecipeName(name: MutableState<String>) {
    CardView {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                name.value,
                singleLine = true,
                onValueChange = {
                    name.value = it
                },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text("Name")
                },
            )
        }
    }
}

@Composable
fun TimeToCook(value: MutableState<String>) {
    CardView {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            OutlinedTextField(
                value.value,
                singleLine = true,
                onValueChange = {
                    if (it.isDigitsOnly()) {
                        value.value = it
                    }
                },
                label = {
                    Text("Time To Make")
                },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Composable
fun Instructions() {
    val instructions = remember { mutableStateListOf("") }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            CardView {
                Column(modifier = Modifier.fillMaxWidth().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Instructions", style = MaterialTheme.typography.titleLarge)
                }
            }
            instructions.forEachIndexed { index, it ->
                CardView {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Column {
                            IconButton(
                                onClick = {
                                    val temp = instructions[index - 1]
                                    instructions[index - 1] =  instructions[index]
                                    instructions[index] = temp
                                          },
                                enabled = index != 0
                            ) {
                                Icon(Icons.Filled.KeyboardArrowUp, contentDescription = "Shift Up")
                            }

                            IconButton(
                                onClick = {
                                    val temp = instructions[index + 1]
                                    instructions[index + 1] =  instructions[index]
                                    instructions[index] = temp
                                },
                                enabled = index != instructions.lastIndex
                            ) {
                                Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Delete")
                            }
                        }
                        OutlinedTextField(
                            it,
                            singleLine = false,
                            onValueChange = { newValue ->
                                instructions[index] = newValue
                            },
                            label = {
                                Text(index.toString())
                            },
                            placeholder = {
                                Text("Enter Instruction")
                            },
                            modifier = Modifier.weight(1f),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )

                        IconButton(onClick = {
                            instructions.removeAt(index)
                        }) {
                            Icon(Icons.Filled.Close, contentDescription = "Delete")
                        }
                    }
                }
            }

            SmallFloatingActionButton(
                onClick = {
                    instructions.add("")
                }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Instruction")
            }
        }
}

@Composable
fun SubmitButton(action: () -> Unit) {
    Button(
        onClick = action,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.padding(horizontal = 16.dp).padding(top = 8.dp)
    ) {
        Text(
            "Submit",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            textAlign = TextAlign.Center
        )
    }
}

// TODO: State based textfields
class DigitOnlyInputTransformation : InputTransformation {
    override fun TextFieldBuffer.transformInput() {
        if (!asCharSequence().isDigitsOnly()) {
            revertAllChanges()
        }
    }
}