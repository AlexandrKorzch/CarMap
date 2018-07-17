package com.alex.car.repo.db.model.track

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Segment(
        @PrimaryKey var id: Long? = null,
        var lat: Double? = null,
        var lon: Double? = null) : RealmObject()