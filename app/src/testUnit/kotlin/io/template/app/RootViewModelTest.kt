package io.template.app

import androidx.lifecycle.SavedStateHandle
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.Called
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.confirmVerified
import io.mockk.mockk
import io.mockk.verify
import io.template.app.core.RootEffect
import io.template.app.core.RootState
import io.template.app.core.RootViewModel
import io.template.domain.session.SessionService
import org.orbitmvi.orbit.test

internal class RootViewModelTest : BehaviorSpec({
    Given("${RootViewModel::class.simpleName}") {
        val sessionService = mockk<SessionService>()
        When("start with ${RootState.SessionUnknown::class.simpleName}") {
            mapOf(
                false to RootEffect.GoAuthorize,
                true to RootEffect.GoDashboard
            ).onEach { (sessionResponse, effect) ->
                then("check session, signal ${effect::class.simpleName}") {
                    coEvery { sessionService.sessionExists() } returns sessionResponse
                    val viewModel = RootViewModel(SavedStateHandle(), sessionService).test(
                        initialState = RootState.SessionUnknown
                    ).runOnCreate()
                    coVerify { sessionService.sessionExists() }
                    confirmVerified(sessionService)
                    viewModel.assert(RootState.SessionUnknown) {
                        states({ RootState.SessionChecked })
                        postedSideEffects(effect)
                    }
                }
            }
        }
        When("start with ${RootState.SessionChecked::class.simpleName}") {
            Then("do nothing") {
                val viewModel = RootViewModel(SavedStateHandle(), sessionService).test(
                    initialState = RootState.SessionChecked
                ).runOnCreate()
                verify { sessionService wasNot Called }
                viewModel.assert(RootState.SessionChecked)
            }
        }
    }
})
