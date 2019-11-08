package com.edgar.mercadosearch.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edgar.mercadosearch.repository.MercadoSearchRepository
import com.edgar.mercadosearch.ui.home.HomeActivityViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.hamcrest.CoreMatchers.notNullValue
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito.*

@RunWith(JUnit4::class)
class MercadoSearchViewModelTest {

    private val errorApi = "Algo a ocurrido"

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    private val repository = mock(MercadoSearchRepository::class.java)
    private var viewModel = HomeActivityViewModel(repository)

    @Test
    fun testNull() {
        assertThat(viewModel.loadData, nullValue())
        assertThat(viewModel.errorApi, notNullValue())
    }

    @Test
    fun doNotFetchWithoutObservers() {
        viewModel.errorApi.value = errorApi
    }

    @Test
    fun doNotFetchWithoutObserverOnConnectionChange() {
        viewModel.errorApi.value = errorApi
    }

}