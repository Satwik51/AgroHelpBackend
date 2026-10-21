package com.agrohelp.backend.config

import com.agrohelp.backend.models.Crop
import com.agrohelp.backend.models.Fertilizer
import com.agrohelp.backend.models.MandiPrice
import com.agrohelp.backend.repositories.CropRepository
import com.agrohelp.backend.repositories.FertilizerRepository
import com.agrohelp.backend.repositories.MandiPriceRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DatabaseSeeder {

    private val logger = LoggerFactory.getLogger(DatabaseSeeder::class.java)

    @Bean
    fun seedDatabase(
        cropRepository: CropRepository,
        fertilizerRepository: FertilizerRepository,
        mandiPriceRepository: MandiPriceRepository
    ) = CommandLineRunner {
        logger.info("🌱 Checking database records for bulk seeding...")

        // Seed or refresh crops
        cropRepository.deleteAll()
        logger.info("🌾 Seeding Bulk Verified Crop Data with Images into PostgreSQL...")
        cropRepository.saveAll(
            listOf(
                // CEREALS
                Crop(
                    name = "Wheat",
                    hindiName = "गेहूं",
                    category = "Cereals",
                    season = "Rabi (October - December)",
                    soil = "Well-drained fertile loamy & clay loam soil (pH 6.0 - 7.5)",
                    irrigation = "4-6 irrigations (Critical: CRI stage at 21 days, tillering, flowering, milk stage)",
                    fertilizer = "Basal: 55kg DAP + 20kg MOP + 35kg Urea. Top dress: 75kg Urea in 2 split doses.",
                    diseases = "Yellow & Brown Rust (Spray Propiconazole 25 EC @ 1ml/L), Loose Smut, Karnal Bunt, Aphids",
                    yieldInfo = "20-25 quintals per acre (High yielding varieties: HD-2967, PBW-550, DBW-187)",
                    imageUrl = "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "15°C - 25°C",
                    waterRequirement = "450 - 650 mm",
                    sowingMonths = "November",
                    harvestingMonths = "April"
                ),
                Crop(
                    name = "Rice / Paddy",
                    hindiName = "धान / चावल",
                    category = "Cereals",
                    season = "Kharif (June - July)",
                    soil = "Heavy clayey or alluvial soil with high water retention (pH 5.5 - 7.0)",
                    irrigation = "Continuous shallow submergence (5 cm) from transplanting to grain development",
                    fertilizer = "Basal: 60kg DAP + 30kg MOP + 35kg Urea. Top dress: 65kg Urea at tillering & panicle initiation + 25kg Zinc Sulphate.",
                    diseases = "Bacterial Leaf Blight (Spray Streptocycline), Blast, Sheath Blight, Stem Borer, Brown Plant Hopper",
                    yieldInfo = "22-28 quintals per acre (Varieties: Pusa Basmati 1121, PR-126, MTU-1010)",
                    imageUrl = "https://images.unsplash.com/photo-1536304993881-ff6e9eefa2a6?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "22°C - 35°C",
                    waterRequirement = "1200 - 1500 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "October - November"
                ),
                Crop(
                    name = "Maize / Corn",
                    hindiName = "मक्का",
                    category = "Cereals",
                    season = "Kharif (June) & Rabi (October)",
                    soil = "Deep, rich, well-drained fertile loam rich in organic matter (pH 6.5 - 7.5)",
                    irrigation = "5-6 irrigations. Avoid waterlogging; critical at knee-high, tasseling & silking stages",
                    fertilizer = "Basal: 60kg DAP + 30kg MOP + 40kg Urea. Top dress: 80kg Urea at knee-high and silking.",
                    diseases = "Fall Armyworm (Spray Chlorantraniliprole 18.5 SC), Maydis Leaf Blight, Stem Borer",
                    yieldInfo = "28-35 quintals per acre (Hybrids: Pioneer 3396, Dekalb 9108, PAC-751)",
                    imageUrl = "https://images.unsplash.com/photo-1551754655-cd27e38d2076?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "18°C - 32°C",
                    waterRequirement = "500 - 800 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "September - October"
                ),
                Crop(
                    name = "Barley",
                    hindiName = "जौ",
                    category = "Cereals",
                    season = "Rabi (October - November)",
                    soil = "Sandy loam to clay loam soils, highly tolerant to salinity and drought (pH 7.0 - 8.5)",
                    irrigation = "2-3 irrigations (Active tillering, booting, and grain filling stages)",
                    fertilizer = "Basal: 40kg DAP + 20kg MOP + 30kg Urea. Top dress: 40kg Urea at first irrigation.",
                    diseases = "Stripe Rust, Covered Smut, Aphids (Spray Dimethoate 30 EC)",
                    yieldInfo = "16-22 quintals per acre (Varieties: RD-2552, BH-902, DWRB-123)",
                    imageUrl = "https://images.unsplash.com/photo-1530595467537-0b5996c41f2d?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "12°C - 22°C",
                    waterRequirement = "350 - 450 mm",
                    sowingMonths = "October - November",
                    harvestingMonths = "March - April"
                ),
                Crop(
                    name = "Pearl Millet (Bajra)",
                    hindiName = "बाजरा",
                    category = "Cereals",
                    season = "Kharif (June - July)",
                    soil = "Light sandy to sandy loam with good drainage (pH 6.5 - 8.0)",
                    irrigation = "Rainfed; 1-2 protective irrigations during dry spells at flowering",
                    fertilizer = "Basal: 40kg DAP + 15kg MOP + 25kg Urea. Top dress: 35kg Urea at 30 days.",
                    diseases = "Downy Mildew (Green Ear), Ergot, Stem Borer (Spray Carbofuran granules in whorl)",
                    yieldInfo = "12-16 quintals per acre (Hybrids: HHB-67, Pioneer 86M88, ProAgro 9444)",
                    imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "25°C - 38°C",
                    waterRequirement = "300 - 400 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "September - October"
                ),
                Crop(
                    name = "Sorghum (Jowar)",
                    hindiName = "ज्वार",
                    category = "Cereals",
                    season = "Kharif (June) & Rabi (September)",
                    soil = "Clayey black soils with high moisture retention capacity (pH 6.0 - 8.5)",
                    irrigation = "Rainfed crop; 1-2 irrigations at primordial initiation and grain filling",
                    fertilizer = "Basal: 45kg DAP + 20kg MOP + 30kg Urea. Top dress: 40kg Urea at 30 days.",
                    diseases = "Shoot Fly (Seed treatment with Imidacloprid), Grain Mold, Stem Borer",
                    yieldInfo = "14-18 quintals per acre (Hybrids: CSH-16, CSH-25, CSV-20)",
                    imageUrl = "https://images.unsplash.com/photo-1605000797499-95a51c5269ae?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "25°C - 35°C",
                    waterRequirement = "400 - 550 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "October"
                ),

                // PULSES
                Crop(
                    name = "Chickpea (Gram / Chana)",
                    hindiName = "चना",
                    category = "Pulses",
                    season = "Rabi (October - November)",
                    soil = "Well-drained deep loamy to black cotton soils (pH 6.5 - 7.5)",
                    irrigation = "1-2 light irrigations (Pre-flowering & pod development). Never irrigate during active flowering.",
                    fertilizer = "Basal: 50kg DAP + 20kg MOP + 15kg Urea + Rhizobium & PSB seed treatment.",
                    diseases = "Fusarium Wilt (Seed treatment with Trichoderma), Pod Borer (Spray Emamectin Benzoate 5% SG)",
                    yieldInfo = "10-15 quintals per acre (Varieties: JG-14, JAKI-9218, Pusa-362)",
                    imageUrl = "https://images.unsplash.com/photo-1543362906-acfc16c67564?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "15°C - 25°C",
                    waterRequirement = "250 - 350 mm",
                    sowingMonths = "October - November",
                    harvestingMonths = "February - March"
                ),
                Crop(
                    name = "Pigeon Pea (Arhar / Tur)",
                    hindiName = "अरहर / तूर",
                    category = "Pulses",
                    season = "Kharif (June - July)",
                    soil = "Deep, well-drained loamy to black soils free from salinity (pH 6.5 - 7.5)",
                    irrigation = "Rainfed; 2 protective irrigations at branching and pod development",
                    fertilizer = "Basal: 60kg DAP + 20kg MOP + 20kg Sulphur + Rhizobium seed inoculation.",
                    diseases = "Sterility Mosaic Disease, Fusarium Wilt, Pod Fly, Gram Pod Borer",
                    yieldInfo = "8-12 quintals per acre (Varieties: BDN-711, ICPL-87119 / Asha, Pusa-992)",
                    imageUrl = "https://images.unsplash.com/photo-1586201375761-83865001e31c?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "20°C - 32°C",
                    waterRequirement = "500 - 650 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "December - January"
                ),
                Crop(
                    name = "Green Gram (Moong)",
                    hindiName = "मूंग",
                    category = "Pulses",
                    season = "Kharif (July), Zaid / Summer (March)",
                    soil = "Fertile well-drained sandy loam to loam soil (pH 6.2 - 7.2)",
                    irrigation = "Summer moong needs 3-4 irrigations every 10-12 days; stop irrigation 10 days before harvest",
                    fertilizer = "Basal: 40kg DAP + 15kg MOP + 10kg Urea + seed treatment with Rhizobium.",
                    diseases = "Yellow Mosaic Virus (Control whitefly vector with Thiamethoxam), Cercospora leaf spot",
                    yieldInfo = "5-8 quintals per acre (Varieties: Pusa Vishal, IPM 205-7 / Virat, Samrat)",
                    imageUrl = "https://images.unsplash.com/photo-1515543237350-b3eea1ec8082?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "25°C - 35°C",
                    waterRequirement = "300 - 400 mm",
                    sowingMonths = "March - April (Summer) / July (Kharif)",
                    harvestingMonths = "May - June / September"
                ),
                Crop(
                    name = "Black Gram (Urad)",
                    hindiName = "उड़द",
                    category = "Pulses",
                    season = "Kharif (June - July) & Summer (March)",
                    soil = "Loamy to heavy black cotton soils with good water-holding capacity (pH 6.5 - 7.8)",
                    irrigation = "Summer crop requires 3 irrigations at 15-day intervals",
                    fertilizer = "Basal: 40kg DAP + 15kg MOP + 10kg Urea.",
                    diseases = "Yellow Mosaic Virus, Root Rot, Powdery Mildew, Aphids",
                    yieldInfo = "5-7 quintals per acre (Varieties: Uttara, T-9, PU-31, Shekhar-2)",
                    imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "25°C - 35°C",
                    waterRequirement = "350 - 450 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "September - October"
                ),

                // OILSEEDS
                Crop(
                    name = "Mustard & Rapeseed",
                    hindiName = "सरसों / राई",
                    category = "Oilseeds",
                    season = "Rabi (October - November)",
                    soil = "Loamy to heavy loam with good drainage (pH 6.5 - 7.5)",
                    irrigation = "2 irrigations (First at 30-35 days / flowering, second at 60-65 days / siliqua filling)",
                    fertilizer = "Basal: 40kg DAP + 15kg MOP + 35kg Urea + 15kg Elemental Sulphur (critical for oil content). Top dress: 35kg Urea.",
                    diseases = "Mustard Aphids (Spray Rogor / Dimethoate), White Rust, Alternaria Blight",
                    yieldInfo = "8-12 quintals per acre (Varieties: Pusa Bold, RH-749, Pioneer 45S46, Giriraj)",
                    imageUrl = "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "15°C - 25°C",
                    waterRequirement = "250 - 350 mm",
                    sowingMonths = "October",
                    harvestingMonths = "February - March"
                ),
                Crop(
                    name = "Soybean",
                    hindiName = "सोयाबीन",
                    category = "Oilseeds",
                    season = "Kharif (June - July)",
                    soil = "Fertile black cotton soils or deep well-drained loam (pH 6.5 - 7.5)",
                    irrigation = "Mainly rainfed; critical irrigations during pod development if dry spell exceeds 15 days",
                    fertilizer = "Basal: 60kg DAP + 25kg MOP + 20kg Zinc Sulphate + Bradyrhizobium japonicum inoculation.",
                    diseases = "Yellow Mosaic Virus, Stem Fly, Girdle Beetle, Semi-looper (Spray Chlorantraniliprole)",
                    yieldInfo = "10-14 quintals per acre (Varieties: JS-335, JS-9560, JS-2034, NRC-86)",
                    imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "22°C - 32°C",
                    waterRequirement = "450 - 650 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "October"
                ),
                Crop(
                    name = "Groundnut (Peanut)",
                    hindiName = "मूंगफली",
                    category = "Oilseeds",
                    season = "Kharif (June - July) & Summer (January - February)",
                    soil = "Light sandy loam, well-drained, friable soil for smooth peg penetration (pH 6.0 - 7.0)",
                    irrigation = "4-5 irrigations; critical during flowering, pegging, and pod development",
                    fertilizer = "Basal: 40kg DAP + 30kg MOP + 15kg Urea + 100kg Gypsum at pegging stage.",
                    diseases = "Tikka Disease (Leaf Spot - Spray Mancozeb), Collar Rot, Leaf Miner",
                    yieldInfo = "12-16 quintals per acre (Varieties: TG-37A, GG-20, TAG-24, Kadiri-6)",
                    imageUrl = "https://images.unsplash.com/photo-1567401893414-76b7b1e5a7a5?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "22°C - 30°C",
                    waterRequirement = "500 - 650 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "October - November"
                ),
                Crop(
                    name = "Sunflower",
                    hindiName = "सूरजमुखी",
                    category = "Oilseeds",
                    season = "Kharif, Rabi & Spring (Year-round adaptable)",
                    soil = "Deep neutral soils with good drainage (pH 6.5 - 8.0)",
                    irrigation = "4-6 irrigations; critical at buttoning, ray-floret opening and seed filling",
                    fertilizer = "Basal: 50kg DAP + 20kg MOP + 30kg Urea + 15kg Sulphur. Top dress: 30kg Urea.",
                    diseases = "Alternaria Blight, Head Rot (Rhizopus), Capitulum Borer (Spray Spinosad)",
                    yieldInfo = "8-12 quintals per acre (Hybrids: Sunbred-275, PAC-36, KBSH-44)",
                    imageUrl = "https://images.unsplash.com/photo-1597848212624-a19eb35e2651?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "20°C - 30°C",
                    waterRequirement = "400 - 600 mm",
                    sowingMonths = "February - March",
                    harvestingMonths = "May - June"
                ),

                // CASH CROPS
                Crop(
                    name = "Cotton",
                    hindiName = "कपास",
                    category = "Cash Crops",
                    season = "Kharif (May - June)",
                    soil = "Deep black cotton soils (Vertisols) or deep fertile alluvium (pH 7.0 - 8.5)",
                    irrigation = "5-6 irrigations; avoid excess water during seedling, critical at squaring & boll bursting",
                    fertilizer = "Basal: 65kg DAP + 35kg MOP + 35kg Urea + 10kg Zinc. Top dress: 90kg Urea in 3 splits.",
                    diseases = "Pink Bollworm (Pheromone traps & Emamectin Benzoate), Whitefly, Cotton Leaf Curl Virus",
                    yieldInfo = "12-16 quintals per acre of seed cotton (Hybrids: RCH-659, US-51, Kaveri Jadu)",
                    imageUrl = "https://images.unsplash.com/photo-1606041008023-472dfb5e530f?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "25°C - 35°C",
                    waterRequirement = "700 - 1000 mm",
                    sowingMonths = "May - June",
                    harvestingMonths = "October - January"
                ),
                Crop(
                    name = "Sugarcane",
                    hindiName = "गन्ना",
                    category = "Cash Crops",
                    season = "Autumn (October) & Spring (February - March)",
                    soil = "Deep well-drained rich loam soil with high fertility (pH 6.5 - 7.5)",
                    irrigation = "Frequent irrigations every 8-12 days (Total 18-25 irrigations per annual cycle)",
                    fertilizer = "Basal: 110kg DAP + 50kg MOP + 60kg Urea. Top dress: 160kg Urea in 3 splits before earthing up.",
                    diseases = "Red Rot (Seed sett treatment with Carbendazim), Early Shoot Borer, Top Borer, Pyrilla",
                    yieldInfo = "350-450 quintals (35-45 tons) per acre (Varieties: Co-0238, Co-0118, Co-86032)",
                    imageUrl = "https://images.unsplash.com/photo-1593113598332-cd288d649433?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "24°C - 38°C",
                    waterRequirement = "1500 - 2000 mm",
                    sowingMonths = "February - March",
                    harvestingMonths = "December - March"
                ),

                // VEGETABLES
                Crop(
                    name = "Potato",
                    hindiName = "आलू",
                    category = "Vegetables",
                    season = "Rabi (October - November)",
                    soil = "Friable, loose, well-aerated sandy loam with high organic carbon (pH 5.2 - 6.5)",
                    irrigation = "Light and frequent irrigations every 6-8 days; avoid furrow flooding over ridges",
                    fertilizer = "Basal: 120kg DAP + 80kg MOP + 60kg Urea. Top dress: 90kg Urea at first earthing up (30 days).",
                    diseases = "Late Blight (Spray Dimethomorph + Mancozeb), Early Blight, Potato Tuber Moth",
                    yieldInfo = "100-140 quintals per acre (Varieties: Kufri Jyoti, Kufri Pukhraj, Kufri Chipsona)",
                    imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "15°C - 22°C",
                    waterRequirement = "400 - 500 mm",
                    sowingMonths = "October - November",
                    harvestingMonths = "January - February"
                ),
                Crop(
                    name = "Tomato",
                    hindiName = "टमाटर",
                    category = "Vegetables",
                    season = "Year-round (Autumn: Aug-Sep, Spring: Nov-Dec, Summer: Feb-Mar)",
                    soil = "Deep rich loamy soil rich in organic matter (pH 6.0 - 7.0)",
                    irrigation = "Drip irrigation recommended; every 3-4 days in warm weather",
                    fertilizer = "Basal: 100kg DAP + 50kg MOP + 40kg Urea. Top dress: 60kg Urea in 2 splits with calcium nitrate.",
                    diseases = "Early Blight, Bacterial Wilt, Tomato Fruit Borer (Helicoverpa), Leaf Curl Virus (Whitefly)",
                    yieldInfo = "150-220 quintals per acre (Hybrids: US-440, Shivam, Abhinav, Arka Rakshak)",
                    imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "18°C - 28°C",
                    waterRequirement = "500 - 700 mm",
                    sowingMonths = "August / November / February",
                    harvestingMonths = "After 75-90 days"
                ),
                Crop(
                    name = "Onion",
                    hindiName = "प्याज",
                    category = "Vegetables",
                    season = "Kharif (June), Late Kharif (Aug-Sep), Rabi (Nov-Dec)",
                    soil = "Well-drained loose fertile sandy loam or silty loam (pH 6.5 - 7.5)",
                    irrigation = "10-12 light irrigations; stop irrigation 15 days before harvesting for bulb drying",
                    fertilizer = "Basal: 50kg DAP + 40kg MOP + 30kg Urea + 20kg Sulphur. Top dress: 50kg Urea.",
                    diseases = "Purple Blotch (Spray Difenoconazole), Thrips (Spray Fipronil 5 SC), Basal Rot",
                    yieldInfo = "100-140 quintals per acre (Varieties: Bhima Super, N-53, Agrifound Dark Red)",
                    imageUrl = "https://images.unsplash.com/photo-1618512496248-a07fe83aa8cb?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "15°C - 30°C",
                    waterRequirement = "400 - 600 mm",
                    sowingMonths = "November - December",
                    harvestingMonths = "April - May"
                ),
                Crop(
                    name = "Chili (Green & Red)",
                    hindiName = "मिर्च",
                    category = "Vegetables",
                    season = "Kharif (June - July) & Summer (January - February)",
                    soil = "Well-drained light loam or black clay loam (pH 6.5 - 7.5)",
                    irrigation = "Irrigate every 5-7 days; avoid water stagnation",
                    fertilizer = "Basal: 60kg DAP + 30kg MOP + 30kg Urea. Top dress: 50kg Urea in 2 split doses.",
                    diseases = "Anthracnose (Die-back), Leaf Curl Virus (Thrips/Mites vector), Fruit Borer",
                    yieldInfo = "35-50 quintals green chili or 8-12 quintals dry chili per acre",
                    imageUrl = "https://images.unsplash.com/photo-1588252303782-cb80119abd6d?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "20°C - 30°C",
                    waterRequirement = "600 - 800 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "November - January"
                ),

                // FRUITS
                Crop(
                    name = "Mango",
                    hindiName = "आम",
                    category = "Fruits",
                    season = "Perennial (Flowering in Jan-Feb, Harvest May-July)",
                    soil = "Deep, rich alluvial to loamy well-drained soil (pH 5.5 - 7.5)",
                    irrigation = "Regular irrigation during fruit development; stop 2-3 months before flowering to induce bloom",
                    fertilizer = "Per mature tree: 1kg Urea + 1kg Single Super Phosphate + 1kg MOP + 50kg FYM in monsoon.",
                    diseases = "Mango Hopper, Powdery Mildew, Anthracnose, Fruit Fly (Methyl Eugenol traps)",
                    yieldInfo = "40-60 quintals per acre (Commercial varieties: Dasheri, Alphonso, Langra, Chausa)",
                    imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "24°C - 35°C",
                    waterRequirement = "1000 - 1500 mm",
                    sowingMonths = "July - August (Planting)",
                    harvestingMonths = "May - July"
                ),
                Crop(
                    name = "Banana",
                    hindiName = "केला",
                    category = "Fruits",
                    season = "Year-round planting (June-July & September-October)",
                    soil = "Deep fertile clay loam rich in organic matter (pH 6.5 - 7.5)",
                    irrigation = "High water requirement (Drip irrigation with 15-20 liters/plant/day)",
                    fertilizer = "Per plant: 200g Nitrogen + 60g Phosphorus + 300g Potash in multiple split fertigations.",
                    diseases = "Panama Wilt (Fusarium), Sigatoka Leaf Spot (Spray Carbendazim), Banana Weevil",
                    yieldInfo = "30-40 tons per acre (Grand Naine / G-9 tissue culture)",
                    imageUrl = "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "20°C - 35°C",
                    waterRequirement = "1500 - 2200 mm",
                    sowingMonths = "June - July",
                    harvestingMonths = "11-12 months after planting"
                ),
                Crop(
                    name = "Apple",
                    hindiName = "सेब",
                    category = "Fruits",
                    season = "Temperate (Harvest July - October)",
                    soil = "Deep well-drained loamy soil rich in organic matter (pH 5.5 - 6.5)",
                    irrigation = "Critical during fruit sizing; drip irrigation recommended during dry spells",
                    fertilizer = "Per mature tree: 700g Nitrogen + 350g Phosphorus + 700g Potash + micro-nutrient foliar spray.",
                    diseases = "Apple Scab (Spray Dodine / Mancozeb), Woolly Apple Aphid, Powdery Mildew, Canker",
                    yieldInfo = "8-12 tons per acre (Varieties: Royal Delicious, Red Chief, Gala, Granny Smith)",
                    imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "-7°C (chilling) to 25°C",
                    waterRequirement = "1000 - 1250 mm",
                    sowingMonths = "January - February (Dormant saplings)",
                    harvestingMonths = "August - October"
                ),
                Crop(
                    name = "Pomegranate",
                    hindiName = "अनार",
                    category = "Fruits",
                    season = "Ambe Bahar (Jan-Feb), Mrig Bahar (June-July), Hasta Bahar (Sep-Oct)",
                    soil = "Deep loamy or alluvial soil with good drainage (pH 6.5 - 7.5)",
                    irrigation = "Controlled drip irrigation; water stress induced for flower regulation",
                    fertilizer = "Per tree/year: 250g Nitrogen + 125g Phosphorus + 250g Potash + 20kg Farm Yard Manure.",
                    diseases = "Bacterial Blight (Telya - Spray Streptocycline + Copper Oxychloride), Fruit Borer",
                    yieldInfo = "6-9 tons per acre (Bhagwa variety / Sindhuri)",
                    imageUrl = "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "20°C - 38°C",
                    waterRequirement = "700 - 900 mm",
                    sowingMonths = "July - August",
                    harvestingMonths = "150-180 days after flowering"
                ),
                Crop(
                    name = "Papaya",
                    hindiName = "पपीता",
                    category = "Fruits",
                    season = "Spring (Feb-Mar), Monsoon (June-July), Autumn (Oct-Nov)",
                    soil = "Rich well-drained sandy loam soil; highly intolerant to waterlogging (pH 6.0 - 7.0)",
                    irrigation = "Frequent light irrigation every 6-8 days in winter, 3-4 days in summer",
                    fertilizer = "Per plant: 250g Urea + 500g Superphosphate + 250g MOP applied in 4 split doses.",
                    diseases = "Papaya Ring Spot Virus (Control Aphids vector), Root Rot / Damping Off, Mealybugs",
                    yieldInfo = "30-40 tons per acre (Varieties: Red Lady 786, Pusa Delicious, Taiwan-786)",
                    imageUrl = "https://images.unsplash.com/photo-1517282009859-f000ec3b26fe?auto=format&fit=crop&w=800&q=80",
                    temperatureRange = "22°C - 35°C",
                    waterRequirement = "1000 - 1400 mm",
                    sowingMonths = "February - March / July",
                    harvestingMonths = "9-10 months after planting"
                )
            )
        )

        // Seed or refresh fertilizers
        fertilizerRepository.deleteAll()
        logger.info("🧪 Seeding Bulk Verified Fertilizer Data with Images into PostgreSQL...")
        fertilizerRepository.saveAll(
            listOf(
                Fertilizer(
                    name = "Neem Coated Urea (46% N)",
                    hindiName = "नीम लेपित यूरिया",
                    category = "Nitrogenous",
                    type = "Nitrogen Fertilizer",
                    composition = "46.0% Total Nitrogen (Amide form) with 0.05% natural neem oil coating",
                    usage = "Basal application at sowing + 2-3 split top dressings during active vegetative growth. Apply only on moist soil.",
                    precautions = "Slow-release prevents leaching and denitrification. Avoid overuse which causes lodging and pest susceptibility.",
                    suitableCrops = "Wheat, Rice, Maize, Sugarcane, Cotton, Vegetables, Cereals",
                    imageUrl = "https://images.unsplash.com/photo-1628352081506-83c43123ed6d?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹266.50",
                    subsidized = true,
                    standardPackSize = "45 kg Bag"
                ),
                Fertilizer(
                    name = "DAP (Di-Ammonium Phosphate 18:46:0)",
                    hindiName = "डीएपी (डाय अमोनियम फॉस्फेट)",
                    category = "Phosphatic",
                    type = "Phosphorus & Nitrogen Complex",
                    composition = "18.0% Ammoniacal Nitrogen + 46.0% Available Phosphate (P2O5)",
                    usage = "Best applied as basal dose at or before sowing near seed depth to accelerate root elongation and vigor.",
                    precautions = "Store in dry place. Do not mix with quicklime or calcium nitrate. High phosphorus fixation in acidic/alkaline soils.",
                    suitableCrops = "All crops: Wheat, Paddy, Potato, Mustard, Pulses, Oilseeds",
                    imageUrl = "https://images.unsplash.com/photo-1585314062340-f1a5a7c9328d?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹1,350.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "MOP (Muriate of Potash 0:0:60)",
                    hindiName = "एमओपी (पोटाश)",
                    category = "Potassic",
                    type = "Potassium Fertilizer",
                    composition = "60.0% Water Soluble Potash (K2O as Potassium Chloride)",
                    usage = "Applied as basal dose or in two splits for sugarcane and tuber crops. Improves disease resistance and grain grain quality.",
                    precautions = "Contains chlorine; avoid for chloride-sensitive crops like tobacco, grapes, and potato starch varieties.",
                    suitableCrops = "Paddy, Wheat, Sugarcane, Maize, Cotton, Banana",
                    imageUrl = "https://images.unsplash.com/photo-1615811361523-6bd03d7748e7?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹1,650.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "NPK 10:26:26 Complex",
                    hindiName = "एनपीके 10:26:26",
                    category = "Complex NPK",
                    type = "Complete Balanced Complex Fertilizer",
                    composition = "10.0% Total Nitrogen, 26.0% Phosphate (P2O5), 26.0% Potash (K2O)",
                    usage = "Ideal basal fertilizer for heavy feeding crops and soils high in nitrogen but deficient in potassium and phosphorus.",
                    precautions = "Uniform granule size ensures equal distribution of nutrients across root zones.",
                    suitableCrops = "Cotton, Sugarcane, Potato, Pulses, Oilseeds, Vegetables",
                    imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹1,470.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "NPK 12:32:16 Complex",
                    hindiName = "एनपीके 12:32:16",
                    category = "Complex NPK",
                    type = "High Phosphorus Starter Complex",
                    composition = "12.0% Total Nitrogen, 32.0% Phosphate, 16.0% Potash",
                    usage = "Basal dressing at the time of sowing. Promotes deep rooting and tillering.",
                    precautions = "Keep in airtight bags to prevent moisture absorption.",
                    suitableCrops = "Wheat, Mustard, Soybean, Paddy, Maize, Chickpea",
                    imageUrl = "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹1,470.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "Single Super Phosphate (SSP 16% P + 11% S)",
                    hindiName = "सिंगल सुपर फॉस्फेट (एसएसपी)",
                    category = "Phosphatic",
                    type = "Phosphorus & Sulphur Fertilizer",
                    composition = "16.0% Water Soluble P2O5 + 11.0% Sulphur + 21% Calcium",
                    usage = "Basal application for all oilseeds, pulses, and leguminous crops.",
                    precautions = "Contains gypsum and active sulphur; excellent for reclaiming alkali soils.",
                    suitableCrops = "Mustard, Groundnut, Soybean, Sunflower, Pulses",
                    imageUrl = "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹520.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "Zinc Sulphate Monohydrate (33% Zn + 15% S)",
                    hindiName = "जिंक सल्फेट 33%",
                    category = "Micronutrients",
                    type = "Essential Micronutrient Fertilizer",
                    composition = "33.0% Zinc (Zn) + 15.0% Sulphur (S)",
                    usage = "Apply 5-7.5 kg per acre during field preparation or top dressing. Prevents Khaira disease in paddy.",
                    precautions = "Never mix Zinc Sulphate directly with DAP or SSP in solution (forms insoluble zinc phosphate).",
                    suitableCrops = "Paddy, Wheat, Maize, Cotton, Citrus, Sugarcane",
                    imageUrl = "https://images.unsplash.com/photo-1585314062340-f1a5a7c9328d?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹450.00",
                    subsidized = false,
                    standardPackSize = "5 kg Pack"
                ),
                Fertilizer(
                    name = "Borax (10.5% Boron)",
                    hindiName = "सुहागा / बोरेक्स",
                    category = "Micronutrients",
                    type = "Micronutrient Fertilizer",
                    composition = "10.5% Elemental Boron (Disodium Octaborate Tetrahydrate)",
                    usage = "Soil application: 2-4 kg/acre. Foliar spray (Solubor 20%): 1g/liter during flower initiation.",
                    precautions = "Essential for pollen tube germination and preventing fruit cracking in pomegranate, tomato, apple.",
                    suitableCrops = "Tomato, Pomegranate, Cauliflower, Mustard, Apple, Cotton",
                    imageUrl = "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹120.00",
                    subsidized = false,
                    standardPackSize = "1 kg Pack"
                ),
                Fertilizer(
                    name = "Agricultural Gypsum (CaSO4.2H2O)",
                    hindiName = "कृषि जिप्सम",
                    category = "Soil Amendments",
                    type = "Soil Conditioner & Secondary Nutrient",
                    composition = "70-80% Calcium Sulphate (23% Calcium + 18% Sulphur)",
                    usage = "Soil amendment: 200-500 kg/acre for sodic soil reclamation. Pod-formation booster: 100 kg/acre in groundnut.",
                    precautions = "Replaces exchangeable sodium in alkali soils and improves water infiltration.",
                    suitableCrops = "Groundnut, Mustard, Sodic soils, Potato, Sugarcane",
                    imageUrl = "https://images.unsplash.com/photo-1567401893414-76b7b1e5a7a5?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹350.00",
                    subsidized = true,
                    standardPackSize = "50 kg Bag"
                ),
                Fertilizer(
                    name = "Vermicompost / Organic Compost",
                    hindiName = "केंचुआ खाद (वर्मीकम्पोस्ट)",
                    category = "Organic & Biofertilizers",
                    type = "100% Organic Soil Enrichment",
                    composition = "Rich in organic carbon (18-20%), N (1.5-2%), P (1%), K (1%), beneficial microbes",
                    usage = "1-2 tons per acre before last ploughing or 2-5 kg per fruit tree basin.",
                    precautions = "Improves soil microflora, water retention, and cation exchange capacity without chemical burn.",
                    suitableCrops = "All crops, organic farming, horticulture, vegetables",
                    imageUrl = "https://images.unsplash.com/photo-1589923188900-85dae523342b?auto=format&fit=crop&w=800&q=80",
                    pricePerBag = "₹300.00",
                    subsidized = false,
                    standardPackSize = "40 kg Bag"
                )
            )
        )

        // Seed or refresh mandi prices
        mandiPriceRepository.deleteAll()
        logger.info("📈 Seeding Bulk Live Mandi Price Data with Images into PostgreSQL...")
        mandiPriceRepository.saveAll(
            listOf(
                MandiPrice(cropName = "Wheat (गेहूं)", category = "Cereals", price = "2,275 / quintal", minPrice = "2,200", maxPrice = "2,350", modalPrice = "2,275", mandiLocation = "Khanna Mandi", state = "Punjab", trend = 1, changePercent = "+2.8%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1574323347407-f5e1ad6d020b?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Paddy / Basmati Rice (धान)", category = "Cereals", price = "3,850 / quintal", minPrice = "3,600", maxPrice = "4,100", modalPrice = "3,850", mandiLocation = "Karnal Mandi", state = "Haryana", trend = 1, changePercent = "+1.5%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1536304993881-ff6e9eefa2a6?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Maize (मक्का)", category = "Cereals", price = "1,920 / quintal", minPrice = "1,850", maxPrice = "2,050", modalPrice = "1,920", mandiLocation = "Gulabbagh Mandi", state = "Bihar", trend = -1, changePercent = "-0.9%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1551754655-cd27e38d2076?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Cotton / Kapas (कपास)", category = "Cash Crops", price = "6,800 / quintal", minPrice = "6,400", maxPrice = "7,150", modalPrice = "6,800", mandiLocation = "Rajkot Mandi", state = "Gujarat", trend = 1, changePercent = "+3.4%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1606041008023-472dfb5e530f?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Soybean (सोयाबीन)", category = "Oilseeds", price = "4,450 / quintal", minPrice = "4,200", maxPrice = "4,600", modalPrice = "4,450", mandiLocation = "Indore Mandi", state = "Madhya Pradesh", trend = -1, changePercent = "-1.6%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1599940824399-b87987ceb72a?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Mustard (सरसों)", category = "Oilseeds", price = "5,450 / quintal", minPrice = "5,200", maxPrice = "5,650", modalPrice = "5,450", mandiLocation = "Bharatpur Mandi", state = "Rajasthan", trend = 1, changePercent = "+1.8%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1500937386664-56d1dfef3854?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Groundnut (मूंगफली)", category = "Oilseeds", price = "6,200 / quintal", minPrice = "5,800", maxPrice = "6,500", modalPrice = "6,200", mandiLocation = "Gondal Mandi", state = "Gujarat", trend = 1, changePercent = "+2.0%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1567401893414-76b7b1e5a7a5?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Chickpea / Chana (चना)", category = "Pulses", price = "5,800 / quintal", minPrice = "5,500", maxPrice = "6,050", modalPrice = "5,800", mandiLocation = "Bikaner Mandi", state = "Rajasthan", trend = -1, changePercent = "-0.8%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1543362906-acfc16c67564?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Potato (आलू)", category = "Vegetables", price = "1,450 / quintal", minPrice = "1,300", maxPrice = "1,600", modalPrice = "1,450", mandiLocation = "Agra Mandi", state = "Uttar Pradesh", trend = 1, changePercent = "+4.2%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1518977676601-b53f82aba655?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Onion (प्याज)", category = "Vegetables", price = "2,150 / quintal", minPrice = "1,800", maxPrice = "2,400", modalPrice = "2,150", mandiLocation = "Lasalgaon Mandi", state = "Maharashtra", trend = 1, changePercent = "+5.1%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1618512496248-a07fe83aa8cb?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Tomato (टमाटर)", category = "Vegetables", price = "2,200 / quintal", minPrice = "1,900", maxPrice = "2,500", modalPrice = "2,200", mandiLocation = "Kolar Mandi", state = "Karnataka", trend = -1, changePercent = "-3.5%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1592924357228-91a4daadcfea?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Chili / Dry Red (मिर्च)", category = "Spices", price = "18,500 / quintal", minPrice = "16,000", maxPrice = "21,000", modalPrice = "18,500", mandiLocation = "Guntur Mandi", state = "Andhra Pradesh", trend = 1, changePercent = "+2.2%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1588252303782-cb80119abd6d?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Cumin / Jeera (जीरा)", category = "Spices", price = "24,800 / quintal", minPrice = "22,500", maxPrice = "27,000", modalPrice = "24,800", mandiLocation = "Unjha Mandi", state = "Gujarat", trend = 1, changePercent = "+3.1%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1596040033229-a9821ebd058d?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Turmeric / Haldi (हल्दी)", category = "Spices", price = "13,200 / quintal", minPrice = "11,800", maxPrice = "14,500", modalPrice = "13,200", mandiLocation = "Nizamabad Mandi", state = "Telangana", trend = 1, changePercent = "+1.9%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Apple (सेब)", category = "Fruits", price = "7,500 / quintal", minPrice = "6,500", maxPrice = "9,000", modalPrice = "7,500", mandiLocation = "Sopore Mandi", state = "Jammu & Kashmir", trend = 1, changePercent = "+3.0%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1560806887-1e4cd0b6cbd6?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Banana (केला)", category = "Fruits", price = "2,400 / quintal", minPrice = "2,100", maxPrice = "2,700", modalPrice = "2,400", mandiLocation = "Jalgaon Mandi", state = "Maharashtra", trend = 1, changePercent = "+1.4%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1571771894821-ce9b6c11b08e?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Mango (आम)", category = "Fruits", price = "6,500 / quintal", minPrice = "5,500", maxPrice = "7,800", modalPrice = "6,500", mandiLocation = "Malihabad Mandi", state = "Uttar Pradesh", trend = 1, changePercent = "+2.9%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1553279768-865429fa0078?auto=format&fit=crop&w=400&q=80"),
                MandiPrice(cropName = "Pomegranate (अनार)", category = "Fruits", price = "9,800 / quintal", minPrice = "8,500", maxPrice = "11,500", modalPrice = "9,800", mandiLocation = "Solapur Mandi", state = "Maharashtra", trend = 1, changePercent = "+4.0%", arrivalDate = "Today", imageUrl = "https://images.unsplash.com/photo-1615485290382-441e4d049cb5?auto=format&fit=crop&w=400&q=80")
            )
        )
        logger.info("✅ Bulk Data with Images successfully seeded into PostgreSQL!")
    }
}
