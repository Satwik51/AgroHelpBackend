-- ===================================================
-- AgroHelp Database Schema Initialization
-- Tables: crops, fertilizers, mandi_prices, inquiries
-- ===================================================

CREATE TABLE IF NOT EXISTS crops (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    hindi_name VARCHAR(255),
    category VARCHAR(255) NOT NULL,
    season VARCHAR(255),
    soil VARCHAR(255),
    irrigation VARCHAR(255),
    fertilizer VARCHAR(1000),
    diseases VARCHAR(1000),
    yield_info VARCHAR(500),
    image_url VARCHAR(1000),
    temperature_range VARCHAR(255),
    water_requirement VARCHAR(255),
    sowing_months VARCHAR(255),
    harvesting_months VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS fertilizers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    hindi_name VARCHAR(255),
    category VARCHAR(255) NOT NULL,
    type VARCHAR(255),
    composition VARCHAR(1000),
    usage_instruction VARCHAR(1000),
    precautions VARCHAR(1000),
    suitable_crops VARCHAR(500),
    image_url VARCHAR(1000),
    price_per_bag VARCHAR(255),
    subsidized BOOLEAN DEFAULT TRUE,
    standard_pack_size VARCHAR(255) DEFAULT '50 kg'
);

CREATE TABLE IF NOT EXISTS mandi_prices (
    id BIGSERIAL PRIMARY KEY,
    crop_name VARCHAR(255) NOT NULL,
    category VARCHAR(255) DEFAULT 'Cereals',
    price VARCHAR(255) NOT NULL,
    min_price VARCHAR(255),
    max_price VARCHAR(255),
    modal_price VARCHAR(255),
    mandi_location VARCHAR(255) NOT NULL,
    state VARCHAR(255) DEFAULT 'India',
    trend INT DEFAULT 1,
    change_percent VARCHAR(255) DEFAULT '+0.0%',
    arrival_date VARCHAR(255) DEFAULT 'Today',
    image_url VARCHAR(1000)
);

CREATE TABLE IF NOT EXISTS inquiries (
    id BIGSERIAL PRIMARY KEY,
    farmer_name VARCHAR(255) NOT NULL,
    contact_number VARCHAR(255) NOT NULL,
    query TEXT NOT NULL,
    crop_name VARCHAR(255),
    state VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_crops_category ON crops(category);
CREATE INDEX IF NOT EXISTS idx_fertilizers_category ON fertilizers(category);
CREATE INDEX IF NOT EXISTS idx_mandi_category ON mandi_prices(category);
CREATE INDEX IF NOT EXISTS idx_mandi_crop ON mandi_prices(crop_name);
