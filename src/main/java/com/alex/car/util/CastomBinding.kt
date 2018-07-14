package com.alex.car.util

//import android.databinding.BindingAdapter
//import android.graphics.Bitmap
//import android.graphics.drawable.BitmapDrawable
//import android.os.Handler
//import android.support.v4.content.ContextCompat
//import android.support.v4.view.ViewPager
//import android.support.v7.widget.AppCompatImageView
//import android.support.v7.widget.AppCompatTextView
////import android.support.v7.widget.RecyclerView
//import android.view.View
//import com.bumptech.glide.Glide
//import com.bumptech.glide.load.engine.DiskCacheStrategy
//import com.bumptech.glide.request.RequestOptions
//import com.bumptech.glide.request.target.SimpleTarget
//import com.bumptech.glide.request.transition.Transition
//import net.vjet.myplace.App
//import net.vjet.myplace.R
//import net.vjet.myplace.repo.db.model.basket.Goods
//import net.vjet.myplace.repo.remote.model.Booking
//import net.vjet.myplace.repo.remote.model.ProdCategory
//import net.vjet.myplace.ui.place.adapters.PlacePagerAdapter
//import net.vjet.myplace.view.baseview.BaseAdapter
//import net.vjet.myplace.view.baseview.BaseTwoHolderAdapter
import android.databinding.BindingAdapter
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import com.google.android.gms.location.places.AutocompletePredictionBuffer
import java.text.SimpleDateFormat
import java.util.*
import android.widget.ArrayAdapter
import java.util.Arrays.asList


//@BindingAdapter("bind:stateChecked")
//fun setOrderPaymentTypeCheckedState(imageView: AppCompatImageView, checked: Boolean) {
//    val backgroundDrawableId: Int
//    var srcDrawableId = R.drawable.ic_cash_for_order_gray
//    val checkedId = imageView.id
//
//    if (checked) {
//        backgroundDrawableId = R.drawable.background_circle_border_green
//        when (checkedId) {
//            R.id.iv_wallet -> srcDrawableId = R.drawable.ic_cash_for_order_green
//            R.id.iv_card -> srcDrawableId = R.drawable.ic_cart_for_order_green
//            R.id.iv_cash -> srcDrawableId = R.drawable.ic_wallet_for_order_green
//        }
//    } else {
//        backgroundDrawableId = R.drawable.background_circle_border_gray
//        when (checkedId) {
//            R.id.iv_wallet -> srcDrawableId = R.drawable.ic_cash_for_order_gray
//            R.id.iv_card -> srcDrawableId = R.drawable.ic_cart_for_order_gray
//            R.id.iv_cash -> srcDrawableId = R.drawable.ic_wallet_for_order_grey
//        }
//    }
//    imageView.background = ContextCompat.getDrawable(imageView.context, backgroundDrawableId)
//    imageView.setImageResource(srcDrawableId)
//}
//


//@BindingAdapter("bind:places")



