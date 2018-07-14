package com.alex.car.base

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.ViewOutlineProvider
import android.view.Window
import com.alex.car.BR
import com.alex.car.R
import com.alex.car.extantions.obtainViewModel
import com.alex.car.util.AskPermission
import com.alex.car.extantions.toast
import com.tbruyelle.rxpermissions2.RxPermissions
import dmax.dialog.SpotsDialog


abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<BaseHandler>>
    : AppCompatActivity(), Progress {

    protected lateinit var binding: T
    private lateinit var viewModel: V

    var progress: AlertDialog? = null

    @LayoutRes
    protected abstract fun layoutResId(): Int

    protected abstract fun viewModelClass(): Class<V>

    protected fun viewModel(): V = viewModel

    var outlineProvider: ViewOutlineProvider? = null

    protected abstract fun getViewModelHandler(): BaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = obtainViewModel(viewModelClass())
        viewModel.setHandler(getViewModelHandler())
        binding = DataBindingUtil.setContentView(this, layoutResId())
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
        this.lifecycle.addObserver(viewModel)
        this.lifecycle.addObserver(viewModel.repository)
        subscribeToDataLoadingEvents()
        subscribeToShowErrorEvents()
        subscribeToAskPermissionEvents()
    }

    protected fun setupToolbar(toolbar: Toolbar, backBt: Boolean, backIconId: Int) {
        setSupportActionBar(toolbar)
        if (backBt) showBackToolbarButton(toolbar, backIconId)
    }

    private fun showBackToolbarButton(toolbar: Toolbar, backIconId: Int) {
        toolbar.setNavigationIcon(backIconId)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun subscribeToDataLoadingEvents() {
        viewModel().dataLoadingEvent.observe(this, Observer {
            when (it) {
                true -> showProgress()
                false -> hideProgress()
            }
        })
    }

    private fun subscribeToShowErrorEvents() {
        viewModel().showErrorEvent.observe(this, Observer {
            it?.let { toast(it) } //todo show error
        })
    }

    private fun subscribeToAskPermissionEvents() {
        viewModel().askPermissionEvent.observe(this, Observer {
            val askPermission: AskPermission? = it
            val permissions: Array<String>? = it?.permissions
            permissions?.let {
                RxPermissions(this)
                        .request(*permissions)
                        .subscribe { granted ->
                            if (!granted) toast("No permission")
                            askPermission?.function?.invoke(granted)
                        }
            }
        })
    }

    override fun showProgress() {
        if (progress == null) {
            progress = SpotsDialog(this)
            progress?.requestWindowFeature(Window.FEATURE_NO_TITLE)
            val wmlp = progress?.getWindow()!!.attributes
            wmlp.gravity = Gravity.CENTER
            wmlp.y = 100
            progress?.show()
        }
    }


    private fun showAlertDialog(title: String?, message: String, obj: () -> Unit, buttonText: String?) {
        AlertDialog.Builder(this, R.style.AlertDialogCustom)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText) { _, _ -> obj.invoke() }
                .setNegativeButton("cancel") { dialog, _ -> dialog?.dismiss() }
                .create().show()
    }

    override fun hideProgress() {
        progress?.hide()
        progress = null
    }

    override fun onBackPressed() {
        hideProgress()
        showAlertDialog(null, "Exit?", { super.onBackPressed() }, "exit")
    }

    override fun onPause() {
        super.onPause()
        hideProgress()
    }
}

interface Progress {
    fun showProgress()
    fun hideProgress()
}