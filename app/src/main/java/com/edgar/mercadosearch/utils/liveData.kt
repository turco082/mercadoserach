package com.edgar.mercadosearch.utils


import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.edgar.mercadosearch.network.*

/**
 * @author Edgar Glellel
 * Switch map for ApiResponse
 */
fun <T, X> switchMapForApiResponse(liveData: LiveData<ApiResponse<T>>,
                                   doOnSubscribe: (() -> Unit)? = null,
                                   doOnSuccess: (((T?) -> X?)?) = null,
                                   doOnError: (((Throwable) -> Unit)?) = null)
        : LiveData<X?>? {

    return Transformations.map(liveData) {
        when (it) {
            is ApiIsLoading -> {
                doOnSubscribe?.invoke()
                null
            }
            is ApiSuccessResponse -> {
                val responseBody = it.body
                doOnSuccess?.invoke(responseBody)
            }
            is ApiEmptyResponse<*> -> {
                doOnSuccess?.invoke(null)
                null
            }
            is ApiErrorResponse<*> -> {
                doOnError?.invoke(it.errorMessage)
                null
            }
            else -> null
        }
    }
}