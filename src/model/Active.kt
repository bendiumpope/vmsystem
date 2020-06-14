package com.system.model

import io.ktor.auth.Principal
import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class Active(
    var id: Int,
    val userId: String,
    val status: String
): Serializable, Principal

object Actives: IntIdTable(){
    val user: Column<String> = varchar("user_id", 20).index()
    var status: Column<String> = varchar("status", 255)
}