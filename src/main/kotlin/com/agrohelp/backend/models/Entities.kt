package com.agrohelp.backend.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "crops")
data class Crop(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String = "",

    val hindiName: String = "",

    @Column(nullable = false)
    val category: String = "", // Cereals, Pulses, Oilseeds, Cash Crops, Vegetables, Fruits, Spices

    val season: String = "",
    val soil: String = "",
    val irrigation: String = "",

    @Column(length = 1000)
    val fertilizer: String = "",

    @Column(length = 1000)
    val diseases: String = "",

    @Column(name = "yield_info", length = 500)
    val yieldInfo: String = "",

    @Column(length = 1000)
    val imageUrl: String = "",

    val temperatureRange: String = "",
    val waterRequirement: String = "",
    val sowingMonths: String = "",
    val harvestingMonths: String = ""
)

@Entity
@Table(name = "fertilizers")
data class Fertilizer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false, unique = true)
    val name: String = "",

    val hindiName: String = "",

    @Column(nullable = false)
    val category: String = "", // Nitrogenous, Phosphatic, Potassic, Micronutrients, Complex NPK, Organic & Biofertilizers, Soil Amendments

    val type: String = "",

    @Column(length = 1000)
    val composition: String = "",

    @Column(name = "usage_instruction", length = 1000)
    val usage: String = "",

    @Column(length = 1000)
    val precautions: String = "",

    @Column(length = 500)
    val suitableCrops: String = "",

    @Column(length = 1000)
    val imageUrl: String = "",

    val pricePerBag: String = "",
    val subsidized: Boolean = true,
    val standardPackSize: String = "50 kg"
)

@Entity
@Table(name = "mandi_prices")
data class MandiPrice(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val cropName: String = "",

    val category: String = "Cereals",

    @Column(nullable = false)
    val price: String = "",

    val minPrice: String = "",
    val maxPrice: String = "",
    val modalPrice: String = "",

    @Column(nullable = false)
    val mandiLocation: String = "",

    val state: String = "India",

    @Column(nullable = false)
    val trend: Int = 1, // 1: Up, -1: Down, 0: Stable

    @Column(nullable = false)
    val changePercent: String = "+0.0%",

    val arrivalDate: String = "Today",

    @Column(length = 1000)
    val imageUrl: String = ""
)

@Entity
@Table(name = "inquiries")
data class Inquiry(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    val farmerName: String = "",

    @Column(nullable = false)
    val contactNumber: String = "",

    @Column(length = 1000, nullable = false)
    val query: String = "",

    val cropName: String? = null,

    val state: String? = null,

    val createdAt: LocalDateTime = LocalDateTime.now()
)
