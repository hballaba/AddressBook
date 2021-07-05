package com.project.eraga.service

import com.project.eraga.dao.AreaDaoImpl
import com.project.eraga.entity.Area

class AreaService {
    private val areaDaoImpl: AreaDaoImpl = AreaDaoImpl()

    fun AreaService() {}

    fun findArea(name: String?): Area? {
        return areaDaoImpl.findArea(name)
    }

    fun saveArea(area: Area?) {
        areaDaoImpl.saveArea(area)
    }

    fun updateArea(area: Area?) {
        areaDaoImpl.updateArea(area)
    }
}