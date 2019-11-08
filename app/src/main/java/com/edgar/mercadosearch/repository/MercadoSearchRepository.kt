package com.edgar.mercadosearch.repository

import androidx.lifecycle.LiveData
import com.edgar.mercadosearch.data.api.BaseResponce
import com.edgar.mercadosearch.data.api.DetailResponce
import com.edgar.mercadosearch.network.ApiResponse

/**
 * @author Edgar Glellel
 * Implementation in @see [MercadoSearchRepositoryImpl]
 */
interface MercadoSearchRepository {
    fun searchItem(text: String)    : LiveData<ApiResponse<BaseResponce>>
    fun loadItemDetail(ids: String) : LiveData<ApiResponse<DetailResponce>>
}