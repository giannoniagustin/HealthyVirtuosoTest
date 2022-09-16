package com.example.healthyvirtuosotest.core.abstraction.callbacks

/**
 * DataSet Change Interface
 *
 * @param <M> Model Class
 * @author Agustin Giannoni
 * @version 1.0
</M> */
interface DataSetChangeCallBack<M> {
    /**
     * It is called when any adapter change the dataSet value
     *
     * @param dataSet ArrayList of object model
     */
    fun onDataSetChange(dataSet: MutableList<M>)
}
