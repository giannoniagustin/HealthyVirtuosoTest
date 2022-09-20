package com.example.healthyvirtuosotest.core.abstraction.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.healthyvirtuosotest.core.abstraction.callbacks.AdapterScrollCallBack
import com.example.healthyvirtuosotest.core.abstraction.callbacks.DataSetChangeCallBack
import com.example.healthyvirtuosotest.core.extensions.setSingleOnClickListener
import java.util.*

/**
 * Custom collection Adapter base class
 *
 * @param <M> Model class to create an Array list of this model
 * @param <H> Holder class to handle iterations
 * @author
 * @version 1.0
</H></M> */
abstract class CollectionAdapter<M, H : Holder> : RecyclerView.Adapter<H> {
    private var dataSetChangedCallBack: DataSetChangeCallBack<M>? = null
    private var onItemClickListener: OnItemClickListener<M, H>? = null
    private var scrollListener: AdapterScrollCallBack? = null
    var dataSet: MutableList<M> = arrayListOf()
    /**
     * Get adapter context
     *
     * @return Context
     */
    /**
     * Set context
     *
     * @param context Context
     */
    var context: Context

    protected constructor(context: Context) {
        this.context = context
    }

    protected constructor(
        context: Context,
        dataSet: ArrayList<M>,
    ) {
        this.context = context
        this.dataSet = dataSet
    }

    protected constructor(
        context: Context,
        dataSet: ArrayList<M>,
        dataSetChangedCallBack: DataSetChangeCallBack<M>,
    ) {
        this.context = context
        this.dataSet = dataSet
        this.dataSetChangedCallBack = dataSetChangedCallBack
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): H {
        val cardView =
            LayoutInflater.from(context).inflate(resource(viewType = viewType), parent, false)
        return createHolder(cardView, viewType)
    }

    override fun onBindViewHolder(holder: H, position: Int) {
        // notify scroll ended
        if (scrollListener != null) {
            scrollListener?.onScrollEnded(position)
        }
        /* binding view holder */
        bindHolder(getItem(position), holder, position)
        holder.itemView.setSingleOnClickListener {
            onItemClickListener?.onItemClick(
                getItem(position),
                holder,
                position
            )
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    /**
     * abstract method
     * It is called for get a new holder instance and initialize all views inside the holder
     *
     * @param cardView View where you can able to get all references inside your layout card view.
     * @param viewType int type
     * @return Holder instance
     */
    protected abstract fun createHolder(cardView: View, viewType: Int): H

    protected abstract fun resource(viewType: Int): Int


    /**
     * abstract method
     * It is called for bind the holder in position passed.
     *
     * @param item     Model item in position
     * @param holder   Holder attached
     * @param position int position to render
     */
    protected abstract fun bindHolder(item: M, holder: H, position: Int)

    /**
     * Get model item in a specific position
     *
     * @param position int position
     * @return model item
     */
    private fun getItem(position: Int): M {
        return dataSet[position]
    }

    /**
     * remove item inside dataSet collection
     *
     * @param position int position to remove
     */
    fun removeItem(position: Int) {
        if (position >= dataSet.size) return
        dataSet.removeAt(position)
        notifyItemRemoved(position)
        if (getDataSetChangedCallBack() != null) getDataSetChangedCallBack()?.onDataSetChange(
            dataSet
        )
    }

    /**
     * Add item to the final position inside dataSet
     *
     * @param item Model item
     */
    fun addItem(item: M) {
        dataSet.add(item)
        notifyItemInserted(dataSet.size - 1)
        if (getDataSetChangedCallBack() != null) getDataSetChangedCallBack()?.onDataSetChange(
            dataSet
        )
    }

    /**
     * Add item inside dataSet in a specific position
     *
     * @param item     Model item to insert
     * @param position position to insert
     * @throws Exception
     */
    @Throws(Exception::class)
    fun addItem(item: M, position: Int) {
        dataSet.add(position, item)
        notifyItemInserted(position)
        if (getDataSetChangedCallBack() != null) getDataSetChangedCallBack()?.onDataSetChange(
            dataSet
        )
    }

    /**
     * Change current dataSet
     *
     * @param dataSet ArrayList of object model
     */
    fun changeDataSet(dataSet: MutableList<M>) {
        this.dataSet = dataSet
        notifyDataSetChanged()
        if (getDataSetChangedCallBack() != null) getDataSetChangedCallBack()?.onDataSetChange(this.dataSet)
    }

    /**
     * Change current dataSet
     *
     * @param dataSet ArrayList of object model
     */
    fun changeAddDataSet(dataSet: MutableList<M>) {
        this.dataSet.addAll(dataSet)
        notifyDataSetChanged()
        if (getDataSetChangedCallBack() != null) getDataSetChangedCallBack()?.onDataSetChange(this.dataSet)
    }

    /**
     * Get dataSetChanged CallBack
     *
     * @return IDataSetChanged object instance
     */
    private fun getDataSetChangedCallBack(): DataSetChangeCallBack<M>? {
        return dataSetChangedCallBack
    }

    /**
     * Set dataSetChangedCallBack
     *
     * @param dataSetChangedCallBack IDataSetChanged of Model
     */
    fun setDataSetChangedCallBack(dataSetChangedCallBack: DataSetChangeCallBack<M>) {
        this.dataSetChangedCallBack = dataSetChangedCallBack
    }

    fun setScrollListener(scrollListener: AdapterScrollCallBack?) {
        this.scrollListener = scrollListener
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener<M, H>?) {
        this.onItemClickListener = onItemClickListener
    }

    interface OnItemClickListener<M, H> {
        fun onItemClick(item: M, holder: H, position: Int)
    }
}
