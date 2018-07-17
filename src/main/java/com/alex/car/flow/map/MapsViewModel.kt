package com.alex.car.flow.map

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableBoolean
import android.os.Bundle
import com.alex.car.R
import com.alex.car.base.BaseHandler
import com.alex.car.base.BaseViewModel
import com.alex.car.repo.Repository
import com.alex.car.repo.db.model.track.Segment
import com.alex.car.repo.db.model.track.Track
import com.alex.car.repo.remote.model.Rout
import com.alex.car.util.END
import com.alex.car.util.INTERMEDIATE
import com.alex.car.util.START
import com.alex.car.util.getRoutFlowables
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.AutocompletePredictionBuffer
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.PolyUtil
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.realm.RealmList


class MapsViewModel(context: Application, repository: Repository)
    : BaseViewModel<MapsViewModel.Handler>(context, repository),
        LifecycleObserver, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    val MAX_INTERMADIATE = 3

    private var connected: Boolean = false //not use

    var startPlace: LatLng? = null
    var endPlace: LatLng? = null
    var intermediatePlaces = ArrayList<LatLng>()

    val fromTrack = ObservableBoolean(false)
    val intermediateBt = ObservableBoolean(false)
    val addIntermediate = ObservableBoolean(false)
    val rase = ObservableBoolean(false)

    var steps = ArrayList<LatLng>()

    private lateinit var googleApiClient: GoogleApiClient


    fun startPlaceTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showStartPlaces(it) }
    }

    fun nextPlaceTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showNextPlaces(it) }
    }

    fun intermediateTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showIntermediatePlaces(it) }
    }

    fun addIntermediateClick() {
        addIntermediate.set(true)
        getHandler().clearIntermediateText()
    }

    fun startClick() = requestRouts()

    private fun requestRouts() {
        addDisposable(Flowable
                .concat(getRoutFlowables(repository, startPlace, intermediatePlaces, endPlace))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ onGetRout(it) },
                        {
                            hideProgress()
                            showError(getApplication<Application>().getString(R.string.error))
                            showError(it.message)
                        },
                        {
                            hideProgress()
                            showRouts()
                        }))
    }

    private fun onGetRout(it: Rout?) {
        steps.addAll(PolyUtil.decode(it?.routes?.first()?.overviewPolyline?.points))
    }

    private fun showRouts() {
        rase.set(true)
        getHandler().showPolilines(steps)
        getHandler().runCar(steps)
    }


    fun saveRoute(steps: ArrayList<LatLng>) {
        val segments = RealmList<Segment>()
        steps.forEachIndexed { index, latLng ->
            segments.add(Segment(index.toLong(), latLng.latitude, latLng.longitude))
        }
        val track = Track(1L, segments)
        repository.saveTrack(track)
        fromTrack.set(true)
    }

    fun getSavedRoteClick() = getSavedRote()

    private fun getSavedRote() {
        addDisposable(repository.getTrack()
                .subscribe {
                    getHandler().showSavedTrack(it as ArrayList<LatLng>)
                    getHandler().runCar(it)
                }
        )
    }

    private fun checkIntermediateAllow() {
        if (startPlace != null && endPlace != null) {
            intermediateBt.set(true)
        }
    }

    private fun addToIntermediate(latLng: LatLng) {
        if (intermediatePlaces.size < MAX_INTERMADIATE) intermediatePlaces.add(latLng)
        else showError(getApplication<Application>().getString(R.string.max_5))
    }


    //google api client
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = initGoogleApiClient()

    private fun initGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(getApplication())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build()

        googleApiClient.connect()
    }

    private fun getPlacesByText(query: String, func: (buf: AutocompletePredictionBuffer) -> Unit) {
        if (query.length >= 3) {
            Places.GeoDataApi
                    .getAutocompletePredictions(googleApiClient, query, null, null)
                    .setResultCallback { func(it) }
        }
    }

    fun getCoordinatesByPlaceId(placeId: String?, pointType: Int) {
        Places.GeoDataApi.getPlaceById(googleApiClient, placeId).setResultCallback {
            if (it.status.isSuccess) {
                val place = it.first()
                when (pointType) {
                    START -> startPlace = place.latLng
                    END -> endPlace = place.latLng
                    INTERMEDIATE -> addToIntermediate(place.latLng)
                }
                checkIntermediateAllow()
                getHandler().showPin()
            }
            it.release()
        }
    }

    override fun onConnected(p0: Bundle?) {
        connected = true
    }

    override fun onConnectionSuspended(p0: Int) {
        showError(getApplication<Application>().getString(R.string.connection_suspended))
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        showError(getApplication<Application>().getString(R.string.connection_failed))
    }


    //callback
    interface Handler : BaseHandler {
        fun showStartPlaces(buffer: AutocompletePredictionBuffer)
        fun showNextPlaces(buffer: AutocompletePredictionBuffer)
        fun showIntermediatePlaces(buffer: AutocompletePredictionBuffer)
        fun showPolilines(steps: ArrayList<LatLng>)
        fun showSavedTrack(steps: ArrayList<LatLng>)
        fun runCar(steps: ArrayList<LatLng>)
        fun clearIntermediateText()
        fun showPin()
    }
}

