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

const val UPDATESCHEDULE= "/updateschedule"

@Location(UPDATESCHEDULE)
class UpdateSchedule

fun Route.updateschedule(db: Repository, hashFunction: (String) -> String){

    get<UpdateSchedule>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        if(user == null){
            call.redirect(Signin())
        } else{

            call.respond(FreeMarkerContent("updateschedule.ftl", null))

        }
    }

    post<UpdateSchedule>{
        val params = call.receiveParameters()
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        when (action) {
            "update" -> {
                val id = params["id"] ?: throw java.lang.IllegalArgumentException("Missing parameter: id")
                val office = params["office"] ?: throw IllegalArgumentException("Missing parameter: whom to visit")
                val date = params["date"] ?: throw IllegalArgumentException("Missing parameter: visit reason")
                val timeduration = params["timeduration"] ?: throw IllegalArgumentException("Missing parameter: visiting date")

                db.updatevisitingschedule(id.toInt(), office, date, timeduration)

                call.redirect(VisitingSchedules())
            }
        }

    }
}
