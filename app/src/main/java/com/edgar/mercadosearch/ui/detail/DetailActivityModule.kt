package com.edgar.mercadosearch.ui.detail

import com.edgar.mercadosearch.repository.MercadoSearchRepository
import dagger.Module
import dagger.Provides

/**
 * @author Edgar Glellel
 * Provide ViewModelFactory
 */
@Module
class DetailActivityModule {

    @Provides
    internal fun provideViewModelFactory(repository: MercadoSearchRepository) : DetailActivityViewModelFactory
            = DetailActivityViewModelFactory(repository)
}