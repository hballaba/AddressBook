package com.project.eraga

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

//@EnableConfigurationProperties(ApplicationPropertires::class)
@SpringBootApplication
open class EragaApplication {

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder {
        return Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(EragaApplication::class.java, *args)
//    runApplication<EragaApplication>(*args)
}
