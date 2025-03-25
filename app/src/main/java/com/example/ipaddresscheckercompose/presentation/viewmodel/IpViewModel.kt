package com.example.ipaddresscheckercompose.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ipaddresscheckercompose.domain.model.IpStackResponse
import com.example.ipaddresscheckercompose.domain.usecase.IpUseCase
import com.example.ipaddresscheckercompose.presentation.util.IPResults
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class IpViewModel @Inject constructor(private val ipUseCase: IpUseCase): ViewModel() {

    private val _ipResultFlow = MutableStateFlow<IPResults<IpStackResponse>>(IPResults.None)
    val ipResultFlow = _ipResultFlow.asStateFlow()

    init {
        // getIPDetails("","")
    }

    fun getIPDetails(ipAddress: String, accessKey: String) {
        _ipResultFlow.update { IPResults.Loading }
        viewModelScope.launch {
            val result = ipUseCase.getIpDetails(ipAddress, accessKey)
            _ipResultFlow.update { result }
        }
    }
}