//
//@BindingAdapter("bind:twiceItems")
//fun setTwiceItems(recyclerView: RecyclerView, items: List<Any>) {
//    val adapter = recyclerView.adapter as? BaseTwoHolderAdapter<*, *, *>
//    adapter?.setItems(items)
//}
//
//@BindingAdapter("bind:info")
//fun setWalletInfoArrow(imageView: AppCompatImageView, open: Boolean) {
//    val resId = if (open) R.drawable.ic_arrow_detailed_question else R.drawable.ic_arrow_rolled_question
//    imageView.setImageResource(resId)
//}
//
//@BindingAdapter("bind:loadImage")
//fun loadImage(imageView: AppCompatImageView, url: String?) {
//    if (url != null && url != "") {
//        Glide.with(imageView)
//                .load(url)
//                .into(imageView)
//        imageView.visibility = View.VISIBLE
//    } else {
//        imageView.visibility = View.GONE
//    }
//}
//
//@BindingAdapter("bind:loadImageNoGone")
//fun loadImageNoGone(imageView: AppCompatImageView, url: String?) {
//    val requestOptions = RequestOptions()
//    requestOptions.diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//    requestOptions.dontTransform()
//    Glide.with(imageView)
//            .setDefaultRequestOptions(requestOptions)
//            .load(url)
//            .into(imageView)
//}
//
//@BindingAdapter("bind:loadImageCircle")
//fun loadImageCircle(imageView: AppCompatImageView, url: String?) {
//    Glide.with(imageView)
//            .load(url)
//            .apply(RequestOptions.circleCropTransform())
//            .into(imageView)
//}
//
//@BindingAdapter("bind:image")
//fun setImage(imageView: AppCompatImageView, id: Int) {
//    imageView.setImageResource(id)
//}
//
//@BindingAdapter("bind:homeImage")
//fun setImage(imageView: AppCompatImageView, edited: Boolean) {
//    imageView.setImageResource(
//            if (edited) R.drawable.ic_home_mini_filter_white
//            else R.drawable.ic_home_mini_filter_gray
//    )
//}
//
//@BindingAdapter("bind:favoriteImage")
//fun setFavoriteImage(imageView: AppCompatImageView, favorite: Boolean) {
//    imageView.setImageResource(
//            if (favorite) R.drawable.ic_heart_active
//            else R.drawable.ic_heart
//    )
//}
//
//@BindingAdapter("bind:hasBooking", "bind:isBooking")
//fun setBookingImage(imageView: AppCompatImageView, hasBooking: Boolean, isBooking: Boolean) {
//    setVisible(imageView, hasBooking)
//    if (hasBooking) {
//        if (isBooking) {
//            imageView.setBackgroundResource(R.drawable.background_button_circle_green)
//            imageView.setImageResource(R.drawable.ic_booking_white)
//        } else {
//            imageView.setBackgroundResource(R.drawable.background_button_circle_white)
//            imageView.setImageResource(R.drawable.ic_booking)
//        }
//    }
//}
//
//@BindingAdapter("bind:hasDelivery", "bind:isDelivery")
//fun setDeliveryImage(imageView: AppCompatImageView, hasDelivery: Boolean, isDelivery: Boolean) {
//    setVisible(imageView, hasDelivery)
//    if (hasDelivery) {
//        if (isDelivery) {
//            imageView.setBackgroundResource(R.drawable.background_button_circle_green)
//            imageView.setImageResource(R.drawable.ic_delivery_active)
//        } else {
//            imageView.setBackgroundResource(R.drawable.background_button_circle_white)
//            imageView.setImageResource(R.drawable.ic_delivery)
//        }
//    }
//}
//
//private fun setVisible(imageView: AppCompatImageView, visible: Boolean) {
//    imageView.isClickable = visible
//    when (visible) {
//        true -> imageView.visibility = View.VISIBLE
//        false -> imageView.visibility = View.INVISIBLE
//    }
//}
//
//@BindingAdapter("bind:bookingColor")
//fun setBookingTimeLateTextColor(textView: AppCompatTextView, selected: Boolean) {
//    textView.setTextColor(
//            if (selected) ContextCompat.getColor(textView.context, R.color.colorGreen)
//            else ContextCompat.getColor(textView.context, R.color.colorGray)
//    )
//}
//
//@BindingAdapter("bind:gallery")
//fun showGallery(viewPager: ViewPager, items: MutableList<String>) {
//    val adapter = viewPager.adapter  as? PlacePagerAdapter
//    adapter?.list = items
//    adapter?.notifyDataSetChanged()
//}
//
//@BindingAdapter("bind:pressed", "bind:prod_category")
//fun setMenuBtPressed(textView: AppCompatTextView, pressed: Boolean, category: ProdCategory?) {
//    if (category != null) {
//        textView.text = category.title
//        if (pressed) {
//            textView.setBackgroundResource(R.drawable.background_button_menu_green)
//            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.colorWhite))
//        } else {
//            textView.setBackgroundResource(R.drawable.background_button_menu_gray)
//            textView.setTextColor(ContextCompat.getColor(textView.context, R.color.colorBlack))
//        }
//        Handler().postDelayed({
//            //todo need fix - add tree observable?
//            Glide.with(textView).asBitmap()
//                    .load(category.image)
//                    .into(object : SimpleTarget<Bitmap>() {
//                        override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
//                            val drawable = BitmapDrawable(App.applicationContext().resources, resource)
//                            drawable.setBounds(0, 0, textView.height / 2, textView.height / 2)
//                            textView.setCompoundDrawables(drawable, null, null, null)
//                        }
//                    })
//        }, 300)
//    }
//}
//
//@BindingAdapter("bind:date")
//fun parceDate(textView: AppCompatTextView, createdAt: Long) {
//    val date = Date(createdAt * 1000)
//    val dateFormat = SimpleDateFormat("dd MM yyyy", Locale.US)
//    textView.text = dateFormat.format(date)
//}
//
//@BindingAdapter("bind:start", "bind:end")
//fun parcDateFromTo(textView: AppCompatTextView, start: Long, end: Long) {
//    val dateFormat = SimpleDateFormat("dd.MM", Locale.US)
//    val startDate = Date(start * 1000)
//    val endDate = Date(end * 1000)
//    val startStr = dateFormat.format(startDate)
//    val endStr = dateFormat.format(endDate)
//    textView.text = "$startStr - $endStr"
//}
//
//@BindingAdapter("bind:bookingWhen")
//fun setWhenBooking(textView: AppCompatTextView, booking: Booking) {
//    booking.bookingTime?.let {
//        val dateFormat = SimpleDateFormat("dd.MM.yy ${textView.context.getString(R.string.booking_in)} HH.mm", Locale.US)
//        val date = Date(it * 1000)
//        val formattedDate = dateFormat.format(date)
//        textView.text = ("${textView.context.getString(R.string.booking_ad_pref)} $formattedDate")
//    }
//}
//
//@BindingAdapter("bind:items_in_basket")
//fun setItemsInBasket(textView: AppCompatTextView, count: Int) {
//    textView.text = "($count ${textView.context.getString(R.string.count)})"
//}
//
//@BindingAdapter("bind:totalPrice")
//fun setTotalPrice(textView: AppCompatTextView, goods: List<Goods>) {
//    var totalPrice = 0F
//    goods.forEach { totalPrice += (it.price!! * it.count!!) }
//    textView.text = "${textView.context.getString(R.string.total)} $totalPrice ₸"
//}
//
//@BindingAdapter("bind:totalPriceBasket")
//fun setTotalPriceBasket(textView: AppCompatTextView, totalPrice: Float) {
//    textView.text = "${textView.context.getString(R.string.total)} $totalPrice ₸"
//}
//
//@BindingAdapter("bind:totalGoodsPrice")
//fun setPriceForGoods(textView: AppCompatTextView, goods: Goods) {
//    var totalPrice = 0F
//    totalPrice = goods.price!! * goods.count!!
//    textView.text = "$totalPrice ₸ (${goods.count} ${textView.context.getString(R.string.items)})"
//}
//
//@BindingAdapter("bind:createOrder")
//fun setCreateOrderText(textView: AppCompatTextView, totalPrice: Float) {
//    textView.text = "${textView.context.getString(R.string.create_order)} $totalPrice ₸"
//    if (totalPrice > 0) {
//        textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.colorGreen))
//        textView.isClickable = true
//    } else {
//        textView.setBackgroundColor(ContextCompat.getColor(textView.context, R.color.colorGray))
//        textView.isClickable = false
//    }
//}
//
//@BindingAdapter("bind:orderTotalPrice")
//fun setTotalPriceOrder(textView: AppCompatTextView, totalPrice: Float) {
//    textView.text = "$totalPrice ₸"
//}





