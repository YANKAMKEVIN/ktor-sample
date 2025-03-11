package com.kev.service.service

import org.jetbrains.exposed.sql.Table

object Tasks : Table() {
    val id = varchar("id", 50)
    val title = varchar("title", 255)
    val description = varchar("description", 255)
    val completed = bool("completed")

    override val primaryKey = PrimaryKey(id)
}