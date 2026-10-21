package com.agrohelp.backend.services

import com.agrohelp.backend.models.*
import com.agrohelp.backend.repositories.CropRepository
import com.agrohelp.backend.repositories.FertilizerRepository
import com.agrohelp.backend.repositories.InquiryRepository
import com.agrohelp.backend.repositories.MandiPriceRepository
import org.springframework.stereotype.Service

@Service
class CropService(private val cropRepository: CropRepository) {
    fun getAllCrops(): List<Crop> = cropRepository.findAll()

    fun getCropsByCategory(category: String): List<Crop> =
        if (category.equals("All", ignoreCase = true)) cropRepository.findAll()
        else cropRepository.findByCategoryIgnoreCase(category)

    fun searchCrops(query: String): List<Crop> =
        if (query.isBlank()) cropRepository.findAll()
        else cropRepository.searchCrops(query)

    fun getCropById(id: Long): Crop? = cropRepository.findById(id).orElse(null)
}

@Service
class FertilizerService(private val fertilizerRepository: FertilizerRepository) {
    fun getAllFertilizers(): List<Fertilizer> = fertilizerRepository.findAll()

    fun getFertilizersByCategory(category: String): List<Fertilizer> =
        if (category.equals("All", ignoreCase = true)) fertilizerRepository.findAll()
        else fertilizerRepository.findByCategoryIgnoreCase(category)

    fun searchFertilizers(query: String): List<Fertilizer> =
        if (query.isBlank()) fertilizerRepository.findAll()
        else fertilizerRepository.searchFertilizers(query)

    fun getFertilizerById(id: Long): Fertilizer? = fertilizerRepository.findById(id).orElse(null)
}

@Service
class MandiPriceService(private val mandiPriceRepository: MandiPriceRepository) {
    fun getAllMandiPrices(): List<MandiPrice> = mandiPriceRepository.findAll()

    fun getMandiPricesByCategory(category: String): List<MandiPrice> =
        if (category.equals("All", ignoreCase = true)) mandiPriceRepository.findAll()
        else mandiPriceRepository.findByCategoryIgnoreCase(category)

    fun searchMandiPrices(query: String): List<MandiPrice> =
        if (query.isBlank()) mandiPriceRepository.findAll()
        else mandiPriceRepository.searchMandiPrices(query)
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
        "rice" to CropFormula(100.0, 60.0, 30.0, "Apply full DAP and MOP with 1/3 Urea before puddling. Remaining Urea at active tillering (21 days) & panicle initiation."),
        "maize" to CropFormula(120.0, 60.0, 30.0, "Apply full DAP, MOP and 1/3 Urea at sowing. Top dress remaining Urea at knee-high and tasseling stages."),
        "cotton" to CropFormula(130.0, 65.0, 35.0, "Apply full DAP and Potash with 1/4 Urea as basal dose. Split remaining Urea across squaring, flowering, and boll development."),
        "sugarcane" to CropFormula(220.0, 110.0, 75.0, "Apply full DAP and 1/3 Potash at planting. Urea in 3 splits: 45, 90, and 120 days after planting with remaining Potash."),
        "potato" to CropFormula(150.0, 120.0, 80.0, "Apply full DAP and MOP with 1/2 Urea at planting. Top dress remaining Urea during first earthing up (30-35 days)."),
        "mustard" to CropFormula(70.0, 45.0, 15.0, "Apply full DAP, Potash and half Urea at sowing. Apply remaining Urea at first irrigation (30 days after sowing)."),
        "soybean" to CropFormula(30.0, 60.0, 25.0, "Apply full DAP and MOP at sowing. Being a legume, soybean fixes its own nitrogen through root nodules.")
    )

    fun calculate(cropName: String, acres: Double): CalculatorResponse {
        val key = cropName.lowercase().trim()
        val formula = formulas.entries.firstOrNull { key.contains(it.key) }?.value
            ?: formulas["wheat"]!!

        val totalUreaKg = formula.ureaKgPerAcre * acres
        val ureaBags = Math.round((totalUreaKg / 45.0) * 10.0) / 10.0

        val totalDapKg = formula.dapKgPerAcre * acres
        val dapBags = Math.round((totalDapKg / 50.0) * 10.0) / 10.0

        val totalMopKg = formula.mopKgPerAcre * acres
        val mopBags = Math.round((totalMopKg / 50.0) * 10.0) / 10.0

        val estCost = (ureaBags * 266.50) + (dapBags * 1350.0) + (mopBags * 1650.0)

        return CalculatorResponse(
            cropName = cropName,
            acres = acres,
            ureaBags = ureaBags,
            ureaKg = Math.round(totalUreaKg * 10.0) / 10.0,
            dapBags = dapBags,
            dapKg = Math.round(totalDapKg * 10.0) / 10.0,
            mopBags = mopBags,
            mopKg = Math.round(totalMopKg * 10.0) / 10.0,
            estimatedCost = Math.round(estCost * 100.0) / 100.0,
            schedule = formula.schedule
        )
    }
}

@Service
class InquiryService(private val inquiryRepository: InquiryRepository) {
    fun saveInquiry(inquiry: Inquiry): Inquiry = inquiryRepository.save(inquiry)
    fun getAllInquiries(): List<Inquiry> = inquiryRepository.findAll()
}
