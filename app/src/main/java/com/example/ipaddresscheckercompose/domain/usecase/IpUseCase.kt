package com.example.ipaddresscheckercompose.domain.usecase

import com.example.ipaddresscheckercompose.domain.repository.IpRepository
import javax.inject.Inject

class IpUseCase @Inject constructor(private val ipRepository: IpRepository) {

    suspend fun getIpDetails(ipAddress: String, accessKey: String) =
        ipRepository.getIpDetails(ipAddress, accessKey)
}