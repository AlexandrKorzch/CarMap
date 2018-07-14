package com.alex.car.repo.remote

import com.alex.car.repo.remote.api.Api
import com.alex.car.repo.remote.api.RetrofitCreator.initRetrofit
import com.alex.car.repo.remote.api.RetrofitCreator.initServices


object RemoteRepository : RemoteDataSource {

    lateinit var api: Api

    init {
        initServices(initRetrofit())
    }

//    override fun getAdvantages(): Flowable<BaseDto<List<Advantage>>>
//            = api.getAdvantages()
//            .filter(ApiResponseFilter())
//
//    override fun loadPhoto(url: String?): Flowable<ResponseBody>
//            = api.loadPhoto(url)
//
//    override fun signIn(token: String, body: SignInRequestBody): Flowable<BaseDto<List<SignIn>>>
//            = api.signIn(token, body)
//            .filter(ApiResponseFilter())
//
//    override fun getFilteredPlaces(body: GetFilteredPlacesBody): Flowable<BaseDto<List<Place>>>
//            = api.getFilteredPlaces(body)
//            .filter(ApiResponseFilter())
//
//    override fun setFavorite(placeId: FavoriteBody): Flowable<BaseDto<Any>>
//            = api.setFavorite(placeId)
//            .filter(ApiResponseFilter())
//
//    override fun getPlace(placeId: GetPlaceBody): Flowable<BaseDto<List<PlaceExtended>>>
//            = api.getPlace(placeId)
//            .filter(ApiResponseFilter())
//
//    override fun getFags(): Flowable<BaseDto<Fags>>
//            = api.getFags()
//            .filter(ApiResponseFilter())
//
//    override fun getCities(): Flowable<BaseDto<List<City>>>
//            = api.getCities()
//            .filter(ApiResponseFilter())
//
//    override fun getPlacesTypes(): Flowable<BaseDto<List<BaseCategory>>>
//            = api.getPlacesTypes()
//            .filter(ApiResponseFilter())
//
//    override fun getKitchens(): Flowable<BaseDto<List<BaseCategory>>>
//            = api.getKitchens()
//            .filter(ApiResponseFilter())
//
//    override fun getSorts(): Flowable<BaseDto<List<BaseCategory>>>
//            = api.getSorts()
//            .filter(ApiResponseFilter())
//
//    override fun getSupportCategories(): Flowable<BaseDto<Support>>
//            = api.getSupportCategories()
//            .filter(ApiResponseFilter())
//
//    override fun sendSupportMessage(message: SendToSupportBody): Flowable<BaseDto<Any>>
//            = api.sendSupportMessage(message)
//            .filter(ApiResponseFilter())
//
//    override fun getAbuseCategories(): Flowable<BaseDto<List<BaseCategory>>>
//            = api.getAbuseCategories()
//            .filter(ApiResponseFilter())
//
//    override fun sendAbuse(abuse: AbuseBody): Flowable<BaseDto<Any>>
//            = api.sendAbuse(abuse)
//            .filter(ApiResponseFilter())
//
//    override fun getWalletInfo(): Flowable<BaseDto<List<WalletInfo>>>
//            = api.getWalletInfo()
//            .filter(ApiResponseFilter())
//
//    override fun getBookings(getBooking: GetBookingsBody): Flowable<BaseDto<List<Booking>>>
//            = api.getBookings(getBooking)
//            .filter(ApiResponseFilter())
//
//    override fun getProductCategories(getCategories: GetProductsCategoriesBody): Flowable<BaseDto<List<ProdCategory>>>
//            = api.getProductCategories(getCategories)
//            .filter(ApiResponseFilter())
//
//    override fun getProducts(getProducts: GetProductsBody): Flowable<BaseDto<List<Product>>>
//            = api.getProducts(getProducts)
//            .filter(ApiResponseFilter())
//
//    override fun getProduct(getProduct: GetProductBody): Flowable<BaseDto<List<Product>>>
//            = api.getProduct(getProduct)
//            .filter(ApiResponseFilter())
//
//    override fun getComments(getComments: GetCommentsBody): Flowable<BaseDto<List<Comment>>>
//            = api.getComments(getComments)
//            .filter(ApiResponseFilter())
//
//    override fun addComment(addCommentBody: AddCommentBody): Flowable<BaseDto<Any>>
//            = api.addComment(addCommentBody)
//            .filter(ApiResponseFilter())
//
//    override fun getTransactions(getTransactionsBody: GetTransactionsBody): Flowable<BaseDto<Any>>
//            = api.getTransactions(getTransactionsBody)
//            .filter(ApiResponseFilter())
//
//    override fun getProfile(): Flowable<BaseDto<List<Profile>>>
//            = api.getProfile()
//            .filter(ApiResponseFilter())
//
//    override fun editProfile(editProfile: EditProfileBody): Flowable<BaseDto<Any>>
//            = api.editProfile(editProfile)
//            .filter(ApiResponseFilter())
//
//    override fun updateProfileImage(accept: String, image: MultipartBody.Part): Flowable<BaseDto<Any>>
//            = api.updateProfileImage(accept, image)
//            .filter(ApiResponseFilter())
//
//    override fun getActions(getActions: GetActionsBody): Flowable<BaseDto<List<ActionPojo>>>
//            = api.getActions(getActions)
//            .filter(ApiResponseFilter())
//
//    override fun getAction(getAction: GetActionBody): Flowable<BaseDto<List<ActionPojo>>>
//            = api.getAction(getAction)
//            .filter(ApiResponseFilter())
//
//    override fun getPages(): Flowable<BaseDto<List<Page>>>
//            = api.getPages()
//            .filter(ApiResponseFilter())
//
//    override fun getHalls(getHallsBody: GetHallsBody): Flowable<BaseDto<List<Hall>>>
//            = api.getHalls(getHallsBody)
//            .filter(ApiResponseFilter())
//
//    override fun addBooking(addBooking: AddBookingBody): Flowable<BaseDto<Any>>
//            = api.addBooking(addBooking)
//            .filter(ApiResponseFilter())
//
//    override fun getCurrent(): Flowable<BaseDto<CurrentBookingsAndOrders>>
//            = api.getCurrent()
//            .filter(ApiResponseFilter())
//
//
//
//
//    override fun addOrder(body: AddOrderBody): Flowable<BaseDto<Any>>
//            = api.addOrder(body)
//            .filter(ApiResponseFilter())
//
//    override fun addProductToCarts(body: AddProductBody): Flowable<BaseDto<Any>>
//            = api.addProductToCarts(body)
//            .filter(ApiResponseFilter())
//
//    override fun getCarts(): Flowable<BaseDto<List<Cart>>>
//            = api.getCarts()
//            .filter(ApiResponseFilter())
//
//    override fun getBalanceRecharge(body: BalanceRechargeBody): Flowable<BaseDto<Any>>
//            = api.getBalanceRecharge(body)
//            .filter(ApiResponseFilter())
//
//    override fun getNotification(body: NotificationBody): Flowable<BaseDto<Any>>
//            = api.getNotification(body)
//            .filter(ApiResponseFilter())
//
//    override fun getNotifications(body: NotificationsBody): Flowable<BaseDto<Any>>
//            = api.getNotifications(body)
//            .filter(ApiResponseFilter())
//
//    override fun bookingAnswer(body: BookingAnswerBody): Flowable<BaseDto<Any>>
//            = api.bookingAnswer(body)
//            .filter(ApiResponseFilter())
//
//    override fun editProfile(body: NotificationReadBody): Flowable<BaseDto<Any>>
//            = api.editProfile(body)
//            .filter(ApiResponseFilter())
//
//    override fun deleteTransaction(body: DeleteTransactionBody): Flowable<BaseDto<Any>>
//            = api.deleteTransaction(body)
//            .filter(ApiResponseFilter())
//
//    override fun deleteBooking(body: DeleteBookingBody): Flowable<BaseDto<Any>>
//            = api.deleteBooking(body)
//            .filter(ApiResponseFilter())
}