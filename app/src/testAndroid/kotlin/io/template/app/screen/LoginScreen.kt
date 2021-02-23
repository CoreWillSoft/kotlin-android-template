package io.template.app.screen

import com.agoda.kakao.edit.KEditText
import com.agoda.kakao.edit.KTextInputLayout
import com.agoda.kakao.screen.Screen
import io.template.app.R

class LoginScreen : Screen<LoginScreen>() {
    val disclaimer = KEditText { withId(R.id.disclaimer) }
    val usernameWrapper = KTextInputLayout { withId(R.id.username_wrapper) }
    val username = KEditText { withId(R.id.username) }
    val passwordWrapper = KTextInputLayout { withId(R.id.password_wrapper) }
    val password = KEditText { withId(R.id.password) }
    val action = KEditText { withId(R.id.try_login) }
    val unregistered = KEditText { withId(R.id.unregistered) }
}
