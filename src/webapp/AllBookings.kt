package com.system.webapp

import com.system.model.EPSession
import com.system.model.VisitBooking
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

const val ALLBOOKINGS = "/allbookings"

@Location(ALLBOOKINGS)
class AllBookings

fun Route.allbookings(db: Repository, hashFunction: (String) -> String){


    get<AllBookings>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls=""


        if(user == null){
            call.redirect(Signin())
        } else{
            var bookings : List<VisitBooking>

            if((user.address == "Admin") || (user.address == "Secretary")){
                bookings = db.visitbookings()

            }else{

               bookings = db.visitbooking(user.userId)
            }

            val profile = db.profilepix(user.userId)

            if(profile.isNotEmpty()){
                profileUrls = profile.get(profile.lastIndex).imageUrl

            }
            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent("bookings.ftl",
                    mapOf("bookings" to bookings, "user" to user, "profileUrls" to profileUrls, "date" to date, "code" to code), user.userId)
            )

        }

    }

    post<AllBookings>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls=""
        val params = call.receiveParameters()
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        when(action){
            "delete" -> {
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")
//                println("the id is ${id}")
                db.removevisitbooking(id)
                call.redirect(AllBookings())
            }

            "completed" -> {
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")

//                println("the id ${id}")
                val booking = db.visitbooking(id.toInt())
//                println("booking $booking")
                db.addvisitrecord(booking!!.userId, booking.date, booking.visittime, booking.whomtovisit)
                db.removevisitbooking(id)
                call.redirect(AllBookings())
            }

            "edit"->{
                val id = params["id"] ?: throw IllegalArgumentException("Missing parameter: id")
//                println("the id ${id}")
                val booking = db.visitbooking(id.toInt())
//                println("booking $booking")

                if(user == null){
                    call.redirect(Signin())
                } else{
                    val profile = db.profilepix(user.userId)
                    if(profile.isNotEmpty()){
                        profileUrls = profile.get(profile.lastIndex).imageUrl

                    }

                    val date = System.currentTimeMillis()
                    val code = call.securityCode(date, user, hashFunction)

                    call.respond(
                        FreeMarkerContent("updatebookings.ftl",
                            mapOf( "booking" to booking, "profileUrls" to profileUrls, "user" to user, "code" to code, "id" to id), user.userId)
                    )
                }
            }
        }
    }

}