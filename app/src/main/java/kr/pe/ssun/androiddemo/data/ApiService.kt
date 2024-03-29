package kr.pe.ssun.androiddemo.data

import kr.pe.ssun.androiddemo.data.model.Shop
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/search/shop.json")
    suspend fun getShop(
        @Query("query") query: String,
        @Query("display") display: Int,
        @Query("start") start: Int,
        @Query("sort") sort: String
    ): Shop
}