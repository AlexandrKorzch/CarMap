package com.alex.car.flow.map

import android.app.Application
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.databinding.ObservableBoolean
import android.os.Bundle
import android.util.Log
import com.alex.car.base.BaseHandler
import com.alex.car.base.BaseViewModel
import com.alex.car.repo.Repository
import com.alex.car.util.END
import com.alex.car.util.INTERMEDIATE
import com.alex.car.util.START
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.places.AutocompletePredictionBuffer
import com.google.android.gms.location.places.Places
import com.google.android.gms.maps.model.LatLng


class MapsViewModel(context: Application, repository: Repository)
    : BaseViewModel<MapsViewModel.Handler>(context, repository),
        LifecycleObserver, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    val MAX_NEXT = 3

    var startPlace: LatLng? = null
    var endPlace: LatLng? = null
    var intermediatePlaces = ArrayList<LatLng>()

    val connected = ObservableBoolean(false)
    val intermediateBt = ObservableBoolean(false)
    val addIntermediate = ObservableBoolean(false)

    private lateinit var googleApiClient: GoogleApiClient

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onResume() {
        initGoogleApiClient()
    }

    fun startPlaceTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showStartPlaces(it) }
    }

    fun nextPlaceTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showNextPlaces(it) }
    }

    fun intermediateTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
        getPlacesByText(s.toString()) { getHandler().showIntermediatePlaces(it) }
    }

    fun addIntermediateClick(){
        addIntermediate.set(true)
        getHandler().clearIntermediateText()
    }

    fun startClick(){
        Log.d("TAG", "startClick")
    }

    private fun getPlacesByText(query: String, func: (buf: AutocompletePredictionBuffer) -> Unit) {
        if (query.length >= 3) {
            Places.GeoDataApi
                    .getAutocompletePredictions(googleApiClient, query, null, null)
                    .setResultCallback {func(it)}
        }
    }

    fun getCoordinatesByPlaceId(placeId: String?, pointType: Int) {
        Places.GeoDataApi.getPlaceById(googleApiClient, placeId).setResultCallback {
            if (it.status.isSuccess) {
                val place = it.first()
                when(pointType){
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

    private fun checkIntermediateAllow() {
        if(startPlace != null && endPlace != null){
            intermediateBt.set(true)
        }
    }

    private fun addToIntermediate(latLng: LatLng) {
        if(intermediatePlaces.size < MAX_NEXT)intermediatePlaces.add(latLng)
        else showError("No more then 5")
    }

    private fun initGoogleApiClient() {
        googleApiClient = GoogleApiClient.Builder(getApplication())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .addApi(Places.GEO_DATA_API)
                .build()

        googleApiClient.connect()
    }

    override fun onConnected(p0: Bundle?) = connected.set(true)

    override fun onConnectionSuspended(p0: Int) {
        connected.set(false)
        showError("Connection Suspended")
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        connected.set(false)
        showError("Connection Failed")
    }


    interface Handler : BaseHandler {
        fun showStartPlaces(buffer: AutocompletePredictionBuffer)
        fun showNextPlaces(buffer: AutocompletePredictionBuffer)
        fun showIntermediatePlaces(buffer: AutocompletePredictionBuffer)
        fun clearIntermediateText()
        fun showPin()
    }
}