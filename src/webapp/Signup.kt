package com.system.webapp

import com.system.MIN_PASSWORD_LENGTH
import com.system.MIN_USER_ID_LENGTH
import com.system.model.EPSession
import com.system.model.User
import com.system.redirect
import com.system.repository.Repository
import com.system.userNameValid
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.application.log
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

const val SIGNUP = "/signup"

@Location(SIGNUP)
data class Signup(
    val userId: String="",
    val firstName: String="",
    val lastName: String="",
    val emailAddress: String="",
    val orgName: String="",
    val orgAddress: String="",
    val error: String = ""
)

fun Route.signup(db: Repository, hashFunction: (String) -> String){
    post<Signup>{
        val user = call.sessions.get<EPSession>()?.let { user -> db.user(user.userId) }


        val signUpParameters = call.receive<Parameters>()

        val userId = signUpParameters["userName"] ?: return@post call.redirect(it)
        val surName = signUpParameters["surName"] ?: return@post call.redirect(it)
        val firstName = signUpParameters["firstName"] ?: return@post call.redirect(it)
        val otherName = signUpParameters["otherName"] ?: return@post call.redirect(it)
        val emailAddress = signUpParameters["emailAddress"] ?: return@post call.redirect(it)
        val phoneNumber = signUpParameters["phoneNumber"] ?: return@post call.redirect(it)
        val address = signUpParameters["address"] ?: return@post call.redirect(it)
        val role = signUpParameters["role"] ?: return@post call.redirect(it)
        val password = signUpParameters["password"] ?: return@post call.redirect(it)
        val confirmPassword = signUpParameters["confirmPassword"] ?: return@post call.redirect(it)

        val signupError = Signup(userId, surName, emailAddress)
        val userRole = db.userByRole(role)
        //println("user  ${userRole[0]}")

        if( (role != "Admin") && (role != "VC") && (role != "HOD") && (role != "Secretary") && (role != "Visitor")){
            call.redirect(signupError.copy(error = "Role must be Admin, VC, HOD, Secretary or Visitor"))
        }


        if ( (userRole.isEmpty()) && ((role != "Secretary" ) || (role != "Visitor"))) {
            call.redirect(signupError.copy(error = "You cant signup as an Adim, a VC or a HOD"))
        }

        when{
            password.length < MIN_PASSWORD_LENGTH ->
                call.redirect(signupError.copy(error = "Password should be at least $MIN_PASSWORD_LENGTH characters long"))
            password != confirmPassword ->
                call.redirect(signupError.copy(error = "Password/confirm password dont match"))
            userId.length < MIN_USER_ID_LENGTH ->
                call.redirect(signupError.copy(error = "Username should be at least $MIN_USER_ID_LENGTH characters long"))
            !userNameValid(userId) ->
                call.redirect(signupError.copy(error = "Username should consist of digits, letters, dots or underscores"))
            db.user(userId) != null ->
                call.redirect(signupError.copy(error = "User with the following username is already registered"))


            else -> {
                val hash = hashFunction(password)
                val newUser = User(userId, surName, firstName, otherName, emailAddress, phoneNumber, address, role, hash)

                try {
                    db.createUser(newUser)
                    db.addactiveUser(userId, "OnLine")
                } catch (e: Throwable){
                    when{
                        db.user(userId) != null ->
                            call.redirect(signupError.copy(error = "User with the following username $ is already registered"))
                        db.userByEmail(emailAddress) != null ->
                            call.redirect(signupError.copy(error = "User with the following email $emailAddress is already registered"))

                        else ->{
                            application.log.error("Failed to register user", e)
                            call.redirect(signupError.copy(error = "Failed to register"))
                            println("an error occured $e")
                        }
                    }
                }
                call.redirect(AllBookings())
            }
        }
    }

    get<Signup>{
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        if (user != null){
            call.redirect(AllBookings())
        } else{
            call.respond(FreeMarkerContent("signup.ftl", mapOf("error" to it.error)))
        }
    }
}