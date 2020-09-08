package io.template.app.dashboard

import android.os.Bundle
import android.view.View
import io.template.app.R
import io.template.app._core.ui.BaseFragment
import io.template.app._core.ui.viewLifecycleLazy
import io.template.app.databinding.DashboardLayoutBinding
import io.template.library.FactorialCalculator
import io.template.library.android.NotificationUtil

class DashboardFragment : BaseFragment(R.layout.dashboard_layout) {

    private val binding by viewLifecycleLazy { DashboardLayoutBinding.bind(requireView()) }
    private val notificationUtil: NotificationUtil by lazy { NotificationUtil(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonCompute.setOnClickListener {
            val input = binding.editTextFactorial.text.toString().toInt()
            val result = FactorialCalculator.computeFactorial(input).toString()

            binding.textResult.text = result
            binding.textResult.visibility = View.VISIBLE

            notificationUtil.showNotification(
                context = requireContext(),
                title = getString(R.string.notification_title),
                message = result
            )
        }
    }

}
