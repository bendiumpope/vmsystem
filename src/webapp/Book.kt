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

const val BOOK = "/book"

@Location(BOOK)
class Book

fun Route.book(db: Repository, hashFunction: (String) -> String) {

    get<Book> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var profileUrls = ""

        if(user == null){
            call.redirect(Signin())
        } else{

            val bookings = db.visitbookings()
            val profile = db.profilepix(user.userId)

            if (profile.isNotEmpty()) {
                profileUrls = profile.get(profile.lastIndex).imageUrl
            }

            val date = System.currentTimeMillis()
            val code = call.securityCode(date, user, hashFunction)
            call.respond(
                FreeMarkerContent("book.ftl",
                    mapOf("bookings" to bookings, "profileUrls" to profileUrls, "user" to user, "date" to date, "code" to code), user.userId)
            )

        }
    }

    post<Book> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        val params = call.receiveParameters()
        val date = params["date"] ?.toLongOrNull() ?: return@post call.redirect(it)
        val code = params["code"] ?: return@post call.redirect(it)
        val action = params["action"] ?: throw java.lang.IllegalArgumentException("Missing parameter: action")

        if(user == null || !call.verifyCode(date, user,code,hashFunction)){
            call.redirect(Signin())
        }

        when (action) {
            "add" -> {
                //HOW TO HANDLE DATE AND TIME IN KOTLIN
                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
                val formatted = current.format(formatter)

                val whomtovisit = params["whomtovisit"] ?: throw IllegalArgumentException("Missing parameter: Staff Name")
                val reason = params["reason"] ?: throw IllegalArgumentException("Missing parameter: Faculty")
                val visitingdate = ""
                val visittime = ""
                val bookingstatus = "Pending...."
                val datebooked = formatted.toString()

                db.addvisitbooking(user!!.userId, whomtovisit, reason, visitingdate, visittime, bookingstatus, datebooked)

            }
        }
        call.redirect(AllBookings())
    }
}