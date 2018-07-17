package com.alex.car.repo

import android.arch.lifecycle.LifecycleObserver
import com.alex.car.repo.db.LocalDataSource
import com.alex.car.repo.db.model.track.Track
import com.alex.car.repo.remote.RemoteDataSource
import com.alex.car.repo.remote.api.ApiSettings.API_KEY
import com.alex.car.repo.remote.model.Rout
import com.google.android.gms.maps.model.LatLng
import io.reactivex.Flowable


class Repository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) : LifecycleObserver {

    /*REMOTE*/
    fun getRout(position: String, destination: String): Flowable<Rout>
            = remoteDataSource.getRout(position, destination, API_KEY)

    /*LOCAL*/
    fun saveTrack(track: Track) {
        localDataSource.saveTrack(track)
    }

    fun getTrack(): Flowable<List<LatLng>> {
        return localDataSource.getTrack()
                .map { it.segments }
                .map {
                    val list = ArrayList<LatLng> ()
                    it.forEach {
                        list.add(LatLng(it.lat!!, it.lon!!))
                    }
                    list
                }
    }

    companion object {
        private var INSTANCE: Repository? = null
        @JvmStatic
        fun getInstance(remoteDataSource: RemoteDataSource,
                        localDataSource: LocalDataSource) =
                INSTANCE ?: synchronized(Repository::class.java) {
                    INSTANCE ?: Repository(remoteDataSource, localDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}
