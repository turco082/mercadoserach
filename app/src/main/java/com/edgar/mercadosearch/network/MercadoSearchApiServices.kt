package com.edgar.mercadosearch.network

import androidx.lifecycle.LiveData
import com.edgar.mercadosearch.data.api.BaseResponce
import com.edgar.mercadosearch.data.api.DetailResponce
import retrofit2.http.*

/**
 * @author Edgar Glellel
 * ApiServices for Retrofit, for more information --> https://square.github.io/retrofit/
 * @see [ApiResponse]
 */
interface MercadoSearchApiServices {

    /**
     * Get query for searchItem
     * @return BaseResponce
     * @see [BaseResponce]
     */
    @Headers("Content-type: application/json", "Accept: */*")
    @GET(value = "/sites/MLA/search?")
    fun searchItem(@Query("q") text: String): LiveData<ApiResponse<BaseResponce>>

    /**
     * Get query for loadItemDetail
     * @return DetailResponce
     * @see [DetailResponce]
     */
    @Headers("Content-type: application/json", "Accept: */*")
    @GET(value = "/items?attributes=title,price,pictures,accepts_mercadopago,shipping&")
    fun loadItemDetail(@Query("id") ids: String): LiveData<ApiResponse<DetailResponce>>
}