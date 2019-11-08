package com.edgar.mercadosearch.di

import android.util.Log
import com.edgar.mercadosearch.BuildConfig
import com.edgar.mercadosearch.network.LiveDataCallAdapterFactory
import com.edgar.mercadosearch.network.MercadoSearchApiServices
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Edgar Glellel
 */
@Module
class NetworkModule {

    @Provides
    fun provideMercadoSearchApi(retrofit: Retrofit) = retrofit.create(MercadoSearchApiServices::class.java)

    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.MAIN_URL)
                    .addCallAdapterFactory(LiveDataCallAdapterFactory())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()

    /**
     *
     */
    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.d("NETWORK: ", message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
    }

    /*@Provides
    @Singleton
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient = OkHttpClient.Builder().addInterceptor(
            httpLoggingInterceptor).build()*/

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor(
                HttpLoggingInterceptor.Logger { message -> Log.d("NETWORK: ", message) })
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}