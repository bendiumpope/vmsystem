package com.system.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class VisitingSchedule(
    var id: Int,
    val userId: String,
    val office: String,
    val date: String,
    val timeduration: String
) : Serializable

object VisitingSchedules: IntIdTable(){
    val user: Column<String> = varchar("user_id", 20).index()
    var office: Column<String> = varchar("office", 255)
    var date: Column<String> = varchar("date", 255)
    var timeduration: Column<String> = varchar("time_duration", 255)
}