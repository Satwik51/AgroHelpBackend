package com.agrohelp.backend.services

import com.agrohelp.backend.models.*
import com.agrohelp.backend.repositories.CropRepository
import com.agrohelp.backend.repositories.FertilizerRepository
import com.agrohelp.backend.repositories.InquiryRepository
import com.agrohelp.backend.repositories.MandiPriceRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CropService(private val cropRepository: CropRepository) {
    fun getAllCrops(): List<Crop> = cropRepository.findAll()

    fun getAllCrops(pageable: Pageable): Page<Crop> = cropRepository.findAll(pageable)

    fun getCropsByCategory(category: String): List<Crop> =
        if (category.equals("All", ignoreCase = true)) cropRepository.findAll()
        else cropRepository.findByCategoryIgnoreCase(category)

    fun getCropsByCategory(category: String, pageable: Pageable): Page<Crop> =
        if (category.equals("All", ignoreCase = true)) cropRepository.findAll(pageable)
        else cropRepository.findByCategoryIgnoreCase(category, pageable)

    fun searchCrops(query: String): List<Crop> =
        if (query.isBlank()) cropRepository.findAll()
        else cropRepository.searchCrops(query)

    fun searchCrops(query: String, pageable: Pageable): Page<Crop> =
        if (query.isBlank()) cropRepository.findAll(pageable)
        else cropRepository.searchCrops(query, pageable)

    fun getCropById(id: Long): Crop? = cropRepository.findById(id).orElse(null)

    fun getCropsCount(): Long = cropRepository.count()
}

@Service
class FertilizerService(private val fertilizerRepository: FertilizerRepository) {
    fun getAllFertilizers(): List<Fertilizer> = fertilizerRepository.findAll()

    fun getAllFertilizers(pageable: Pageable): Page<Fertilizer> = fertilizerRepository.findAll(pageable)

    fun getFertilizersByCategory(category: String): List<Fertilizer> =
        if (category.equals("All", ignoreCase = true)) fertilizerRepository.findAll()
        else fertilizerRepository.findByCategoryIgnoreCase(category)

    fun getFertilizersByCategory(category: String, pageable: Pageable): Page<Fertilizer> =
        if (category.equals("All", ignoreCase = true)) fertilizerRepository.findAll(pageable)
        else fertilizerRepository.findByCategoryIgnoreCase(category, pageable)

    fun searchFertilizers(query: String): List<Fertilizer> =
        if (query.isBlank()) fertilizerRepository.findAll()
        else fertilizerRepository.searchFertilizers(query)

    fun searchFertilizers(query: String, pageable: Pageable): Page<Fertilizer> =
        if (query.isBlank()) fertilizerRepository.findAll(pageable)
        else fertilizerRepository.searchFertilizers(query, pageable)

    fun getFertilizerById(id: Long): Fertilizer? = fertilizerRepository.findById(id).orElse(null)

    fun getFertilizersCount(): Long = fertilizerRepository.count()
}

@Service
class MandiPriceService(private val mandiPriceRepository: MandiPriceRepository) {
    fun getAllMandiPrices(): List<MandiPrice> = mandiPriceRepository.findAll()

    fun getAllMandiPrices(pageable: Pageable): Page<MandiPrice> = mandiPriceRepository.findAll(pageable)

    fun getMandiPricesByCategory(category: String): List<MandiPrice> =
        if (category.equals("All", ignoreCase = true)) mandiPriceRepository.findAll()
        else mandiPriceRepository.findByCategoryIgnoreCase(category)

    fun getMandiPricesByCategory(category: String, pageable: Pageable): Page<MandiPrice> =
        if (category.equals("All", ignoreCase = true)) mandiPriceRepository.findAll(pageable)
        else mandiPriceRepository.findByCategoryIgnoreCase(category, pageable)

    fun searchMandiPrices(query: String): List<MandiPrice> =
        if (query.isBlank()) mandiPriceRepository.findAll()
        else mandiPriceRepository.searchMandiPrices(query)

    fun searchMandiPrices(query: String, pageable: Pageable): Page<MandiPrice> =
        if (query.isBlank()) mandiPriceRepository.findAll(pageable)
        else mandiPriceRepository.searchMandiPrices(query, pageable)

