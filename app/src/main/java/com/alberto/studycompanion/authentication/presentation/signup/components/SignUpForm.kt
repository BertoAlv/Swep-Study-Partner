package com.alberto.studycompanion.authentication.presentation.signup.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alberto.studycompanion.authentication.presentation.signup.SignUpEvent
import com.alberto.studycompanion.authentication.presentation.signup.SignUpState
import com.alberto.studycompanion.core.StudyButton
import com.alberto.studycompanion.core.StudyPasswordTextfield
import com.alberto.studycompanion.core.StudyTextfield
import com.alberto.studycompanion.core.StudyTitle

@Composable
fun SignUpForm(
    state: SignUpState,
    onEvent: (SignUpEvent) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {

        StudyTitle(title = "CREATE YOUR ACCOUNT")

        Spacer(modifier = Modifier.height(32.dp))

        StudyTextfield(
            value = state.email,
            onValueChange = { onEvent(SignUpEvent.EmailChanged(it)) },
            placeholder = "Email",
            contentDescription = "Enter email",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            leadingIcon = Icons.Outlined.Email,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(onNext = {
                focusManager.moveFocus(FocusDirection.Next)
            }),
            errorMessage = state.emailError,
            isEnabled = !state.isLoading,
            backgroundColor = Color.White
        )

        StudyPasswordTextfield(
            value = state.password,
            onValueChange = { onEvent(SignUpEvent.PasswordChanged(it)) },
            contentDescription = "Enter password",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 6.dp)
                .padding(horizontal = 20.dp),
            errorMessage = state.passwordError,
            isEnabled = !state.isLoading,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                focusManager.clearFocus()
                onEvent(SignUpEvent.SignUp)
            }),
            backgroundColor = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        StudyButton(
            content = "Create Account",
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            isEnabled = !state.isLoading
        ) {
            onEvent(SignUpEvent.SignUp)
        }
        TextButton(onClick = { onEvent(SignUpEvent.SignIn) }) {
            Text(
                text = buildAnnotatedString {
                    append("Already have an account? ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Sign in")
                    }
                },
                color = MaterialTheme.colorScheme.tertiary
            )
        }
    }
}

@Composable
@Preview
fun FormPreview() {
    SignUpForm(state = SignUpState(), onEvent = {})
}