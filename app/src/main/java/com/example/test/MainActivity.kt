package com.example.test

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            MemberScreen()
        }
    }
}

@Composable
fun MemberScreen() {

    var studentName by remember {
        mutableStateOf("Suprabhat Chowhan")
    }

    var registrationNumber by remember {
        mutableStateOf("12301752")
    }

    var department by remember {
        mutableStateOf("Computer Science")
    }

    var contact by remember {
        mutableStateOf("8837361741")
    }

    var showDialog by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        StudentCard(
            studentName = studentName,
            registrationNumber = registrationNumber,
            department = department,
            contact = contact,
            onEditClick = {
                showDialog = true
            }
        )
    }

    if (showDialog) {

        EditStudentDialog(
            studentName = studentName,
            registrationNumber = registrationNumber,
            department = department,
            contact = contact,

            onDismiss = {
                showDialog = false
            },

            onSave = { newName, newRegistrationNumber, newDepartment, newContact ->

                studentName = newName
                registrationNumber = newRegistrationNumber
                department = newDepartment
                contact = newContact

                showDialog = false
            }
        )
    }
}


@Composable
fun StudentCard(
    studentName: String,
    registrationNumber: String,
    department: String,
    contact: String,
    onEditClick: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            Text(
                text = "Student Details",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            StudentDetail(
                label = "Name",
                value = studentName
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            StudentDetail(
                label = "Registration Number",
                value = registrationNumber
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            StudentDetail(
                label = "Department",
                value = department
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            StudentDetail(
                label = "Contact",
                value = contact
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )

            Button(
                onClick = onEditClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Edit Student")
            }
        }
    }
}


@Composable
fun StudentDetail(
    label: String,
    value: String
) {

    Column {

        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}


@Composable
fun EditStudentDialog(
    studentName: String,
    registrationNumber: String,
    department: String,
    contact: String,
    onDismiss: () -> Unit,
    onSave: (
        String,
        String,
        String,
        String
    ) -> Unit
) {

    var editedName by remember {
        mutableStateOf(studentName)
    }

    var editedRegistrationNumber by remember {
        mutableStateOf(registrationNumber)
    }

    var editedDepartment by remember {
        mutableStateOf(department)
    }

    var editedContact by remember {
        mutableStateOf(contact)
    }

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {
            Text(
                text = "Edit Student"
            )
        },

        text = {

            Column {

                OutlinedTextField(
                    value = editedName,
                    onValueChange = {
                        editedName = it
                    },
                    label = {
                        Text("Name")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = editedRegistrationNumber,
                    onValueChange = {
                        editedRegistrationNumber = it
                    },
                    label = {
                        Text("Registration Number")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = editedDepartment,
                    onValueChange = {
                        editedDepartment = it
                    },
                    label = {
                        Text("Department")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                OutlinedTextField(
                    value = editedContact,
                    onValueChange = {
                        editedContact = it
                    },
                    label = {
                        Text("Contact")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )
            }
        },

        confirmButton = {

            TextButton(
                onClick = {

                    onSave(
                        editedName,
                        editedRegistrationNumber,
                        editedDepartment,
                        editedContact
                    )
                }
            ) {
                Text("Save")
            }
        },

        dismissButton = {

            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}