package io.template.app.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.screen.Screen
import io.template.app.R

class LoginScreen : Screen<LoginScreen>() {
    val disclaimer = KEditText { withId(R.id.login_disclaimer) }
}
