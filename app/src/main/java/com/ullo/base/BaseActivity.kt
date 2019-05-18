package com.ullo.base

import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.ullo.R
import com.ullo.utils.ConnectionLiveData
import dagger.android.AndroidInjection
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
import android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
import android.view.View


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(), BaseNavigator {

    private var viewDataBinding: T? = null
    private var mViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V?

    abstract val isFullScreen: Boolean
    var isConnected: Boolean = false

    fun getViewDataBinding(): T {
        return viewDataBinding!!
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        if (isFullScreen) {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN

        super.onCreate(savedInstanceState)
        performDataBinding()

        val connectionLiveData = ConnectionLiveData(this)
        connectionLiveData.observe(this, Observer { isConnected ->
            isConnected?.let {
                this.isConnected = it
            }
        })
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.mViewModel = if (mViewModel == null) viewModel else mViewModel
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.executePendingBindings()
    }

    override fun handleError(error: String) {
        AlertDialog.Builder(this)
                .setTitle("Error")
                .setIcon(R.drawable.error)
                .setMessage(error)
                .setPositiveButton(android.R.string.yes) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
    }

    override fun onInternetConnectionError() {
        AlertDialog.Builder(this)
                .setTitle("Error")
                .setIcon(R.drawable.error)
                .setMessage(getString(R.string.please_check_your_internet_connection_or_try_again_later))
                .setPositiveButton(android.R.string.yes) { dialog, _ ->
                    dialog.dismiss()
                }
                .show()
    }
}