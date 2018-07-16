package com.alex.car

import android.content.Context
import android.support.multidex.MultiDexApplication
import io.realm.Realm


class App : MultiDexApplication() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext(): Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}