    fun getMandiPricesCount(): Long = mandiPriceRepository.count()
}

@Service
class CalculatorService {

    data class CropFormula(
        val ureaKgPerAcre: Double,
        val dapKgPerAcre: Double,
        val mopKgPerAcre: Double,
        val schedule: String
    )

    private val formulas = mapOf(
        "wheat" to CropFormula(110.0, 55.0, 20.0, "Apply full DAP + full MOP + 1/3 Urea at sowing (basal dose). Remaining Urea in 2 splits with 1st irrigation (CRI stage) & 2nd irrigation."),
        "rice" to CropFormula(120.0, 60.0, 30.0, "Apply full DAP + full MOP + 1/3 Urea at transplanting. Remaining Urea in 2 equal splits at tillering and panicle initiation stages."),
        "paddy" to CropFormula(120.0, 60.0, 30.0, "Apply full DAP + full MOP + 1/3 Urea at transplanting. Remaining Urea in 2 equal splits at tillering and panicle initiation stages."),
        "maize" to CropFormula(130.0, 65.0, 25.0, "Apply full DAP + full MOP + 1/3 Urea as basal dose. 1/3 Urea at knee-high stage and remaining 1/3 at tasseling stage."),
        "cotton" to CropFormula(90.0, 45.0, 30.0, "Apply full DAP + full MOP + 1/4 Urea at sowing. Remaining Urea in 3 splits at square formation, flowering, and boll development."),
        "sugarcane" to CropFormula(220.0, 110.0, 75.0, "Apply full DAP and 1/3 Potash at planting. Urea in 3 splits: 45, 90, and 120 days after planting with remaining Potash."),
        "potato" to CropFormula(150.0, 75.0, 60.0, "Apply full DAP + full MOP + 1/2 Urea at planting. Remaining Urea at earthing-up (30-35 days after planting)."),
        "mustard" to CropFormula(80.0, 40.0, 20.0, "Apply full DAP + full MOP + 1/2 Urea at sowing. Remaining Urea with 1st irrigation (30-35 days). Add Sulphur 10kg/acre."),
        "soybean" to CropFormula(30.0, 60.0, 20.0, "Apply full dose as basal at sowing. Being a legume, Soybean fixes atmospheric Nitrogen; avoid excess Urea.")
    )

    fun calculate(cropName: String, acres: Double): CalculatorResponse {
        val key = cropName.lowercase().trim()
        val formula = formulas.entries.firstOrNull { key.contains(it.key) }?.value
            ?: CropFormula(100.0, 50.0, 25.0, "Standard balanced dosage: Apply full DAP and MOP at sowing. Urea in 2 equal split doses with irrigation.")

        val totalUreaKg = formula.ureaKgPerAcre * acres
        val ureaBags = Math.round((totalUreaKg / 45.0) * 10.0) / 10.0

        val totalDapKg = formula.dapKgPerAcre * acres
        val dapBags = Math.round((totalDapKg / 50.0) * 10.0) / 10.0

        val totalMopKg = formula.mopKgPerAcre * acres
        val mopBags = Math.round((totalMopKg / 50.0) * 10.0) / 10.0

        // Subsidized prices: Urea = ₹266.50/45kg, DAP = ₹1,350/50kg, MOP = ₹1,700/50kg
        val estimatedCost = Math.round(((ureaBags * 266.50) + (dapBags * 1350.0) + (mopBags * 1700.0)) * 100.0) / 100.0

        return CalculatorResponse(
            cropName = cropName,
            acres = acres,
            ureaBags = ureaBags,
            ureaKg = Math.round(totalUreaKg * 10.0) / 10.0,
            dapBags = dapBags,
            dapKg = Math.round(totalDapKg * 10.0) / 10.0,
            mopBags = mopBags,
            mopKg = Math.round(totalMopKg * 10.0) / 10.0,
            estimatedCost = estimatedCost,
            schedule = formula.schedule
        )
    }
}

@Service
class InquiryService(private val inquiryRepository: InquiryRepository) {
    fun saveInquiry(inquiry: Inquiry): Inquiry = inquiryRepository.save(inquiry)
    fun getAllInquiries(): List<Inquiry> = inquiryRepository.findAll()
}
