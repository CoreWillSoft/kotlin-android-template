package io.template.app.feature.dashboard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.template.app.R

@Composable
fun DashboardContent(
    input: String,
    factorial: String,
    onTextChanged: (String) -> Unit = {},
    onComputeClick: () -> Unit = {}
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            Modifier
                .padding(16.dp)
                .align(Alignment.Center),
            Arrangement.spacedBy(6.dp)
        ) {
            Text(
                modifier = Modifier.testTag("title"),
                text = stringResource(id = R.string.main_title),
                style = MaterialTheme.typography.h5
            )
            Text(
                modifier = Modifier.testTag("description"),
                text = stringResource(id = R.string.main_description),
                style = MaterialTheme.typography.caption
            )
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth().testTag("input"),
                value = input,
                label = { Text(text = stringResource(id = R.string.main_factorial_hint)) },
                onValueChange = onTextChanged,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)
            )
            Text(
                modifier = Modifier.fillMaxWidth().testTag("factorial"),
                text = factorial,
                textAlign = TextAlign.End,
                color = Color.Gray,
                style = MaterialTheme.typography.h5
            )
            Button(onClick = { onComputeClick.invoke() }, Modifier.align(Alignment.End).testTag("compute")) {
                Text(text = stringResource(id = R.string.main_compute))
            }
        }
    }
}

@Composable
@Preview
fun DashboardPreview() {
    DashboardContent(input = "3", factorial = "6")
}
