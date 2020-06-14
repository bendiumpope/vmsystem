package com.system.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class VisitRecord(
    var id: Int,
    val userId: String,
    val date: String,
    val timein: String,
    var visitedwhom: String
) : Serializable

object VisitRecords: IntIdTable(){
    val user: Column<String> = varchar("user_id", 20).index()
    var date: Column<String> = varchar("date", 255)
    var timein: Column<String> = varchar("time_in", 255)
    var visitedwhom: Column<String> = varchar("visited_whom", 255)
}