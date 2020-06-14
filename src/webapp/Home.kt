package com.system.webapp

import com.system.model.EPSession
import com.system.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions

const val HOME = "/"

@Location(HOME)
class Home

fun Route.home(db: Repository) {
    get<Home> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls = ""

        if (user != null) {

            val profile = db.profilepix(user.userId)

            if (profile.isNotEmpty()) {
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
        }
        call.respond(FreeMarkerContent("home.ftl", mapOf("user" to user, "profileUrls" to profileUrls)))

    }
}