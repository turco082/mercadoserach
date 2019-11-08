package com.edgar.mercadosearch.di

import com.edgar.mercadosearch.network.MercadoSearchApiServices
import com.edgar.mercadosearch.repository.MercadoSearchRepository
import com.edgar.mercadosearch.repository.MercadoSearchRepositoryImpl


import dagger.Module
import dagger.Provides
/**
 * @author Edgar Glellel
 */
@Module
class RepositoryModule {

    @Provides
    fun getMercadoSearchRepo(api: MercadoSearchApiServices) : MercadoSearchRepository = MercadoSearchRepositoryImpl(api)

}