package com.example.ipaddresscheckercompose.data.neteworking

import com.example.ipaddresscheckercompose.domain.model.IpStackResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("/{ipAddress}")
    suspend fun getIpDetails(
        @Path("ipAddress") ipAddress: String,
        @Query("access_key") accessKey: String
    ) : IpStackResponse
}