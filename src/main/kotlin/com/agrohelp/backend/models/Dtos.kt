package com.agrohelp.backend.models

data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val page: Int? = null,
    val size: Int? = null,
    val totalElements: Long? = null,
    val totalPages: Int? = null,
    val hasNext: Boolean? = null,
    val timestamp: Long = System.currentTimeMillis()
) {
    companion object {
        fun <T> ok(message: String, data: T?): ApiResponse<T> = ApiResponse(
            success = true,
            message = message,
            data = data
        )

        fun <T> paged(
            message: String,
            data: T?,
            page: Int,
            size: Int,
            totalElements: Long,
            totalPages: Int,
            hasNext: Boolean
        ): ApiResponse<T> = ApiResponse(
            success = true,
            message = message,
            data = data,
            page = page,
            size = size,
            totalElements = totalElements,
            totalPages = totalPages,
            hasNext = hasNext
        )

        fun <T> error(message: String): ApiResponse<T> = ApiResponse(
            success = false,
            message = message,
            data = null
        )
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
