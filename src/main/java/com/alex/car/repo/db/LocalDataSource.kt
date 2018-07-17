package com.alex.car.repo.db

import com.alex.car.repo.db.model.track.Track
import io.reactivex.Flowable

interface LocalDataSource {

    /*TRACK*/
    fun saveTrack(track: Track)

    fun getTrack() : Flowable<Track>

}