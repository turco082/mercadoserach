package com.edgar.mercadosearch.data.api
/**
 * @author Edgar Glellel
 * Parce only what I will use
 */
data class BaseResponce(
    //val available_filters: List<AvailableFilter?>?,
    //val available_sorts: List<AvailableSort?>?,
    //val filters: List<Filter?>?,
    //val paging: Paging?,
    //val query: String?,
    //val related_results: List<Any?>?,
    val results: List<Result?>?,
    //val secondary_results: List<Any?>?,
    val site_id: String?
    //val sort: Sort?
)