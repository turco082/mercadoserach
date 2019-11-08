package com.edgar.mercadosearch.di


import com.edgar.mercadosearch.MercadoSearchApp
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
/**
 * @author Edgar Glellel
 */
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class, ActivityBuilder::class])
interface AppComponent : AndroidInjector<MercadoSearchApp> {
    @Component.Builder
    abstract  class Builder : AndroidInjector.Builder<MercadoSearchApp>()

}