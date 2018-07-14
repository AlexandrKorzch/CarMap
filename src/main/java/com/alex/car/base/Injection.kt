package com.alex.car.base

import android.content.Context
import com.alex.car.repo.Repository
import com.alex.car.repo.db.LocalRepository
import com.alex.car.repo.remote.RemoteRepository


object Injection {
    fun provideRepository(context: Context): Repository {
        return Repository.getInstance(RemoteRepository, LocalRepository)
    }
}
