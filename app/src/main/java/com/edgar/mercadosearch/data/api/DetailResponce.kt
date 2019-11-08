package com.edgar.mercadosearch.data.api

data class DetailResponce(
    val accepts_mercadopago: Boolean?,
    val pictures: List<Picture?>?,
    val price: Double?,
    val title: String?,
    val shipping: Shipping?
)