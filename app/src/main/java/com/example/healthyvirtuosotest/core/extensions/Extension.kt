package com.example.healthyvirtuosotest.core.extensions

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.healthyvirtuosotest.Constants.MIN_TIME_BETWEEN_CLICK
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.core.abstraction.adapters.CollectionAdapter
import com.example.healthyvirtuosotest.core.abstraction.adapters.Holder
import com.example.healthyvirtuosotest.core.enums.ErrorCode
import com.example.healthyvirtuosotest.core.exception.model.ErrorApi
import com.example.healthyvirtuosotest.core.view.dialog.BaseDialogFragment
import com.example.healthyvirtuosotest.core.view.toolbar.AppBarLayoutBehavior
import com.google.android.material.appbar.AppBarLayout
import com.google.gson.Gson
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

fun ifElseCondition(
    condition: () -> Boolean,
    blockTrue: () -> Unit,
    blockFalse: () -> Unit
) {
    if (condition.invoke()) {
        blockTrue.invoke()
    } else {
        blockFalse.invoke()
    }
}

fun MutableList<BigDecimal>.toMutableInt(): MutableList<Int> {
    val data = arrayListOf<Int>()
    for (item in this) {
        data.add(item.toInt())
    }
    return data
}

fun View.toggle() {
    if (this.visibility == View.VISIBLE) {
        this.gone()
    } else {
        this.visible()
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.disable() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        background.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                ContextCompat.getColor(
                    this.context,
                    R.color.color_gray_DFDFDF
                ), BlendModeCompat.SRC_IN
            )
    } else {
        background.setColorFilter(
            ContextCompat.getColor(this.context, R.color.color_gray_DFDFDF),
            PorterDuff.Mode.SRC_IN
        )
    }
    isEnabled = false
}

fun View.enable() {
    background.colorFilter = null
    isEnabled = true
}

fun View.setSingleOnClickListener(listener: () -> Unit) {
    var lastClickTime = 0L
    this.setOnClickListener {
        if (SystemClock.elapsedRealtime() - lastClickTime < MIN_TIME_BETWEEN_CLICK) {
            return@setOnClickListener
        }
        lastClickTime = SystemClock.elapsedRealtime()
        listener.invoke()
    }
}

fun View.dpToPixel(dp: Int, density: Float) = (dp * density).toInt()
fun View.pixelToDp(pixel: Int, density: Float) = (pixel / density).toInt()


fun AppBarLayout.isEnable(enable: Boolean = true) {

    val coordinator = (this.layoutParams as CoordinatorLayout.LayoutParams)

    if (coordinator.behavior is AppBarLayoutBehavior) {
        (coordinator.behavior as AppBarLayoutBehavior).isEnabled = enable
    }
}

fun AppBarLayout.isExpanded(): Boolean {
    val behavior = (this.layoutParams as CoordinatorLayout.LayoutParams).behavior
    return if (behavior is AppBarLayout.Behavior) behavior.topAndBottomOffset == 0 else false
}


fun AppCompatImageView.loadImage(context: Context, url: String, view: View? = null) {

    val image = this

    Glide.with(context)
        .load(url)
        .listener(object : RequestListener<Drawable> {
            override fun onLoadFailed(
                e: GlideException?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                isFirstResource: Boolean
            ): Boolean {
                return false
            }

            override fun onResourceReady(
                resource: Drawable?,
                model: Any?,
                target: com.bumptech.glide.request.target.Target<Drawable>?,
                dataSource: DataSource?,
                isFirstResource: Boolean
            ): Boolean {
                image.setImageDrawable(resource)
                image.clipToOutline = true
                view?.gone()
                return true
            }

        })
        .into(image)
}

fun SearchView.onCleanLister(listener: View.OnClickListener) {
    val id: Int = this.context.resources.getIdentifier("android:id/search_close_btn", null, null)
    val image = findViewById<ImageView>(id)
    image.setOnClickListener {
        listener.onClick(this)
    }
}

fun SearchView.changeHint(typeface: Typeface) {
    val id: Int = this.context
        .resources
        .getIdentifier("android:id/search_src_text", null, null)
    val textView = this.findViewById(id) as TextView
    textView.typeface = typeface


}

fun Map<String, Any>.toBundle(): Bundle {
    return Bundle().apply {
        forEach {
            putString(it.key, it.value.toString())
        }
    }
}

fun BigDecimal.toCurrencyFormant(): String {
    val formatter = DecimalFormat("₡###,###,###")
    return formatter.format(this).replace(",", ".")
}

