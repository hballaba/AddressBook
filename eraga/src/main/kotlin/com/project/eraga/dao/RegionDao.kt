package com.project.eraga.dao

import com.project.eraga.entity.Region

interface RegionDao {
    fun saveRegion(region: Region?)
    fun updateRegion(region: Region?)
    fun findRegion(name: String?): Region?
    fun getAllRegions(): List<Region?>?
}