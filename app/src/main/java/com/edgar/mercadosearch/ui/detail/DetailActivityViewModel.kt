package com.edgar.mercadosearch.ui.detail

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edgar.mercadosearch.repository.MercadoSearchRepository
import com.edgar.mercadosearch.SingleLiveEvent
import com.edgar.mercadosearch.data.api.DetailResponce
import com.edgar.mercadosearch.utils.switchMapForApiResponse

/**
 * @author Edgar Glellel
 * View Model for @see [DetailActivity]
 */
class DetailActivityViewModel constructor (
    private val mercadoSearchRepository: MercadoSearchRepository
) : ViewModel() {

    val errorApi   = MutableLiveData<String>()
    val showLoader = MutableLiveData<Int>()
    val loadData: SingleLiveEvent<Unit> = SingleLiveEvent()


    init {
        //hide loader
        showLoader.value = View.VISIBLE
        loadData.call()
    }
    /**
     * Function that performs the search by the text entered by the user
     * @see [switchMapForApiResponse]
     * @return MapForApiResponse
     */
    fun loadItemDetail(ids:String): LiveData<DetailResponce?> {
        return Transformations.switchMap(loadData){
            switchMapForApiResponse(mercadoSearchRepository.loadItemDetail(ids)
                , doOnSuccess = {
                    showLoader.value = View.GONE
                    return@switchMapForApiResponse it
                }, doOnSubscribe = {
                    showLoader.value = View.VISIBLE
                }, doOnError = {
                    Log.e(TAG, it.message)
                    errorApi.value = it.message
                    showLoader.value = View.GONE
                })
        }
    }
    companion object {
        //Tag for debug
        private const val TAG = "DetailActivityVM"
    }
}