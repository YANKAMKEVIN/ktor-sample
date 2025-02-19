package com.kev.service

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import routes.taskRoutes

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello World! My Name is Clarck")
        }
        taskRoutes()
    }
}
