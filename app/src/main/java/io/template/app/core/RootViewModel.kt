package io.template.app.core

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.template.domain.session.SessionService
import kotlinx.parcelize.Parcelize
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class RootViewModel(
    savedStateHandle: SavedStateHandle,
    private val sessionService: SessionService
) : ViewModel(),
    ContainerHost<RootState, RootEffect> {

    override val container = container<RootState, RootEffect>(
        initialState = RootState.SessionUnknown,
        savedStateHandle = savedStateHandle
    ) {
        processState(it)
    }

    private fun processState(state: RootState) {
        when (state) {
            RootState.SessionChecked -> Unit // do nothing
            RootState.SessionUnknown -> intent {
                val isAuthorized = sessionService.sessionExists()
                val effect =
                    if (isAuthorized) RootEffect.GoDashboard
                    else RootEffect.GoAuthorize
                postSideEffect(effect)
                reduce { RootState.SessionChecked }
            }
        }
    }

    fun onAuthorized() {
        intent {
            postSideEffect(RootEffect.GoDashboard)
        }
    }
}

sealed class RootState : Parcelable {
    @Parcelize
    object SessionChecked : RootState()

    @Parcelize
    object SessionUnknown : RootState()
}

sealed class RootEffect {
    object GoAuthorize : RootEffect()
    object GoDashboard : RootEffect()
}
