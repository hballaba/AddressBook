package com.project.eraga

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "application")
open class ApplicationPropertires {
    var dataSource = ""

}