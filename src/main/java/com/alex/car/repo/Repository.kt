package com.alex.car.repo

import android.arch.lifecycle.LifecycleObserver
import com.alex.car.repo.db.LocalDataSource
import com.alex.car.repo.remote.RemoteDataSource


class Repository(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource
) : LifecycleObserver {


//    /*SHARED PREFERENCES*/
//    fun setDeviceId(deviceId: String?) = spDataSource.setDeviceId(deviceId)
//
//    fun setAuthKey(authKey: String?) = authKey?.let { spDataSource.setAuthKey(authKey) }
//    fun setTermsChecked(checked: Boolean) = spDataSource.setTermsChecked(checked)
//    fun getTermsChecked(): Boolean? = spDataSource.getTermsChecked()
//    fun setSignIn(signIn: Boolean) = spDataSource.setSignIn(signIn)
//    fun isSignIn(): Boolean = spDataSource.isSignIn()
//    fun setFirstEnter(signIn: Boolean) = spDataSource.setFirstEnter(signIn)
//    fun isSFirstEnter(): Boolean = spDataSource.isSFirstEnter()
//
//
//    /*REMOTE*/
//    fun getAdvantages(storagePermission: Boolean): Flowable<AdvantageRealm> {
//        return remoteDataSource.getAdvantages()
//                .flatMap { Flowable.fromIterable(it.data) }
//                .flatMap({ loadPhoto(it.image, it.id, storagePermission) },
//                        { advantage, photo ->
//                            advantage.photo = photo
//                            advantage
//                        })
//                .flatMap { Flowable.just(localDataSource.saveAdvantage(it)) }
//    }
//
//    private fun loadPhoto(url: String?, advantageId: String?, storagePermission: Boolean): Flowable<String> {
//        if (storagePermission) {
//            return remoteDataSource.loadPhoto(url)
//                    .map { storageDataSource.writeImageFileToDisk(it, advantageId) }
//        } else {
//            return Flowable.just("")
//        }
//    }
//
//    fun signIn(body: SignInRequestBody): Flowable<BaseDto<List<SignIn>>> {
//        body.pushId = spDataSource.getPushToken()
//        body.deviceId = spDataSource.getDeviceId()
//        val token = md5("${body.phone}$SOLT")
//        return remoteDataSource.signIn(token, body)
//    }
//
//    fun getFilteredPlaces(filter: GetFilteredPlacesBody): Flowable<List<Place>>
//            = remoteDataSource.getFilteredPlaces(filter)
//            .map { it.data }
//
//
//    fun setFavorite(placeId: Long): Flowable<BaseDto<Any>> = remoteDataSource.setFavorite(FavoriteBody(placeId))
//
//    fun getPlace(placeId: Long): Flowable<PlaceExtended> = remoteDataSource.getPlace(GetPlaceBody(placeId))
//            .map { it.data?.first() }
//
//    fun getFags(): Flowable<Fags> = remoteDataSource.getFags()
//            .map { it.data }
//
//    fun getCities(): Flowable<List<City>> = remoteDataSource.getCities()
//            .map { it.data }
//
//    fun getPlacesTypes(): Flowable<List<BaseCategory>> = remoteDataSource.getPlacesTypes()
//            .map { it.data }
//
//    fun getKitchens(): Flowable<List<BaseCategory>> = remoteDataSource.getKitchens()
//            .map { it.data }
//
//    fun getSorts(): Flowable<List<BaseCategory>> = remoteDataSource.getSorts()
//            .map { it.data }
//
//    fun getSupportCategories(): Flowable<Support> = remoteDataSource.getSupportCategories()
//            .map { it.data }
//
//    fun sendSupportMessage(message: SendToSupportBody): Flowable<BaseDto<Any>> = remoteDataSource.sendSupportMessage(message)
//
//    fun getAbuseCategories(): Flowable<List<BaseCategory>> = remoteDataSource.getAbuseCategories()
//            .map { it.data }
//
//    fun sendAbuse(abuse: AbuseBody): Flowable<BaseDto<Any>> = remoteDataSource.sendAbuse(abuse)
//
//    fun getWalletInfo(): Flowable<List<WalletInfo>> = remoteDataSource.getWalletInfo()
//            .map { it.data }
//
//    fun getBookings(getBooking: GetBookingsBody): Flowable<List<Booking>> = remoteDataSource.getBookings(getBooking)
//            .map { it.data }
//
//    fun getProductCategories(getCategories: GetProductsCategoriesBody): Flowable<List<ProdCategory>> = remoteDataSource.getProductCategories(getCategories)
//            .map { it.data }
//
//    fun getProducts(getProducts: GetProductsBody): Flowable<List<Product>> = remoteDataSource.getProducts(getProducts)
//            .map { it.data }
//
//    fun getProduct(getProduct: GetProductBody): Flowable<Product> = remoteDataSource.getProduct(getProduct)
//            .map { it.data?.first() }
//
//    fun getComments(getComments: GetCommentsBody): Flowable<List<Comment>> = remoteDataSource.getComments(getComments)
//            .map { it.data }
//
//    fun addComment(addCommentBody: AddCommentBody): Flowable<Any> = remoteDataSource.addComment(addCommentBody)
//            .map { it.data }
//
//    fun getTransactions(getTransactionsBody: GetTransactionsBody): Flowable<Any> = remoteDataSource.getTransactions(getTransactionsBody)
//            .map { it.data }
//
//    fun getProfile(): Flowable<Profile> = remoteDataSource.getProfile()
//            .map { it.data?.first() }
//
//    fun editProfile(editProfile: EditProfileBody): Flowable<Any> = remoteDataSource.editProfile(editProfile)
//            .map { it.data }
//
//    fun updateProfileImage(image: MultipartBody.Part): Flowable<BaseDto<Any>> = remoteDataSource.updateProfileImage(ACCEPT_SLASH, image)
//
//    fun getActions(getActions: GetActionsBody): Flowable<List<ActionPojo>> = remoteDataSource.getActions(getActions)
//            .map { it.data }
//
//    fun getAction(promotionId: Long): Flowable<ActionPojo> = remoteDataSource.getAction(GetActionBody(promotionId))
//            .map { it.data?.first() }
//
//    fun getHalls(placeId: Long): Flowable<List<Hall>> = remoteDataSource.getHalls(GetHallsBody(placeId))
//            .map { it.data }
//
//    fun addBooking(addBooking: AddBookingBody): Flowable<BaseDto<Any>> = remoteDataSource.addBooking(addBooking)
//
//    fun getCurrent(): Flowable<CurrentBookingsAndOrders>
//            = remoteDataSource.getCurrent()
//            .map { it.data }
//
//    fun addProductToCarts(body: AddProductBody): Flowable<Any>
//            = remoteDataSource.addProductToCarts(body)
//            .map { it.data }
//
//    fun getCarts(placeId: Long): Flowable<Cart>
//            = remoteDataSource.getCarts()
//            .map {getCart(it, placeId)}
//
//
//
//    //todo in work
//    fun addOrder(body: AddOrderBody): Flowable<Any>
//            = remoteDataSource.addOrder(body)
//            .map {it.data}
//
//    fun getBalanceRecharge(body: BalanceRechargeBody): Flowable<Any>
//            = remoteDataSource.getBalanceRecharge(body)
//            .map { it.data }
//
//    fun getNotification(body: NotificationBody): Flowable<Any>
//            = remoteDataSource.getNotification(body)
//            .map { it.data }
//
//    fun getNotifications(body: NotificationsBody): Flowable<Any>
//            = remoteDataSource.getNotifications(body)
//            .map { it.data }
//
//    fun bookingAnswer(body: BookingAnswerBody): Flowable<Any>
//            = remoteDataSource.bookingAnswer(body)
//            .map { it.data }
//
//    fun editProfile(body: NotificationReadBody): Flowable<Any>
//            = remoteDataSource.editProfile(body)
//            .map { it.data }
//
//    fun deleteTransaction(body: DeleteTransactionBody): Flowable<Any>
//            = remoteDataSource.deleteTransaction(body)
//            .map { it.data }
//
//    fun deleteBooking(body: DeleteBookingBody): Flowable<Any>
//            = remoteDataSource.deleteBooking(body)
//            .map { it.data }
//
//
//
//
//
//
//
//
//    /*LOCAL*/
//    fun getAdvantages(): Flowable<List<AdvantageRealm>> {
//        return localDataSource.getAdvantages()
//    }
//
//    fun getGoodsCount(): Flowable<Int> {
//        return localDataSource.getGoodsCount()
//    }
//
//    fun getGoodsCountInBasket(basketId: Long): Flowable<Int> {
//        return localDataSource.getGoodsCountInBasket(basketId)
//    }
//
//    fun addBasket(basket: Basket) {
//        localDataSource.addBasket(basket)
//    }
//
//    fun getBasketByIdOrCreate(placeId: Long): Flowable<Basket> {
//        return localDataSource.getBasketByIdOrCreate(placeId)
//    }
//
//    fun getBasketByIdOrFirst(placeId: Long): Flowable<Basket> {
//        return localDataSource.getBasketByIdOrFirst(placeId)
//    }
//
//    fun getAllBaskets(): Flowable<MutableList<Basket>> {
//        return localDataSource.getAllBaskets()
//    }
//
//    fun updateFilter(filter: Filter) = localDataSource.updateFilter(filter)
//
//    fun getFilter(): Flowable<Filter> = localDataSource.getFilter()
//
//    fun removeType(id: Long) = localDataSource.removeType(id)
//
//    fun removeKitchen(id: Long) = localDataSource.removeKitchen(id)
//
//    fun getPages(): Flowable<String> = remoteDataSource.getPages()
//            .map { it.data?.first()?.url }
//
//    /*ORDER*/
//    fun setNewOrder(order: OrderDb) {
//        localDataSource.setNewOrder(order)
//    }
//
//    fun getOrderById(id: Long) : Flowable<OrderDb>{
//        return localDataSource.getOrderById(id)
//    }


    companion object {
        private var INSTANCE: Repository? = null
        @JvmStatic
        fun getInstance(remoteDataSource: RemoteDataSource,
                        localDataSource: LocalDataSource) =
                INSTANCE ?: synchronized(Repository::class.java) {
                    INSTANCE ?: Repository(remoteDataSource, localDataSource)
                            .also { INSTANCE = it }
                }

        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}


//    private val disposables = CompositeDisposable()

//    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
//    fun clear() {
//        logWithTAG("Repository clear()")
//        disposables.dispose()
//        disposables.clear()
//    }