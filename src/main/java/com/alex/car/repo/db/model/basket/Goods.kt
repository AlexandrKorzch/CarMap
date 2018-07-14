package net.vjet.myplace.repo.db.model.basket

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class Goods(
        @PrimaryKey
        var id: Long? = null,
        var name: String? = null,
        var price: Float? = null,
        var category: Int? = null,
        var image: String? = null,
        var count : Int? = null
) : RealmObject(){

        fun copy() : Goods{
                return Goods(id, name, price, category, image, count)
        }

        override fun toString(): String {
                return "Goods(id=$id, name='$name', price=$price, category=$category, count=$count)"
        }
}