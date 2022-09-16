package com.example.healthyvirtuosotest.core.abstraction.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.example.healthyvirtuosotest.core.abstraction.activity.BaseActivity
import com.example.healthyvirtuosotest.core.view.dialog.ProgressDialog
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.lang.ref.WeakReference

/**
 * Common Base Fragment
 *
 * @author  Agustin Giannoni
 * @version 1.0
 */
abstract class BaseFragment<B : ViewBinding, A : BaseActivity<*>> : Fragment() {

    val navigation by lazy {
        Navigation.findNavController(binding.root)
    }
    private lateinit var dialog: ProgressDialog
    protected lateinit var binding: B

    @Suppress("UNCHECKED_CAST")
    val parent: WeakReference<A> by lazy {
        WeakReference(this.activity as A)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    abstract fun getBindingClass(): B

    /**
     * Find any view inside of this fragment view
     *
     * @param id int resource id to find
     * @return View instance object
     */
    protected fun findViewById(id: Int): View? {
        return binding.root.findViewById(id)
    }

    fun toolbarColor(color: Int) {
        parent.get()?.supportActionBar?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                requireContext(),
                color
            )
        )
    }

    /**
     * Implement this method to be able to initialize this fragment
     * Use findViewById(R.id.view_id) to find any view attached to this fragment
     *
     * @param savedInstanceState Bundle
     */
    protected abstract fun onCreateView(savedInstanceState: Bundle?)
    val navigationBar: BottomNavigationView?
        get() {
            return parent.get()?.navigationBar()
        }

    /**
     * Set ActionBar title if the activity is an instance of AppCompactActivity
     *
     * @param title CharSequence title to setup
     */
    open fun setTitle(title: String) {
        (parent.get() as? BaseActivity<*>)?.title = title
    }

    open fun onBackPressed(): Boolean {
        return false
    }

    fun showDialog() {
        if (!::dialog.isInitialized) {
            dialog = ProgressDialog(context = requireContext())
        }

        if (!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismissDialog() {
        if (::dialog.isInitialized && dialog.isShowing) {
            dialog.dismiss()
        }
    }
}