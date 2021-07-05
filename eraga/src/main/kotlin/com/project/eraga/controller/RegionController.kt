package com.project.eraga.controller

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonView
import com.project.eraga.entity.Region
import com.project.eraga.service.RegionService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value = ["/api/"])
class RegionController {

    @JsonCreator
    @GetMapping("regionName/{name}")
    @Operation(summary = "получение контактов по названию региона")
    fun  findRegion(@PathVariable name: String?): ResponseEntity<Region?>? {
        val regionService = RegionService()
        val region: Region? = regionService.findRegion(name)
        return if (region != null) {
            ResponseEntity.ok<Region?>(region)
        } else {
            ResponseEntity.notFound().build<Region?>()
        }
    }

    @JsonCreator
    @GetMapping("regionAll")
    @Operation(summary = "получение контактов по всем регионам")
    @ResponseBody
    fun  findAllRegion(): ResponseEntity<MutableList<Region>>? {
        val regionService = RegionService()
        val regions: MutableList<Region> = regionService.getAllRegions() as MutableList<Region>
        return if (regions?.size > 0) {
            ResponseEntity.ok<MutableList<Region>>(regions)
        } else {
            ResponseEntity.notFound().build<MutableList<Region>>()
        }
    }
}