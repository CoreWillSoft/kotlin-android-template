package io.template.app.feature.dashboard

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import io.template.app.R
import io.template.library.FactorialCalculator
import io.template.library.android.NotificationUtil

@Composable
fun DashboardScreen() {
    var text by remember {
        mutableStateOf("")
    }
    var factorial by remember {
        mutableStateOf(0L)
    }
    val context = LocalContext.current
    val notificationTitle = stringResource(id = R.string.notification_title)
    val notificationsUtil = NotificationUtil(context)

    DashboardContent(
        input = text,
        factorial = factorial.toString(),
        onTextChanged = { text = it },
        onComputeClick = {
            factorial =
                runCatching { FactorialCalculator.computeFactorial(text.toInt()) }.getOrElse { factorial }
            notificationsUtil.showNotification(
                context = context,
                title = notificationTitle,
                message = factorial.toString()
            )
        }
    )
}
