package com.edgar.mercadosearch.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.edgar.mercadosearch.repository.MercadoSearchRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MercadoSearchTest {
    private lateinit var repository: MercadoSearchRepository

    private val themeId = 456
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    @Before
    fun init() {

    }

    @Test
    fun loadLegoSetsFromNetwork() {
        runBlocking {
        }
    }

}