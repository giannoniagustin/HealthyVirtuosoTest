package com.example.healthyvirtuosotest.core.abstraction.activity

import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat.OnRequestPermissionsResultCallback
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.viewbinding.ViewBinding
import com.example.healthyvirtuosotest.R
import com.example.healthyvirtuosotest.core.extensions.gone
import com.example.healthyvirtuosotest.core.extensions.visible

import com.example.healthyvirtuosotest.core.view.dialog.ProgressDialog
import com.google.android.material.bottomnavigation.BottomNavigationView

/**
 * Base Activity class
 *
 *
 */
abstract class BaseActivity<B : ViewBinding> : AppCompatActivity(),
    OnRequestPermissionsResultCallback {

    protected lateinit var binding: B

    val dialog: ProgressDialog by lazy {
        ProgressDialog(context = this)
    }

    /**
     * flags
     */
    protected var isAuthenticated = false
    private var mTitle: CharSequence? = null
    private var toolbar: Toolbar? = null

    val navigation by lazy {
        (supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment)
    }

    open fun supportBinding(): View? {
        return null
    }

    abstract fun getBindingClass(): B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportBinding()?.let { view ->
            setContentView(view)
        } ?: setContentView(layoutResourceId)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        setToolbar(null)
    }

    open fun navigationBar(): BottomNavigationView? {
        return findViewById(R.id.bottomBar)
    }

    fun hideKeyword() {
        try {
            val view = this.currentFocus
            if (view != null) {
                val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                if (imm.isActive) imm.hideSoftInputFromWindow(view.windowToken, 0)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /**
     * Set title to actionBar
     *
     * @param title CharSequence to set on ActionBar
     */
    override fun setTitle(title: CharSequence) {
        mTitle = title
        val appBarTitle: TextView? = findViewById(toolbarTitleId)
        appBarTitle?.visible()
        appBarTitle?.text = title
    }


    /**
     * Set drawable to ActionBar
     *
     * @param iconRes int resource drawable id to set on ActionBar
     */
    fun setActionBarIcon(iconRes: Int) {
        if (toolbar != null) toolbar!!.setNavigationIcon(iconRes)
    }

    /**
     * Set drawable to ActionBar
     *
     * @param bitmapDrawable BitmapDrawable to set on ActionBar
     */
    fun setActionBarIcon(bitmapDrawable: BitmapDrawable?) {
        if (toolbar != null) toolbar!!.navigationIcon = bitmapDrawable
    }

    override fun onDestroy() {
        hideKeyword()
        super.onDestroy()
    }

    protected fun getToolbar(): Toolbar? {
        return toolbar
    }

    /**
     * Set toolbar action bar
     *
     * @param toolbar support v7 Toolbar
     */
    private fun setToolbar(toolbar: Toolbar?) {
        if (toolbar == null) this.toolbar = findViewById(toolbarId) else this.toolbar = toolbar
        if (this.toolbar != null) {
            setSupportActionBar(this.toolbar)
            if (supportActionBar != null) supportActionBar!!.setDisplayShowTitleEnabled(false)
            window.statusBarColor = ContextCompat.getColor(this.toolbar!!.context, statusBarColor)
        }
    }

    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    protected abstract val toolbarId: Int

    /**
     * Get toolbar layout id
     *
     * @return R.id.toolbar id if your layout has declared one, otherwise 0
     */
    protected abstract val toolbarTitleId: Int

    /**
     * Get layout resource to attach to this activity class
     *
     * @return R.layout.activity_main -> represent the layout view to use inside setContentView method
     */
    protected abstract val layoutResourceId: Int

    protected abstract var statusBarColor: Int

    /**
     * it is used for activity custom toolbar padding in windows status bar translucent feature
     *
     * @return true if you want to setup padding automatically, otherwise false
     */
    private fun hasToolbarPadding(): Boolean {
        return true
    }

    protected abstract val appBarId: Int

    fun showDialog() {
        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismissDialog() {
        if (dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun showDetails(viewIdHide: MutableList<Int>?, viewIdShow: MutableList<Int>?) {

        viewIdHide?.let {
            for (item in it) findViewById<View>(item)?.gone()
        }

        viewIdShow?.let {
            for (item in it) findViewById<View>(item)?.visible()
        }
    }

    fun onResult(requestCode: Int, resultCode: Int, data: Intent?) {
        onActivityResult(resultCode = resultCode, requestCode = requestCode, data = data)
    }

    @Deprecated(
        "Deprecated in Java", ReplaceWith(
            "super.onActivityResult(requestCode, resultCode, data)",
            "androidx.appcompat.app.AppCompatActivity"
        )
    )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
    }
}
