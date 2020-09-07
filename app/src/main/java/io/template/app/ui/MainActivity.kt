package io.template.app.ui

import android.os.Bundle
import android.view.View
import io.template.app.R
import io.template.app.databinding.MainActivityBinding
import io.template.app.ui.base.BaseActivity
import io.template.library.FactorialCalculator
import io.template.library.android.NotificationUtil

class MainActivity : BaseActivity() {

    private val notificationUtil: NotificationUtil by lazy { NotificationUtil(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = MainActivityBinding.inflate(layoutInflater).apply { setContentView(root) }

        binding.buttonCompute.setOnClickListener {
            val input = binding.editTextFactorial.text.toString().toInt()
            val result = FactorialCalculator.computeFactorial(input).toString()

            binding.textResult.text = result
            binding.textResult.visibility = View.VISIBLE

            notificationUtil.showNotification(
                context = this,
                title = getString(R.string.notification_title),
                message = result
            )
        }
    }
}
