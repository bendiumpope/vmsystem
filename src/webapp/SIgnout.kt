package com.system.webapp

import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.clear
import io.ktor.sessions.get
import io.ktor.sessions.sessions

const val SIGNOUT = "/signout"

@Location(SIGNOUT)
class Signout

fun Route.signout(db:Repository){

    get<Signout>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        val activeUser = db.activeUser(user!!.userId)

        try {
            db.updateactiveUser(activeUser!!.id, user.userId, "OffLine")
        } catch (e: Throwable){
            println("an error occured $e")
            }

        call.sessions.clear<EPSession>()
        call.redirect(Signin())
    }
}