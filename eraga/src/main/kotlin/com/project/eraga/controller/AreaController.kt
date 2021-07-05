package com.project.eraga.controller

import com.project.eraga.entity.Area
import com.project.eraga.service.AreaService
import io.swagger.v3.oas.annotations.Operation

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = ["/api/"])
class AreaController {

    @GetMapping("areaName/{name}")
    @Operation(summary = "получение контактов по названию населенного пункта")
    fun findArea(@PathVariable name: String?): ResponseEntity<Area?>? {
        val areaService = AreaService()
        val area = areaService.findArea(name)
        return if (area != null) {
            ResponseEntity.ok<Area?>(area)
        } else {
            ResponseEntity.notFound().build<Area?>()
        }
    }
}