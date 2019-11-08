package com.edgar.mercadosearch.ui.home

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.edgar.mercadosearch.repository.MercadoSearchRepository
import com.edgar.mercadosearch.SingleLiveEvent
import com.edgar.mercadosearch.data.api.BaseResponce
import com.edgar.mercadosearch.data.api.Result
import com.edgar.mercadosearch.utils.switchMapForApiResponse

/**
 * @author Edgar Glellel
 * View Model for @see [HomeActivity]
 */
class HomeActivityViewModel constructor (
    private val mercadoSearchRepository: MercadoSearchRepository
) : ViewModel() {

    val showLoader = MutableLiveData<Int>()
    val errorApi   = MutableLiveData<String>()

    val loadData: SingleLiveEvent<Unit> = SingleLiveEvent()

    init {
        //hide loader
        showLoader.value = View.GONE
        loadData.call()
    }

    /**
     * Function that performs the search by the text entered by the user
     * @see [switchMapForApiResponse]
     * @return MapForApiResponse
     */
    fun searchResults(text:String): LiveData<BaseResponce?> {

        return Transformations.switchMap(loadData){
            switchMapForApiResponse(mercadoSearchRepository.searchItem(text)
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
        private const val TAG = "HomeActivityViewModel"
    }
}