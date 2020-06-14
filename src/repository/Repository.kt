package com.system.repository

import com.system.model.*

interface Repository {
    //VISITBOOKING
    suspend fun addvisitbooking(userId: String, whomtovisitValue: String, visitreasonValue: String,
                                visitingdateValue: String, visittimeValue: String, bookingstatusValue: String, dateValue: String):VisitBooking?
    suspend fun visitbooking(id:Int): VisitBooking?
    suspend fun visitbooking(userId:String): List<VisitBooking>
    suspend fun visitbookings(): List<VisitBooking>
    suspend fun updatevisitbooking(id:Int, newWhomToVisit: String, newVisitReason: String, newVisitingDate:String, newVisitTime : String, newBookingStatus: String, newDate:String)
    suspend fun removevisitbooking(id:Int):Boolean
    suspend fun removevisitbooking(id:String):Boolean
    suspend fun clear()

    //USER
    suspend fun user(userId: String, hash:String? = null): User?
    suspend fun userByEmail(email:String): User?
    suspend fun userByRole(role:String):  List<User>
    suspend fun users(): List<User>
    suspend fun removeuser(id:String): Boolean
    suspend fun createUser(user: User)
    suspend fun updateuser(userId: String, newSurName: String, newFirstName: String, newOtherName:String,
                           newEmailAddress: String, newPhoneNumber: String, newAddress: String, newRole: String, newpasswordHash: String)

    //PROFILEPIX
    suspend fun addprofilepix(userId: String, imageUrl: String)
    suspend fun profilepix(userId:String): List<ProfilePix>
    suspend fun profilepix(): List<ProfilePix>

    //ACTIVE
    suspend fun addactiveUser(userId: String, status: String)
    suspend fun activeUser(userId:String): Active?
    suspend fun activeUsers(): List<Active>
    suspend fun updateactiveUser(id:Int, userId: String, newstatus: String)

    //VISITINGRECORD
    suspend fun addvisitrecord(userId: String, date: String, timein:String, visitedwhom:String)
    suspend fun visitrecord(id:Int): VisitRecord?
    suspend fun visitrecord(userId:String): List<VisitRecord>
    suspend fun visitrecords(): List<VisitRecord>
    suspend fun updatevisitrecord(id:Int, newdate: String, newtimein:String, newvisitedwhom: String)
    suspend fun removevisitrecord(id:Int):Boolean
    suspend fun removevisitrecord(id:String):Boolean

    //VISITINGSCHEDULE
    suspend fun addvisitingschedule(userId: String, office: String, date:String, duration:String)
    suspend fun visitingschedule(id:Int): VisitingSchedule?
    suspend fun visitingschedule(userId:String): List<VisitingSchedule>
    suspend fun visitingschedule(): List<VisitingSchedule>
    suspend fun updatevisitingschedule(id:Int, newoffice: String, newdate:String, newduration: String)
    suspend fun removevisitschedule(id:Int):Boolean
    suspend fun removevisitschedule(id:String):Boolean

}