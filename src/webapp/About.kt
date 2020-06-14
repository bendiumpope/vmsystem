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

const val ABOUT = "/about"

@Location(ABOUT)
class About

fun Route.about(db: Repository){
    get<About>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls = ""

        if (user != null){
            val profile = db.profilepix(user.userId)

            if (profile.isNotEmpty()) {
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
        }
        call.respond(FreeMarkerContent("about.ftl", mapOf("user" to user, "profileUrls" to profileUrls)))
    }
}