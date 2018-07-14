package com.alex.car.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LifecycleObserver
import com.alex.car.util.AskPermission
import com.alex.car.util.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import com.alex.car.repo.Repository


abstract class BaseViewModel<out H : BaseHandler>(context: Application, val repository
: Repository) : AndroidViewModel(context), LifecycleObserver {

    val askPermissionEvent = SingleLiveEvent<AskPermission>()
    val dataLoadingEvent = SingleLiveEvent<Boolean>()
    val showErrorEvent = SingleLiveEvent<String>()

    val disposables = CompositeDisposable()

    private lateinit var handler: H

    fun setHandler(h: BaseHandler) {
        handler = h as H
    }

    protected fun getHandler(): H = handler

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun showProgress() = dataLoadingEvent.postValue(true)
    fun hideProgress() = dataLoadingEvent.postValue(false)

    fun showError(message: String?) {
        showErrorEvent.postValue(message)
    }

    fun askPermission(askPermission: AskPermission) {
        askPermissionEvent.postValue(askPermission)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
        disposables.clear()
    }
}