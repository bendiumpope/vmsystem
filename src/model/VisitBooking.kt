package com.system.model

import org.jetbrains.exposed.dao.IntIdTable
import org.jetbrains.exposed.sql.Column
import java.io.Serializable

data class VisitBooking(
    var id: Int,
    val userId: String,
    var whomtovisit: String,
    var visitreason : String,
    var visitingdate: String,
    var visittime:String,
    var bookingstatus:String,
    val date: String=""
    ) : Serializable

object VisitBookings: IntIdTable(){
    val user: Column<String> = varchar("user_id", 20).index()
    var whomtovisit: Column<String> = varchar("whom_to_visit", 255)
    var visitreason: Column<String> = varchar("visit_reason", 255)
    var visitingdate: Column<String> = varchar("visiting_date", 255)
    var visittime: Column<String> = varchar("visit_time", 255)
    var bookingstatus: Column<String> = varchar("booking_status", 255)
    var date: Column<String> = varchar("date", 255)
}