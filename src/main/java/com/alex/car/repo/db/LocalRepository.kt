package com.alex.car.repo.db


import com.alex.car.repo.db.model.track.Track
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.disposables.Disposables
import io.realm.Realm
import io.realm.RealmChangeListener


object LocalRepository : LocalDataSource {

    private fun realmTransaction(function: (realm: Realm) -> Unit) {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        function(realm)
        realm.commitTransaction()
        realm.close()
    }

    private fun realm(needListener: Boolean): Flowable<Realm> {
        return Flowable.create({
            val emitter = it
            val realm = Realm.getDefaultInstance()
            var listener: RealmChangeListener<Realm>? = null
            if (needListener) {
                listener = RealmChangeListener { emitter.onNext(it) }
                realm.addChangeListener(listener)
            }
            emitter.onNext(realm)
            emitter.setDisposable(Disposables.fromRunnable {
                listener?.let { realm.removeChangeListener(listener) }
                realm.close()
            })
        }, BackpressureStrategy.LATEST)
    }

    /*TRACK*/
    override fun saveTrack(track: Track) {
        realmTransaction { it.insertOrUpdate(track) }
    }

    override fun getTrack(): Flowable<Track> {
                return realm(false)
                .map { it.where(Track::class.java).findFirst()}
    }

}