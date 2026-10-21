package com.agrohelp.backend.config

import com.agrohelp.backend.repositories.CropRepository
import com.agrohelp.backend.repositories.FertilizerRepository
import com.agrohelp.backend.repositories.MandiPriceRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator
import javax.sql.DataSource

@Configuration
class DatabaseSeeder {

    private val logger = LoggerFactory.getLogger(DatabaseSeeder::class.java)

    @Bean
    fun seedDatabase(
        dataSource: DataSource,
        cropRepository: CropRepository,
        fertilizerRepository: FertilizerRepository,
        mandiPriceRepository: MandiPriceRepository
    ) = CommandLineRunner {
        val cropCount = cropRepository.count()
        val fertilizerCount = fertilizerRepository.count()
        val mandiCount = mandiPriceRepository.count()

        logger.info("🌱 Database status check: Crops=$cropCount, Fertilizers=$fertilizerCount, MandiPrices=$mandiCount")

        if (cropCount < 500 || fertilizerCount < 500 || mandiCount < 500) {
            logger.info("⚡ Bulk seeding 500+ records into PostgreSQL from data-bulk.sql...")
            try {
                val resource = ClassPathResource("data-bulk.sql")
                if (resource.exists()) {
                    val populator = ResourceDatabasePopulator(resource)
                    populator.setContinueOnError(true)
                    populator.execute(dataSource)
                    logger.info("✅ Bulk seeding complete! Crops=${cropRepository.count()}, Fertilizers=${fertilizerRepository.count()}, MandiPrices=${mandiPriceRepository.count()}")
                } else {
                    logger.warn("⚠️ data-bulk.sql not found in classpath!")
                }
            } catch (e: Exception) {
                logger.error("❌ Error executing bulk seed SQL: ${e.message}", e)
            }
        } else {
            logger.info("✅ Database already has 500+ records in each table. Skipping seed.")
        }
    }
}
