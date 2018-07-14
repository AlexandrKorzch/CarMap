package net.vjet.myplace.repo.db.model.basket

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Basket(
        @PrimaryKey var id: Long? = null,
        var goods: RealmList<Goods> = RealmList()
) : RealmObject(){

    var placeName: String? = null
    var placeImage: String? = null

    fun copy() : Basket{
        val goods: RealmList<Goods> = RealmList()
        this.goods.forEach {goods.add(it.copy()) }
        val basket = Basket(id, goods)
        basket.placeName = placeName
        basket.placeImage = placeImage
        return basket
    }

    override fun toString(): String {
        return "Basket(id=$id, goods=$goods, placeName=$placeName, " +
                "placeImage=$placeImage)"
    }
}