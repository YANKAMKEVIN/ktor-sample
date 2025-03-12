package com.kev.service.models

import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: String,
    val title: String,
    val description: String,
    val completed: Boolean
)

@Serializable
data class TaskRequest(
    val title: String,
    val description: String,
    val completed: Boolean
)