fun ToggleButton.disable(
    callback: CompoundButton.OnCheckedChangeListener,
    checked: Boolean = false
) {
    this.setOnCheckedChangeListener(null)
    this.isChecked = checked
    this.setOnCheckedChangeListener(callback)
}


class AutoClearedDialogValue<T : Any>(val fragment: BaseDialogFragment) :
    ReadWriteProperty<Fragment, T> {
    private var _value: T? = null

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                fragment.viewLifecycleOwnerLiveData.observe(fragment) { viewLifecycleOwner ->
                    viewLifecycleOwner?.lifecycle?.addObserver(object : DefaultLifecycleObserver {
                        override fun onDestroy(owner: LifecycleOwner) {
                            _value = null
                        }
                    })
                }
            }
        })
    }

    override fun getValue(thisRef: Fragment, property: KProperty<*>): T {
        return _value ?: throw IllegalStateException(
            "should never call auto-cleared-value get when it might not be available"
        )
    }

    override fun setValue(thisRef: Fragment, property: KProperty<*>, value: T) {
        _value = value
    }
}

/**
 * Creates an [] associated with this fragment.
 */
//fun <T : Any> BaseFragment.autoCleared() = AutoClearedValue<T>(this)

fun <T : Any> BaseDialogFragment.autoCleared() = AutoClearedDialogValue<T>(this)

fun String.clean(): String {
    return this.replace("\\n".toRegex(), " ").replace("  ", " ")
}
// View

fun EditText.validate(): Boolean {
    return this.text.isNullOrEmpty()
}

@Deprecated("no use in new views arch")
fun Fragment.hideKeyword() {
    try {
        val view = activity?.currentFocus
        if (view != null) {
            val imm =
                activity?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
            if (imm.isActive) imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}

fun <M, H : Holder> RecyclerView.initGrid(
    dataSet: CollectionAdapter<M, H>,
    orientation: Int = RecyclerView.VERTICAL,
    span: Int = 2,
    onItemClickListener: CollectionAdapter.OnItemClickListener<M, H>? = null
) {
    this.setHasFixedSize(true)
    this.layoutManager = GridLayoutManager(context, span, orientation, false)
    this.adapter = dataSet
    dataSet.setOnItemClickListener(onItemClickListener = onItemClickListener)
}


fun decodeErrorApiToException(errorCode: ErrorCode): Exception {
    return Exception(
        Gson().toJson(
            decodeErrorApi(errorCode = errorCode)
        )
    )
}


fun decodeErrorApi(errorCode: ErrorCode): ErrorApi {

    return when (errorCode.code) {

        ErrorCode.SUCCES.code -> {
            ErrorApi(
                statusCode = 200,
                devError = "Success.",
                errorMessage = "Success",
                codeError = ErrorCode.SUCCES
            )
        }

        ErrorCode.UNKNOWN.code -> {
            ErrorApi(
                statusCode = 500,
                devError = "Unknown error",
                errorMessage = ErrorCode.UNKNOWN.message.toString(),
                codeError = ErrorCode.UNKNOWN
            )
        }

        ErrorCode.INVALID_SERVICE.code -> {
            ErrorApi(
                statusCode = 501,
                devError = "Invalid Service",
                errorMessage = ErrorCode.INVALID_SERVICE.message.toString(),
                codeError = ErrorCode.INVALID_SERVICE
            )
        }

        ErrorCode.AUTH_FAILED.code -> {
            ErrorApi(
                statusCode = 502,
                devError = "Auth Failed",
                errorMessage = ErrorCode.AUTH_FAILED.message.toString(),
                codeError = ErrorCode.AUTH_FAILED
            )
        }


        else -> {
            ErrorApi(
                statusCode = 500,
                devError = "Unknown error",
                errorMessage = ErrorCode.UNKNOWN.message.toString(),
                codeError = ErrorCode.UNKNOWN
            )
        }
    }

}

fun decodeExceptionApi(exception: Exception): Exception {
    return Exception(
        Gson().toJson(exception)
    )
}

fun decodeErrorApi(exception: Exception, code: ErrorCode): Exception {
    return Exception(
        Gson().toJson(
            ErrorApi(
                statusCode = 500,
                errorMessage = exception.localizedMessage,
                devError = exception.localizedMessage,
                codeError = code
            )
        )
    )
}


fun decodeErrorApi(exception: Exception): ErrorApi {
    return Gson().fromJson(Gson().toJson(exception), ErrorApi::class.java)
}