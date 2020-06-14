package com.system.webapp

import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import com.system.securityCode
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
import java.lang.IllegalArgumentException

const val ACTIVE = "/active"

@Location(ACTIVE)
class Active

fun Route.active(db: Repository, hashFunction: (String) -> String) {


    get<Active> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        val actives = db.activeUsers()
        println("active ${actives.toList()}")
        var profileUrls = ""

        if (user == null) {
            call.redirect(Signin())
        } else {

            val profile = db.profilepix(user.userId)

            if (profile.isNotEmpty()) {
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }

            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent(
                    "active.ftl",
                    mapOf(
                        "actives" to actives,
                        "user" to user,
                        "profileUrls" to profileUrls
                    ), user.userId
                )
            )

        }

    }
}
