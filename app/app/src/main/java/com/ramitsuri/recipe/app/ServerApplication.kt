package com.ramitsuri.recipe.app

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.builder.SpringApplicationBuilder




@SpringBootApplication
class ServerApplication: SpringBootServletInitializer(){
    override fun configure(application: SpringApplicationBuilder): SpringApplicationBuilder? {
        return application.sources(ServerApplication::class.java)
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(ServerApplication::class.java, *args)
}
