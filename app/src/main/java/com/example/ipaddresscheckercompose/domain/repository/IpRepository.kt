package com.example.ipaddresscheckercompose.domain.repository

import com.example.ipaddresscheckercompose.data.neteworking.ApiService
import com.example.ipaddresscheckercompose.data.neteworking.safeApiCall
import com.example.ipaddresscheckercompose.data.neteworking.toResult
import com.example.ipaddresscheckercompose.domain.model.IpStackResponse
import com.example.ipaddresscheckercompose.presentation.util.IPResults
import javax.inject.Inject

interface IpRepository {
    suspend fun getIpDetails(ipAddress:String, accessKey: String) : IPResults<IpStackResponse>
}

class IpRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : IpRepository {
    override suspend fun getIpDetails(ipAddress:String, accessKey: String): IPResults<IpStackResponse> {
        return safeApiCall { apiService.getIpDetails(ipAddress,accessKey) }.toResult()
    }

}