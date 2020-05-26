package com.dmt.juniortask.network

import retrofit2.http.*

//todo make wrapper for requests as guidelines suggest?
interface ApiService {

    @Headers("Accept: application/json")
    @POST("v1/tokens")
    suspend fun postToken(@Body user : UserProperty): TokenProperty

    @GET("v1/servers")
    suspend fun getServers(@Header("Authorization") authToken : String): List<ServerProperty>

}