package com.recrutify.rgc.mobileassistant.common

data class ApiErrorResponse<T>(val errorMessage: String) : ApiResponse<T>()