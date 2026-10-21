package com.agrohelp.backend.repositories

import com.agrohelp.backend.models.Crop
import com.agrohelp.backend.models.Fertilizer
import com.agrohelp.backend.models.Inquiry
import com.agrohelp.backend.models.MandiPrice
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface CropRepository : JpaRepository<Crop, Long> {
    fun findByCategoryIgnoreCase(category: String): List<Crop>

    @Query("SELECT c FROM Crop c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(c.hindiName) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(c.soil) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(c.category) LIKE LOWER(CONCAT('%', :q, '%'))")
    fun searchCrops(@Param("q") query: String): List<Crop>
}

@Repository
interface FertilizerRepository : JpaRepository<Fertilizer, Long> {
    fun findByCategoryIgnoreCase(category: String): List<Fertilizer>

    @Query("SELECT f FROM Fertilizer f WHERE LOWER(f.name) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(f.hindiName) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(f.composition) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(f.category) LIKE LOWER(CONCAT('%', :q, '%'))")
    fun searchFertilizers(@Param("q") query: String): List<Fertilizer>
}

@Repository
interface MandiPriceRepository : JpaRepository<MandiPrice, Long> {
    fun findByCategoryIgnoreCase(category: String): List<MandiPrice>

    @Query("SELECT m FROM MandiPrice m WHERE LOWER(m.cropName) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(m.mandiLocation) LIKE LOWER(CONCAT('%', :q, '%')) OR LOWER(m.state) LIKE LOWER(CONCAT('%', :q, '%'))")
    fun searchMandiPrices(@Param("q") query: String): List<MandiPrice>
}

@Repository
interface InquiryRepository : JpaRepository<Inquiry, Long>
