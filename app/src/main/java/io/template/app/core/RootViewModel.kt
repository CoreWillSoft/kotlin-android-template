package io.template.app.core

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.babylon.orbit2.ContainerHost
import com.babylon.orbit2.coroutines.transformSuspend
import com.babylon.orbit2.syntax.strict.orbit
import com.babylon.orbit2.syntax.strict.reduce
import com.babylon.orbit2.syntax.strict.sideEffect
import com.babylon.orbit2.viewmodel.container
import io.template.domain.session.SessionService
import kotlinx.android.parcel.Parcelize

class RootViewModel(
    savedStateHandle: SavedStateHandle,
    private val sessionService: SessionService
) : ViewModel(),
    ContainerHost<RootState, RootEffect> {

    override val container = container<RootState, RootEffect>(
        initialState = RootState.SessionUnknown,
        savedStateHandle = savedStateHandle
    ) {
        when (it) {
            RootState.SessionChecked -> Unit // do nothing
            RootState.SessionUnknown -> orbit {
                transformSuspend { sessionService.sessionExists() }
                    .sideEffect {
                        val isAuthorized = event
                        val effect =
                            if (isAuthorized) RootEffect.GoDashboard
                            else RootEffect.GoAuthorize
                        post(effect)
                    }
                    .reduce { RootState.SessionChecked }
            }
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
