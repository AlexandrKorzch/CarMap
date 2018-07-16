package com.alex.car.repo.remote.api


object ApiSettings {

    /*server*/
    private const val SCHEME = "https://"
    private const val HOSTNAME = "maps.googleapis.com/"
    const val SERVER = SCHEME + HOSTNAME

    const val API_KEY = "AIzaSyBmbx43zpCPI-fsKX9inJIp_hVrgt9407A"

    /*methods*/
    const val ROUT = "maps/api/directions/json"

    /*constants*/
    const val ORIGIN = "origin"
    const val DESTINATION = "destination"
    const val KEY = "key"
}