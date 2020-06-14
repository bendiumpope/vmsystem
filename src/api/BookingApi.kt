package com.system.api

import com.system.API_VERSION
import com.system.api.request.VisitorApiRequest
import com.system.apiUser
import com.system.repository.Repository
import io.ktor.application.call
import io.ktor.auth.authenticate
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Route

const val BOOKING_ENDPOINT = "$API_VERSION/booking"

@Location(BOOKING_ENDPOINT)
class BookingApi

fun Route.bookingApi(db: Repository) {

    authenticate("jwt") {

        get<BookingApi>{
            val user = call.apiUser!!
            call.respond(db.visitbooking(user.userId))
        }

        post<BookingApi>{

            val user = call.apiUser!!

            try {
                val request = call.receive<VisitorApiRequest>()

                val booking = db.addvisitbooking(user.userId, request.whomtovisit, request.visitreason,
                    request.visitingdate, request.visitingtime, request.bookingstatus, request.date)
                if (booking != null){
                    call.respond(booking)
                }else{
                    call.respondText("Invalid data received", status = HttpStatusCode.InternalServerError)
                }
            }catch (e: Throwable){
                call.respondText("Invalid data received", status = HttpStatusCode.BadRequest)
            }
        }
    }

}

