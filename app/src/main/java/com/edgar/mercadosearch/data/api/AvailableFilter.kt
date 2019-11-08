package com.edgar.mercadosearch.data.api

data class AvailableFilter(
    val id: String?,
    val name: String?,
    val type: String?,
    val values: List<Value?>?
)