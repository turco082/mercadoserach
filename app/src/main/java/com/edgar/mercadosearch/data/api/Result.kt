package com.edgar.mercadosearch.data.api

/**
 * @author Edgar Glellel
 * Parce only what I will use
 */
data class Result(
    //val accepts_mercadopago: Boolean?,
    //val address: Address?,
    //val attributes: List<Attribute?>?,
    //val available_quantity: Int?,
    //val buying_mode: String?,
    //val catalog_listing: Boolean?,
    //val catalog_product_id: String?,
    //val category_id: String?,
    //val condition: String?,
    //val currency_id: String?,
    //val differential_pricing: DifferentialPricing?,
    val id: String?,
    //val installments: Installments?,
    //val listing_type_id: String?,
    //val official_store_id: Int?,
    //val original_price: Double?,
    //val permalink: String?,
    val price: Double?,
    //val seller: Seller?,
    //val seller_address: SellerAddress?,
    val shipping: Shipping?,
    //val site_id: String?,
    //val sold_quantity: Int?,
    //val stop_time: String?,
    //val tags: List<String?>?,
    val thumbnail: String?,
    val title: String?
)