package com.system.webapp

import com.system.model.EPSession
import com.system.redirect
import com.system.repository.Repository
import io.ktor.application.call
import io.ktor.freemarker.FreeMarkerContent
import io.ktor.http.content.PartData
import io.ktor.http.content.forEachPart
import io.ktor.http.content.streamProvider
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.request.receiveMultipart
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.get
import io.ktor.sessions.sessions
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.io.File
import java.io.InputStream
import java.io.OutputStream

const val PROFILE = "/profile"

@Location(PROFILE)
class Profile

fun Route.profile(db: Repository, hashFunction: (String) -> String) {

    get<Profile> {
        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }

        var profileUrls=""

        if (user == null) {
            call.redirect(Signin())
        } else {

            val profile = db.profilepix(user.userId)

            if(profile.isNotEmpty()){
               profileUrls = profile.get(profile.lastIndex).imageUrl

            }

            call.respond(
                FreeMarkerContent(
                    "profile.ftl",
                    mapOf("profileUrls" to profileUrls, "user" to user), user.userId
                )
            )

        }
    }

    post<Profile>{

        val user = call.sessions.get<EPSession>()?.let { db.user(it.userId) }
        var imageUrl : List<String>
        if (user == null) {
            call.redirect(Signin())
        } else {
            val multipart = call.receiveMultipart()
            var imageFile: String? = null
            val bookings = db.visitbookings()

            // Processes each part of the multipart input content of the user
            multipart.forEachPart { part ->
                if (part is PartData.FileItem) {
                    val ext = File(part.originalFileName).extension
                    val file = File("./resources/images/upload-${System.currentTimeMillis()}${user.userId.hashCode()}.$ext")

                    part.streamProvider().use { its -> file.outputStream().buffered().use { its.copyToSuspend(it) } }

                    imageUrl = file.toString().split("\\")

                    imageFile = imageUrl.get(2)

                    db.addprofilepix(user.userId, imageFile!!)
                    println("$imageFile")

                }

                part.dispose()
            }

            val profile = db.profilepix(user.userId)
            val profileUrls = profile.get(profile.lastIndex).imageUrl
            println("profile $profileUrls")
            call.respond(
                FreeMarkerContent("profile.ftl",
                    mapOf("profileUrls" to profileUrls, "user" to user, "imageFile" to imageFile), user.userId)
            )

        }
        call.redirect(Profile())
    }

}

suspend fun InputStream.copyToSuspend(
    out: OutputStream,
    bufferSize: Int = DEFAULT_BUFFER_SIZE,
    yieldSize: Int = 4 * 1024 * 1024,
    dispatcher: CoroutineDispatcher = Dispatchers.IO
): Long {
    return withContext(dispatcher) {
        val buffer = ByteArray(bufferSize)
        var bytesCopied = 0L
        var bytesAfterYield = 0L
        while (true) {
            val bytes = read(buffer).takeIf { it >= 0 } ?: break
            out.write(buffer, 0, bytes)
            if (bytesAfterYield >= yieldSize) {
                yield()
                bytesAfterYield %= yieldSize
            }
            bytesCopied += bytes
            bytesAfterYield += bytes
        }
        return@withContext bytesCopied
    }
}