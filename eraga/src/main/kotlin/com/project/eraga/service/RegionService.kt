package com.project.eraga.service

import com.project.eraga.dao.RegionDaoImpl
import com.project.eraga.entity.Region

class RegionService {
    private val regionDaoImpl: RegionDaoImpl = RegionDaoImpl()

    fun RegionService() {}


    fun findRegion(name: String?): Region? {
        return regionDaoImpl.findRegion(name)
    }

    fun saveRegion(region: Region?) {
        regionDaoImpl.saveRegion(region)
    }

    fun updateRegion(region: Region?) {
        regionDaoImpl.updateRegion(region)
    }

    fun getAllRegions(): List<Region?>? {
        return regionDaoImpl.getAllRegions()
    }
}