package io.template.app.feature.authorization.login

import android.os.Parcelable
import android.util.Patterns
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.github.michaelbull.result.onFailure
import com.github.michaelbull.result.onSuccess
import io.template.domain.ActivityScopeExample
import io.template.domain.auth.AuthorizationService
import io.template.domain.auth.SigninCredentials
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class LoginViewModel(
    savedStateHandle: SavedStateHandle,
    private val authorizationService: AuthorizationService,
    private val activityScopeExample: ActivityScopeExample,
) : ViewModel(),
    ContainerHost<State, Effect> {

    override val container: Container<State, Effect> = container(
        initialState = State(
            LoginInput("", ""),
            State.Validity(),
            State.ActionState.Idle
        ),
        savedStateHandle = savedStateHandle
    )

    fun onInputChange(input: LoginInput) = intent {
        val result = input.validate()
        reduce { state.copy(input = input, inputValidity = result) }
    }

    fun onLogin() = intent {
        activityScopeExample.runExample()

        reduce { state.copy(actionState = State.ActionState.Progress) }
        val result = with(state.input) {
            authorizationService.signin(SigninCredentials(username, password))
        }
        reduce {
            state.copy(actionState = State.ActionState.Idle)
        }
        result
            .onSuccess { postSideEffect(Effect.Authorized) }
            .onFailure { postSideEffect(Effect.AuthError(badCredentials = true)) }
    }

    fun onRegister() = intent {
        postSideEffect(Effect.GoRegister)
    }
}

@Parcelize
data class State(
    val input: LoginInput,
    val inputValidity: Validity,
    val actionState: ActionState
) : Parcelable {

    @Parcelize
    data class Validity(
        val usernameValid: Boolean? = null,
        val passwordValid: Boolean? = null
    ) : Parcelable {
        @IgnoredOnParcel
        val isValid = usernameValid == true && passwordValid == true
    }

    enum class ActionState { Idle, Progress }
}

sealed class Effect {
    data class AuthError(val badCredentials: Boolean) : Effect()
    object Authorized : Effect()
    object GoRegister : Effect()
}

@Parcelize
data class LoginInput(val username: String, val password: String) : Parcelable {
    companion object {
        internal const val PASSWORD_VALIDATION_LENGTH = 8
    }
}

private fun LoginInput.validate(): State.Validity {
    val emailValid = username
        .ifEmpty { null }
        ?.let { Patterns.EMAIL_ADDRESS.matcher(it).matches() }
    val passwordValid = password
        .ifEmpty { null }
        ?.let { it.length >= LoginInput.PASSWORD_VALIDATION_LENGTH }
    return State.Validity(emailValid, passwordValid)
}
