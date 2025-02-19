package routes

import com.kev.service.models.Task
import com.kev.service.service.TaskService
import io.ktor.http.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.taskRoutes() {
    route("/tasks") {

        get {
            call.respond(TaskService.getTasks())
        }

        post("/add") {
            val task = call.receive<Task>()
            call.respond(TaskService.createTask(task))
        }

        get("/{id}") {
            val taskId = call.parameters["id"]
            val task = taskId?.let { TaskService.getTask(it) }
            if (task != null) {
                call.respond(HttpStatusCode.OK, task)
            } else call.respond(HttpStatusCode.NotFound, "Not found")
        }

        delete("/{id}") {
            val taskId = call.parameters["id"]
            val task = taskId?.let { TaskService.getTask(it) }
            if (task != null) {
                TaskService.deleteTask(taskId)
                call.respond(HttpStatusCode.OK, "Task deleted")
            } else {
                call.respond(HttpStatusCode.NotFound, "Task not found")
            }
        }
    }
}
