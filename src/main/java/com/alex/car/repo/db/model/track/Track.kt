package com.alex.car.repo.db.model.track

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Track(
        @PrimaryKey var id: Long? = null,
        var segments:  RealmList<Segment> = RealmList()
) : RealmObject()