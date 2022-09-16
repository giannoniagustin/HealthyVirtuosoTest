package com.example.healthyvirtuosotest.core.view.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment : DialogFragment() {

    private lateinit var progress: ProgressDialog
    private var rootView: View? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = supportBinding(inflater, container) ?: inflater.inflate(
            layoutResource,
            container,
            false
        )
        onCreateView(savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return rootView
    }

    protected abstract fun onCreateView(savedInstanceState: Bundle?)

    open fun supportBinding(inflater: LayoutInflater, container: ViewGroup?): View? {
        return null
    }

    /**
     * Find any view inside of this fragment view
     *
     * @param id int resource id to find
     * @return View instance object
     */
    protected fun findViewById(id: Int): View? {
        return if (rootView != null) rootView!!.findViewById(id) else null
    }

    /**
     * Get layout resource id Example **R.layout.fragment_main**
     *
     * @return Int Layout resource id
     */
    protected abstract val layoutResource: Int

    fun showDialog() {
        if (!::progress.isInitialized) {
            progress = ProgressDialog(context = requireContext())
        }

        if (!progress.isShowing) {
            progress.show()
        }
    }

    fun dismissDialog() {
        if (::progress.isInitialized && progress.isShowing) {
            progress.dismiss()
        }
    }
}