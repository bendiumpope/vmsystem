package com.system.webapp

import com.system.model.EPSession
import com.system.model.VisitBooking
import com.system.model.VisitRecord
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

const val VISITED = "/visited"

@Location(VISITED)
class Visited

fun Route.visited(db: Repository, hashFunction: (String) -> String){


    get<Visited>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls=""


        if(user == null){
            call.redirect(Signin())
        } else{
            var visitrecords : List<VisitRecord>

            if((user.address == "Admin") || (user.address == "Secretary")){
                visitrecords = db.visitrecords()
            }else{

                visitrecords = db.visitrecord(user.userId)
            }

            val profile = db.profilepix(user.userId)

            if(profile.isNotEmpty()){
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent("visited.ftl",
                    mapOf("visitrecords" to visitrecords, "user" to user, "profileUrls" to profileUrls, "date" to date, "code" to code), user.userId)
            )

        }

    }

    post<Visited>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls=""
        val params = call.receiveParameters()
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        when(action){
            "delete" -> {
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")
                db.removevisitrecord(id)
                call.redirect(Visited())
            }
        }
    }

}