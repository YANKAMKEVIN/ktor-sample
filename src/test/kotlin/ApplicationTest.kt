package com.kev.service

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application {
            module()
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }

    //Get
    @Test
    fun `test GET tasks retourne une liste vide`() = testApplication {
        application {
            module()
        }
        client.get("/tasks").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("[]", bodyAsText())
        }
    }

    //Post
    @Test
    fun `test POST tasks ajoute une tâche`() = testApplication {
        application {
            module()
        }

        val taskJson = """
        {
            "title": "Apprendre Ktor",
            "description": "Faire un projet API avec Ktor",
            "completed": false
        }
    """

        val response = client.post("/tasks/add") {
            contentType(ContentType.Application.Json)
            setBody(taskJson)
        }

        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(response.bodyAsText().contains("Apprendre Ktor"))
    }

    @Test
    fun `test DELETE tasks supprime une tâche`() = testApplication {
        application {
            module()
        }

        // Création d'une tâche
        val taskJson = """
        {
            "title": "Faire les courses",
            "description": "Acheter du pain et du lait",
            "completed": false
        }
    """

        val createResponse = client.post("/tasks/add") {
            contentType(ContentType.Application.Json)
            setBody(taskJson)
        }

        val createdTask = createResponse.bodyAsText()
        val taskId = Regex("\"id\":\"([^\"]+)\"").find(createdTask)?.groupValues?.get(1)
       // assertNotNull(taskId)

        // Suppression de la tâche
        //val deleteResponse = client.delete("/tasks/$taskId")
      //  assertEquals(HttpStatusCode.OK, deleteResponse.status)
    }


}
