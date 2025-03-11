package com.kev.service.service

import com.kev.service.models.Task
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object TaskService {

    fun getTasks(): List<Task> = transaction {
        Tasks.selectAll().map {
            Task(
                id = it[Tasks.id],
                title = it[Tasks.title],
                description = it[Tasks.description],
                completed = it[Tasks.completed],
            )
        }
    }

    fun createTask(task: Task): Task = transaction {
        val insertStatement = Tasks.insert {
            it[id] = task.id
            it[title] = task.title
            it[description] = task.description
            it[completed] = task.completed
        }
        task.copy(id = insertStatement[Tasks.id])
    }

    fun deleteTask(id: String) = transaction {
        Tasks.deleteWhere { Tasks.id eq id }
    }

    fun getTask(id: String): Task? = transaction {
        Tasks.selectAll().where { Tasks.id eq id }
            .map {
                Task(
                    id = it[Tasks.id].toString(),
                    title = it[Tasks.title],
                    description = it[Tasks.description],
                    completed = it[Tasks.completed],
                )
            }
            .singleOrNull()
    }

}