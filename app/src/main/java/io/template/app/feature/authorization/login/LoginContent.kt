package io.template.app.feature.authorization.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.template.app.R

@Composable
fun LoginContent(
    uiState: State,
    onInputChange: (LoginInput) -> Unit = {},
    onLoginClick: () -> Unit = {},
    onRegisterClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h4,
                color = Color.Gray,
                text = stringResource(id = R.string.login_disclaimer)
            )
            CredentialInput(
                input = uiState.input.username,
                hint = stringResource(id = R.string.login_username_hint),
                errorHint = stringResource(id = R.string.login_username_invalid_hint),
                isInputError = uiState.inputValidity.usernameValid == false,
                keyboardType = KeyboardType.Email,
                icon = painterResource(id = R.drawable.login_username_ic),
                onInputChange = { onInputChange(uiState.input.copy(username = it)) }
            )
            CredentialInput(
                input = uiState.input.password,
                hint = stringResource(id = R.string.login_password_hint),
                errorHint = stringResource(id = R.string.login_password_invalid_hint),
                isInputError = uiState.inputValidity.passwordValid == false,
                keyboardType = KeyboardType.Password,
                icon = painterResource(id = R.drawable.login_password_ic),
                onInputChange = { onInputChange(uiState.input.copy(password = it)) }
            )
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                enabled = uiState.inputValidity.isValid,
                onClick = { onLoginClick() }
            ) {
                Text(text = stringResource(id = R.string.login_action))
            }
        }
        OutlinedButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            border = null,
            onClick = { onRegisterClick() }
        ) {
            Text(text = stringResource(id = R.string.login_go_register))
        }
    }
}

@Composable
fun CredentialInput(
    input: String,
    hint: String,
    errorHint: String,
    isInputError: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Decimal,
    icon: Painter,
    onInputChange: (String) -> Unit = {}
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = input,
        shape = MaterialTheme.shapes.large,
        isError = isInputError,
        label = { Text(text = hint) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onValueChange = { onInputChange(it) },
        leadingIcon = {
            Icon(
                painter = icon,
                contentDescription = null
            )
        }
    )
    if (isInputError) {
        Text(
            text = errorHint,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.padding(start = 16.dp)
        )
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginContent(
        uiState = State(
            input = LoginInput(
                username = "user@gmail.com",
                password = "qwerty"
            ),
            inputValidity = State.Validity(usernameValid = true, passwordValid = true),
            actionState = State.ActionState.Idle
        )
    )
}
