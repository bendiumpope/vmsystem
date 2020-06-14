package com.system.webapp

import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import com.system.securityCode
import com.system.verifyCode
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
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

const val ADDSCHEDULE = "/addschedule"

@Location(ADDSCHEDULE)
class AddSchedule

fun Route.addschedule(db: Repository, hashFunction: (String) -> String) {

    get<AddSchedule> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls = ""

        if(user == null){
            call.redirect(Signin())
        } else{
            val profile = db.profilepix(user.userId)

            if (profile.isNotEmpty()) {
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent("addschedule.ftl",
                    mapOf("profileUrls" to profileUrls, "user" to user, "code" to code), user.userId)
            )

        }
    }

    post<AddSchedule> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        val params = call.receiveParameters()

        val code = params["code"] ?: return@post call.redirect(it)
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")
        val date = System.currentTimeMillis()

        if(user == null || !call.verifyCode(date, user,code,hashFunction)){
            call.redirect(Signin())
        }

        when (action) {
            "add" -> {

                val office = params["office"] ?: throw IllegalArgumentException("Missing parameter: Office")
                val availabledate = params["date"] ?: throw IllegalArgumentException("Missing parameter: Date available")
                val timeduration = params["timeduration"] ?: throw IllegalArgumentException("Missing parameter: Time duration")

                db.addvisitingschedule(user!!.userId, office, availabledate, timeduration)

            }
        }
        call.redirect(VisitingSchedules())
    }
}