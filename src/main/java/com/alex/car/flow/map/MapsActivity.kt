package com.alex.car.flow.map

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import com.alex.car.R
import com.alex.car.base.BaseActivity
import com.alex.car.databinding.ActivityMapsBinding
import com.alex.car.extantions.toast
import com.alex.car.util.END
import com.alex.car.util.INTERMEDIATE
import com.alex.car.util.START
import com.google.android.gms.location.places.AutocompletePredictionBuffer
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*


class MapsActivity : BaseActivity<ActivityMapsBinding, MapsViewModel>(), OnMapReadyCallback {

    override fun layoutResId() = R.layout.activity_maps

    override fun viewModelClass() = MapsViewModel::class.java

    private var map: GoogleMap? = null
    private val markerList = ArrayList<Marker>()
    private var car: Marker? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initMap()
    }

    //map
    private fun initMap() {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
    }

    private fun showPins() {
        clearMap()
        viewModel().startPlace?.let {
            val startMarker = map?.addMarker(markerOptions(it))
            markerList.add(startMarker!!)
        }
        viewModel().endPlace?.let {
            val endMarker = map?.addMarker(markerOptions(it))
            markerList.add(endMarker!!)
        }
        viewModel().intermediatePlaces.forEach {
            val nextMarker = map?.addMarker(markerOptions(it))
            markerList.add(nextMarker!!)
        }
        moveCamera()
    }

    private fun showReseivedTrack(steps: ArrayList<LatLng>) {
        map?.addPolyline(PolylineOptions().addAll(steps))
    }

    private fun showSavedTrack(steps: ArrayList<LatLng>) {
        clearMap()
        map?.addPolyline(PolylineOptions()
                .color(ContextCompat.getColor(this,R.color.colorPrimaryDark))
                .addAll(steps))
    }

    private fun moveCamera() {
        if (markerList.isNotEmpty()) {
            val builder = LatLngBounds.Builder()
            markerList.forEach { builder.include(it.position) }
            val bounds = builder.build()
            map?.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 400))
        }
    }

    private fun clearMap() {
        map?.clear()
        markerList.clear()
    }

    private fun markerOptions(latLng: LatLng) =
            MarkerOptions()
                    .position(latLng)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_unselected_pin_for_map))

    private fun runCar(steps: ArrayList<LatLng>) {
        val options = MarkerOptions()
                .position(steps.first())
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.car))
        car = map?.addMarker(options)
        Thread(Runnable {
            steps.forEach {
                runOnUiThread { car?.position = it }
                Thread.sleep(100)
            }
            runOnUiThread {
                toast("Save")
                viewModel().saveRoute(steps)
            }
        }).start()
    }


    //place chooser
    fun showPlaces(autoCompleteTextView: AutoCompleteTextView, buffer: AutocompletePredictionBuffer?) {
        buffer?.let {
            val list = ArrayList<String>()
            it.forEach { list.add(it.getPrimaryText(null).toString()) }
            val arrayAdapter = ArrayAdapter<String>(autoCompleteTextView.context,
                    android.R.layout.select_dialog_item, list)
            autoCompleteTextView.setAdapter(arrayAdapter)
            initPlaceItemClick(autoCompleteTextView, list, buffer)
        }
    }

    private fun initPlaceItemClick(
            autoCompleteTextView: AutoCompleteTextView,
            list: ArrayList<String>, buffer: AutocompletePredictionBuffer) {
        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->
            val selected = parent?.getItemAtPosition(position) as String
            val pos = list.indexOf(selected)
            val pointType = when (autoCompleteTextView.id) {
                R.id.et_start -> START
                R.id.et_end -> END
                R.id.et_interMediate -> INTERMEDIATE
                else -> -1
            }
            viewModel().getCoordinatesByPlaceId(buffer[pos].placeId, pointType)
        }
    }

    private fun clearIntermediateText() {
        binding.etInterMediate.setText("")
    }

    //viewModel callBack
    override fun getViewModelHandler() = object : MapsViewModel.Handler {
        override fun showPin()
                = this@MapsActivity.showPins()
        override fun runCar(steps: ArrayList<LatLng>)
                = this@MapsActivity.runCar(steps)
        override fun clearIntermediateText()
                = this@MapsActivity.clearIntermediateText()
        override fun showSavedTrack(steps: ArrayList<LatLng>)
                = this@MapsActivity.showSavedTrack(steps)
        override fun showPolilines(steps: ArrayList<LatLng>)
                = this@MapsActivity.showReseivedTrack(steps)
        override fun showNextPlaces(buffer: AutocompletePredictionBuffer)
                = this@MapsActivity.showPlaces(binding.etEnd, buffer)
        override fun showStartPlaces(buffer: AutocompletePredictionBuffer)
                = this@MapsActivity.showPlaces(binding.etStart, buffer)
        override fun showIntermediatePlaces(buffer: AutocompletePredictionBuffer)
                = this@MapsActivity.showPlaces(binding.etInterMediate, buffer)
    }

}
