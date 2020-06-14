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
import io.ktor.routing.put
import io.ktor.sessions.get
import io.ktor.sessions.sessions

    const val UPDATEBOOKINGS = "/updatebookings"

@Location(UPDATEBOOKINGS)
class UpdateBookings

fun Route.updatebookings(db: Repository, hashFunction: (String) -> String){

    get<UpdateBookings>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }


        if(user == null){
            call.redirect(Signin())
        } else{

            call.respond(FreeMarkerContent("updatebookings.ftl", null))

        }
    }


    post<UpdateBookings>{
        val params = call.receiveParameters()
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        when (action) {
            "update" -> {
                val id = params["id"] ?: throw java.lang.IllegalArgumentException("Missing parameter: id")
                val whomtovisit = params["whomtovisit"] ?: throw IllegalArgumentException("Missing parameter: whom to visit")
                val visitreason = params["reason"] ?: throw IllegalArgumentException("Missing parameter: visit reason")
                val visitingdate = params["visitingdate"] ?: throw IllegalArgumentException("Missing parameter: visiting date")
                val visittime = params["visittime"] ?: throw IllegalArgumentException("Missing parameter: Reason for visit time")
                val bookingstatus = params["bookingstatus"] ?: throw IllegalArgumentException("Missing parameter: Reason for booking status")
                val date = params["date"] ?: throw IllegalArgumentException("Missing parameter: date")
                db.updatevisitbooking(id.toInt(), whomtovisit, visitreason, visitingdate, visittime, bookingstatus, date)

                call.redirect(AllBookings())
            }
        }

    }
}

