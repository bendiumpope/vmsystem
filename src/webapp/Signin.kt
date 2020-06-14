package com.system.webapp

import com.system.MIN_PASSWORD_LENGTH
import com.system.MIN_USER_ID_LENGTH
import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.Parameters
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import io.ktor.sessions.set

const val SIGNIN = "/signin"

@Location(SIGNIN)
data class Signin(val userId: String="", val error: String="")

fun Route.signin(db: Repository, hashFunction: (String) -> String){
    post<Signin>{

        val signinParameters = call.receive<Parameters>()
        val userId = signinParameters["userName"] ?: return@post call.redirect(it)
        val password = signinParameters["password"] ?: return@post call.redirect(it)

        val signInError = Signin(userId)

        val signin = when{
            userId.length < MIN_USER_ID_LENGTH -> null
            password.length < MIN_PASSWORD_LENGTH -> null
            else -> db.user(userId, hashFunction(password))
        }

        if (signin == null){
            call.redirect(signInError.copy(error = "Invalid username or password"))
        } else {

            val activeUser = db.activeUser(signin.userId)

            try {
                db.updateactiveUser(activeUser!!.id, signin.userId, "OnLine")

            } catch (e: Throwable){
                println("an error occured $e")
            }
            call.sessions.set(EPSession(signin.userId))
            call.redirect(AllBookings())
        }
    }

    get<Signin>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        if (user != null){
            call.redirect(Home())
        } else {
            call.respond(FreeMarkerContent("signin.ftl", mapOf("userId" to it.userId, "error" to it.error), ""))
        }
    }
}

