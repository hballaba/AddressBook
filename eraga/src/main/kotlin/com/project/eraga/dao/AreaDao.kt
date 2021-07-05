package com.project.eraga.dao

import com.project.eraga.entity.Area

interface AreaDao {
    fun saveArea(area: Area?)
    fun updateArea(area: Area?)
    fun findArea(name: String?): Area?
    fun changeArea(name: String?)
}