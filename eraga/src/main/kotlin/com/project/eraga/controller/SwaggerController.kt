package com.project.eraga.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.RedirectView

@RestController
@RequestMapping(value = ["/"])
class SwaggerController {

    @GetMapping("/")
    fun redirectSwagger(): RedirectView? {
        return RedirectView(
            "http://localhost:8080/swagger-ui/" +
                    "index.html?configUrl=/v3/api-docs/swagger-config#/"
        )
    }
}