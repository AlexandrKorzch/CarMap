package com.alex.car.repo.db


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

//    /*ADVANTAGES*/
//    override fun saveAdvantage(advantage: Advantage): AdvantageRealm {
//        var savedAdvantage: AdvantageRealm = getRealmAdvantageFromAdvantage(advantage)
//        realmTransaction { savedAdvantage = it.copyToRealmOrUpdate(savedAdvantage) }
//        return savedAdvantage
//    }
//
//    override fun getAdvantages(): Flowable<List<AdvantageRealm>> {
//        return realm(false)
//                .map { it.where<AdvantageRealm>().findAll().sort("id", Sort.ASCENDING) }
//    }
//
//    /*BASKETS*/
//    override fun addBasket(basket: Basket) {
//        realmTransaction { it.insertOrUpdate(basket) }
//    }
//
//    override fun getAllBaskets(): Flowable<MutableList<Basket>> {
//        return realm(false).map { it.where<Basket>().findAll().toMutableList() }
//    }
//
//    override fun getGoodsCount(): Flowable<Int> {
//        return realm(true).map { it.where<Goods>().findAll().count() }
//    }
//
//    override fun getGoodsCountInBasket(basketId: Long): Flowable<Int> {
//        return realm(true)
//                .map { it.where<Basket>().equalTo("id", basketId).findFirst()?.goods?.size
//                        ?: 0 }
//    }
//
//    override fun getBasketByIdOrCreate(placeId: Long): Flowable<Basket> {
//        return realm(false)
//                .map {it.where<Basket>().equalTo("id", placeId).findFirst()
//                        ?: Basket(placeId)} }
//
//    override fun getBasketByIdOrFirst(placeId: Long): Flowable<Basket> {
//        return LocalRepository.realm(true)
//                .map {it.where<Basket>().equalTo("id", placeId).findFirst()
//                        ?: it.where<Basket>().findFirst()} }
//
//    /*FILTER*/
//    override fun updateFilter(filter: Filter) {
//        realmTransaction { it.insertOrUpdate(filter) }
//    }
//
//    override fun getFilter(): Flowable<Filter> {
//        return realm(false).map { it.where<Filter>().findFirst() ?: Filter() }
//    }
//
//    override fun removeType(id: Long) {
//        realmTransaction { it.where<TypeIdNamePair>().equalTo("id", id).findAll().deleteAllFromRealm() }
//    }
//
//    override fun removeKitchen(id: Long) {
//        realmTransaction { it.where<KitchenIdNamePair>().equalTo("id", id).findAll().deleteAllFromRealm() }
//    }
//
//    /*ORDER*/
//    override fun setNewOrder(order: OrderDb) {
//        realmTransaction { it.insertOrUpdate(order) }
//    }
//
//    override fun getOrderById(id: Long) : Flowable<OrderDb>{
//        return realm(false).map { it.where<OrderDb>().findFirst() }
//    }
}