package io.template.app.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import io.template.app.R

class DashboardScreen : Screen<DashboardScreen>() {
    val title = KTextView { withId(R.id.title) }
    val description = KTextView { withId(R.id.description) }
    val factorial = KEditText { withId(R.id.edit_text_factorial) }
    val compute = KButton { withId(R.id.button_compute) }
    val result = KEditText { withId(R.id.text_result) }
}
