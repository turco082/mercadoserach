package com.edgar.mercadosearch.data.api

data class Filter(
    val id: String?,
    val name: String?,
    val type: String?,
    val values: List<ValueX?>?
)