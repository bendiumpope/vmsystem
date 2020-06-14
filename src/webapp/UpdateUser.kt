package com.system.webapp

import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveParameters
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

const val UPDATEUSER = "/updateuser"

@Location(UPDATEUSER)
class Updateuser

fun Route.updateuser(db: Repository, hashFunction: (String) -> String) {

    get<Updateuser> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        if (user == null) {
            call.redirect(Signin())
        } else {

            call.respond(FreeMarkerContent("updateuser.ftl", null))

        }
    }

    post<Updateuser>{
        val params = call.receiveParameters()
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        when (action) {
            "update" -> {

                val id = params["id"] ?: throw java.lang.IllegalArgumentException("Missing parameter: id")
                val surName = params["surName"] ?: throw IllegalArgumentException("Missing parameter: SurName")
                val firstName = params["firstName"] ?: throw IllegalArgumentException("Missing parameter: First Name")
                val otherName = params["otherName"] ?: throw IllegalArgumentException("Missing parameter: Other Name")
                val emailAddress = params["emailAddress"] ?: throw IllegalArgumentException("Missing parameter: Email Address")
                val phoneNumber = params["phoneNumber"] ?: throw IllegalArgumentException("Missing parameter: Phone Number")
                val address = params["address"] ?: throw IllegalArgumentException("Missing parameter: Address")
                val role = params["role"] ?: throw IllegalArgumentException("Missing parameter: Role")
                val passwordHash = params["passwordHash"] ?: throw IllegalArgumentException("Missing parameter: PasswordHash")

                db.updateuser(id, surName, firstName, otherName, emailAddress, phoneNumber, address, role, passwordHash)

                call.redirect(Users())
            }
        }

    }
}

