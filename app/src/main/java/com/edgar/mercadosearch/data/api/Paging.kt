package com.edgar.mercadosearch.data.api

data class Paging(
    val limit: Int?,
    val offset: Int?,
    val primary_results: Int?,
    val total: Int?
)