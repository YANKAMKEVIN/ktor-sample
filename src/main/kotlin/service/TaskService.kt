package com.kev.service.service

import com.kev.service.models.Task

object TaskService {
    private val task1 = Task(id = "id1", title = "title1", description = "description1", completed = false)
    private val task2 = Task(id = "id2", title = "title2", description = "description2", completed = false)
    private val task3 = Task(id = "id3", title = "title3", description = "description3", completed = false)
    private val task4 = Task(id = "id4", title = "title4", description = "description4", completed = false)
    private val task5 = Task(id = "id5", title = "title5", description = "description5", completed = false)
    private val task6 = Task(id = "id6", title = "title6", description = "description6", completed = false)
    private val task7 = Task(id = "id7", title = "title7", description = "description7", completed = false)
    private val task8 = Task(id = "id8", title = "title8", description = "description8", completed = false)
    private val task9 = Task(id = "id9", title = "title9", description = "description9", completed = false)
    private val task10 = Task(id = "id10", title = "title10", description = "description10", completed = false)

    private val tasks = mutableListOf<Task>(
        task1, task2, task3, task4, task5,
        task6, task7, task8, task9, task10
    )


    fun getTasks(): List<Task> = tasks

    fun createTask(task: Task): Task {
        tasks.add(task)
        return task
    }

    fun deleteTask(id: String) {
        tasks.removeIf { it.id == id }
    }

    fun getTask(id: String): Task? {
        return tasks.find { it.id == id }
    }

}