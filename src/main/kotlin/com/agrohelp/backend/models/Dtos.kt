package com.agrohelp.backend.models

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
) {
    companion object {
        fun <T> ok(message: String, data: T?): ApiResponse<T> = ApiResponse(true, message, data)
        fun <T> error(message: String): ApiResponse<T> = ApiResponse(false, message, null)
    }
}

data class CalculatorRequest(
    val cropName: String = "",
    val acres: Double = 1.0
)

data class CalculatorResponse(
    val cropName: String,
    val acres: Double,
    val ureaBags: Double,
    val ureaKg: Double,
    val dapBags: Double,
    val dapKg: Double,
    val mopBags: Double,
    val mopKg: Double,
    val estimatedCost: Double,
    val schedule: String
)
