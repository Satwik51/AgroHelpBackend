package com.agrohelp.backend.controllers

import com.agrohelp.backend.models.*
import com.agrohelp.backend.services.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/crops")
@CrossOrigin(origins = ["*"])
class CropController(private val cropService: CropService) {

    @GetMapping
    fun getCrops(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<ApiResponse<List<Crop>>> {
        val crops = when {
            !search.isNullOrBlank() -> cropService.searchCrops(search.trim())
            !category.isNullOrBlank() -> cropService.getCropsByCategory(category.trim())
            else -> cropService.getAllCrops()
        }
        return ResponseEntity.ok(ApiResponse.ok("Crops fetched successfully", crops))
    }

    @GetMapping("/{id}")
    fun getCropById(@PathVariable id: Long): ResponseEntity<ApiResponse<Crop>> {
        val crop = cropService.getCropById(id)
            ?: return ResponseEntity.status(404).body(ApiResponse.error("Crop with ID $id not found"))
        return ResponseEntity.ok(ApiResponse.ok("Crop found", crop))
    }
}

@RestController
@RequestMapping("/api/v1/fertilizers")
@CrossOrigin(origins = ["*"])
class FertilizerController(private val fertilizerService: FertilizerService) {

    @GetMapping
    fun getFertilizers(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<ApiResponse<List<Fertilizer>>> {
        val fertilizers = when {
            !search.isNullOrBlank() -> fertilizerService.searchFertilizers(search.trim())
            !category.isNullOrBlank() -> fertilizerService.getFertilizersByCategory(category.trim())
            else -> fertilizerService.getAllFertilizers()
        }
        return ResponseEntity.ok(ApiResponse.ok("Fertilizers fetched successfully", fertilizers))
    }

    @GetMapping("/{id}")
    fun getFertilizerById(@PathVariable id: Long): ResponseEntity<ApiResponse<Fertilizer>> {
        val fertilizer = fertilizerService.getFertilizerById(id)
            ?: return ResponseEntity.status(404).body(ApiResponse.error("Fertilizer with ID $id not found"))
        return ResponseEntity.ok(ApiResponse.ok("Fertilizer found", fertilizer))
    }
}

@RestController
@RequestMapping("/api/v1/mandi-prices")
@CrossOrigin(origins = ["*"])
class MandiPriceController(private val mandiPriceService: MandiPriceService) {

    @GetMapping
    fun getMandiPrices(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) search: String?
    ): ResponseEntity<ApiResponse<List<MandiPrice>>> {
        val prices = when {
            !search.isNullOrBlank() -> mandiPriceService.searchMandiPrices(search.trim())
            !category.isNullOrBlank() -> mandiPriceService.getMandiPricesByCategory(category.trim())
            else -> mandiPriceService.getAllMandiPrices()
        }
        return ResponseEntity.ok(ApiResponse.ok("Mandi rates fetched successfully", prices))
    }
}

@RestController
@RequestMapping("/api/v1/calculator")
@CrossOrigin(origins = ["*"])
class CalculatorController(private val calculatorService: CalculatorService) {

    @PostMapping("/dosage")
    fun calculateDosage(@RequestBody request: CalculatorRequest): ResponseEntity<ApiResponse<CalculatorResponse>> {
        if (request.acres <= 0) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Acres must be greater than zero"))
        }
        val response = calculatorService.calculate(request.cropName, request.acres)
        return ResponseEntity.ok(ApiResponse.ok("Fertilizer dosage calculated successfully", response))
    }
}

@RestController
@RequestMapping("/api/v1/inquiries")
@CrossOrigin(origins = ["*"])
class InquiryController(private val inquiryService: InquiryService) {

    @PostMapping
    fun submitInquiry(@RequestBody inquiry: Inquiry): ResponseEntity<ApiResponse<Inquiry>> {
        val saved = inquiryService.saveInquiry(inquiry)
        return ResponseEntity.ok(ApiResponse.ok("Inquiry submitted successfully", saved))
    }

    @GetMapping
    fun getAllInquiries(): ResponseEntity<ApiResponse<List<Inquiry>>> {
        val inquiries = inquiryService.getAllInquiries()
        return ResponseEntity.ok(ApiResponse.ok("Inquiries fetched successfully", inquiries))
    }
}

@RestController
@RequestMapping("/api/v1/health")
@CrossOrigin(origins = ["*"])
class HealthController(
    private val cropService: CropService,
    private val fertilizerService: FertilizerService,
    private val mandiPriceService: MandiPriceService
) {
    @GetMapping
    fun checkHealth(): ResponseEntity<Map<String, Any>> {
        val crops = cropService.getAllCrops().size
        val fertilizers = fertilizerService.getAllFertilizers().size
        val mandiPrices = mandiPriceService.getAllMandiPrices().size
        val healthData = mapOf(
            "status" to "UP",
            "server" to "AgroHelp Kotlin Spring Boot 3.4.1",
            "database" to "PostgreSQL (agrohelp_db)",
            "cropsCount" to crops,
            "fertilizersCount" to fertilizers,
            "mandiPricesCount" to mandiPrices,
            "totalRecords" to (crops + fertilizers + mandiPrices),
            "timestamp" to System.currentTimeMillis()
        )
        return ResponseEntity.ok(healthData)
    }
}
