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

const val VISITINGSCHEDULES = "/visitingschedules"

@Location(VISITINGSCHEDULES)
class VisitingSchedules

fun Route.visitingschedules(db: Repository, hashFunction: (String) -> String){


    get<VisitingSchedules>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls=""

        if(user == null){
            call.redirect(Signin())
        } else{


            val schedules = db.visitingschedule()
            val profile = db.profilepix(user.userId)

            if(profile.isNotEmpty()){
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent("schedules.ftl",
                    mapOf("schedules" to schedules, "user" to user, "profileUrls" to profileUrls, "code" to code), user.userId)
            )

        }

    }

    post<VisitingSchedules>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls = ""

        val params = call.receiveParameters()

        val action = params["action"] ?: throw IllegalArgumentException("Missing parameter: action")

        when(action){
            "delete" -> {
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")
                db.removevisitschedule(id.toInt())
                call.redirect(VisitingSchedules())
            }

            "edit"->{
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")
                val schedule = db.visitingschedule(id.toInt())

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
                        FreeMarkerContent("updateschedule.ftl",
                            mapOf( "schedule" to schedule, "profileUrls" to profileUrls, "user" to user, "code" to code, "id" to id), user.userId)
                    )
                }
            }
        }
    }

}

