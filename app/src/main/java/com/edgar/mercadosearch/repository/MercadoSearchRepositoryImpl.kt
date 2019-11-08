package com.edgar.mercadosearch.repository

import androidx.lifecycle.LiveData
import com.edgar.mercadosearch.data.api.BaseResponce
import com.edgar.mercadosearch.data.api.DetailResponce
import com.edgar.mercadosearch.network.ApiResponse
import com.edgar.mercadosearch.network.MercadoSearchApiServices

import javax.inject.Inject

/**
 * @author Edgar Glellel
 * @see [MercadoSearchRepository]
 */
class MercadoSearchRepositoryImpl @Inject constructor(val api : MercadoSearchApiServices) : MercadoSearchRepository {

    /**
     * load Item Detail for product id @see [DetailResponce]
     * @return DetailResponce
     */
    override fun loadItemDetail(id: String): LiveData<ApiResponse<DetailResponce>> =
        api.loadItemDetail(id)

    /**
     * function for search for query text @see [BaseResponce]
     * @return BaseResponce
     */
    override fun searchItem(text: String) : LiveData<ApiResponse<BaseResponce>> =
        api.searchItem(text)

}