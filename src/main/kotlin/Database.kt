package com.kev.service

import com.kev.service.service.Tasks
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    try {
        //Connect to database
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")

        //Create tables
        transaction {
            addLogger(StdOutSqlLogger)
            SchemaUtils.create(Tasks)
        }
        println("Database connected and tables created successfully!")

    } catch (e: Exception) {
        println("Error configuring database: ${e.message}")
    }
}