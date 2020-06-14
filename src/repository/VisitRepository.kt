package com.system.repository

import com.system.model.*
import com.system.model.Actives.status
import com.system.repository.DatabaseFactory.dbQuery
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.experimental.transaction
import java.lang.IllegalArgumentException

class VisitRepository : Repository {

    //VISITBOOKING
    override suspend fun addvisitbooking(
        userId: String,
        whomtovisitValue: String,
        visitreasonValue: String,
        visitingdateValue: String,
        visittimeValue: String,
        bookingstatusValue: String,
        dateValue: String
    ) = dbQuery {
        val insertStatement = VisitBookings.insert {
            it[user] = userId
            it[whomtovisit] = whomtovisitValue
            it[visitreason] = visitreasonValue
            it[visitingdate] = visitingdateValue
            it[visittime] = visittimeValue
            it[bookingstatus] = bookingstatusValue
            it[date] = dateValue
        }
        val result = insertStatement.resultedValues?.get(0)
        if (result != null) {
            toVisitBooking(result)
        } else {
            null
        }
    }

    override suspend fun visitbooking(id: Int): VisitBooking? = dbQuery {
        VisitBookings.select {
            (VisitBookings.id eq id)
        }.mapNotNull { toVisitBooking(it) }
            .singleOrNull()
    }

    override suspend fun visitbooking(userId: String): List<VisitBooking> = dbQuery {
        VisitBookings.select {
            (VisitBookings.user eq userId)
        }.map { toVisitBooking(it) }
    }

    override suspend fun visitbookings(): List<VisitBooking> = dbQuery {
        VisitBookings.selectAll().map { toVisitBooking(it) }
    }

    override suspend fun updatevisitbooking(
        id: Int,
        newWhomToVisit: String,
        newVisitReason: String,
        newVisitingDate: String,
        newVisitTime: String,
        newBookingStatus: String,
        newDate: String
    ) = dbQuery {
        VisitBookings.update({ VisitBookings.id eq id }) {
            it[whomtovisit] = newWhomToVisit
            it[visitreason] = newVisitReason
            it[visitingdate] = newVisitingDate
            it[visittime] = newVisitTime
            it[bookingstatus] = newBookingStatus
            it[date] = newDate

        }
        Unit
    }

    override suspend fun removevisitbooking(id: Int): Boolean {
        if (visitbooking(id) == null) {
            throw IllegalArgumentException("No phrase found for id $id.")
        }
        return dbQuery {
            VisitBookings.deleteWhere { VisitBookings.id eq id } > 0
        }
    }

    override suspend fun removevisitbooking(id: String): Boolean {
        return removevisitbooking(id.toInt())
    }

    override suspend fun clear() {
        VisitBookings.deleteAll()
    }


    //USER
    override suspend fun user(userId: String, hash: String?): User? {
        val user = dbQuery {
            Users.select {
                (Users.id eq userId)
            }.mapNotNull { toUser(it) }
                .singleOrNull()
        }
        return when {
            user == null -> null
            hash == null -> user
            user.passwordHash == hash -> user
            else -> null
        }
    }


    override suspend fun userByEmail(email: String): User? = dbQuery {
        Users.select { Users.emailAddress.eq(email) }
            .map {
                User(
                    it[Users.id],
                    it[Users.surName],
                    it[Users.firstName],
                    it[Users.otherName],
                    email,
                    it[Users.phoneNumber],
                    it[Users.address],
                    it[Users.role],
                    it[Users.passwordHash]
                )
            }.singleOrNull()
    }

    override suspend fun userByRole(role: String): List<User> = dbQuery {
        Users.select {
            (Users.role eq role)
        }.map { toUser(it) }
    }


    override suspend fun users(): List<User> = dbQuery {
        Users.selectAll().map { toUser(it) }
    }

    override suspend fun removeuser(userId: String): Boolean {
        if (visitbooking(userId) == null) {
            throw IllegalArgumentException("No User found for id $userId.")
        }
        return dbQuery {
            Users.deleteWhere { Users.id eq userId } > 0
        }
    }


    override suspend fun createUser(user: User) = dbQuery {
        Users.insert {
            it[id] = user.userId
            it[surName] = user.surName
            it[firstName] = user.firstName
            it[otherName] = user.otherName
            it[emailAddress] = user.emailAddress
            it[phoneNumber] = user.phoneNumber
            it[address] = user.address
            it[role] = user.role
            it[passwordHash] = user.passwordHash
        }
        Unit
    }

    override suspend fun updateuser(
        userId: String,
        newSurName: String,
        newFirstName: String,
        newOtherName: String,
        newEmailAddress: String,
        newPhoneNumber: String,
        newAddress: String,
        newRole: String,
        newpasswordHash: String
    ) = dbQuery {
        Users.update({ Users.id eq userId }) {
            it[surName] = newSurName
            it[firstName] = newFirstName
            it[otherName] = newOtherName
            it[emailAddress] = newEmailAddress
            it[phoneNumber] = newPhoneNumber
            it[address] = newAddress
            it[role] = newRole
            it[passwordHash] = newpasswordHash

        }
        Unit
    }


    //PROFILEPIX
    override suspend fun addprofilepix(userId: String, imageUrlValue: String) {
        transaction {
            ProfilePixs.insert {
                it[user] = userId
                it[imageUrl] = imageUrlValue

            }
        }
    }

    override suspend fun profilepix(userId: String): List<ProfilePix> = dbQuery {
        ProfilePixs.select {
            (ProfilePixs.user eq userId)
        }.map { toProfilePix(it) }
    }

    override suspend fun profilepix(): List<ProfilePix> = dbQuery {
        ProfilePixs.selectAll().map { toProfilePix(it) }
    }

    //ACTIVEUSER
    override suspend fun addactiveUser(userId: String, statusValue: String) {
        transaction {
            Actives.insert {
                it[user] = userId
                it[status] = statusValue

            }
        }
    }

    override suspend fun activeUser(userId: String): Active? = dbQuery {
        Actives.select {
            (Actives.user eq userId)
        }.mapNotNull { toActive(it) }
            .singleOrNull()
    }

    override suspend fun activeUsers(): List<Active> = dbQuery {
        Actives.selectAll().map { toActive(it) }
    }


    override suspend fun updateactiveUser(id: Int, userId: String, newstatus: String) = dbQuery {
        Actives.update({ Actives.id eq id }) {
            it[user] = userId
            it[status] = newstatus
        }
        Unit
    }


    //VISITRECORD
    override suspend fun addvisitrecord(
        userId: String,
        dateValue: String,
        timeinValue: String,
        visitedwhomValue: String
    ) {
        transaction {
            VisitRecords.insert {
                it[user] = userId
                it[date] = dateValue
                it[timein] = timeinValue
                it[visitedwhom] = visitedwhomValue

            }
        }
    }

    override suspend fun visitrecord(id: Int): VisitRecord? = dbQuery {
        VisitRecords.select {
            (VisitRecords.id eq id)
        }.mapNotNull { toVisitRecord(it) }
            .singleOrNull()
    }

    override suspend fun visitrecord(userId: String): List<VisitRecord> = dbQuery {
        VisitRecords.select {
            (VisitRecords.user eq userId)
        }.map { toVisitRecord(it) }
    }

    override suspend fun visitrecords(): List<VisitRecord> = dbQuery {
        VisitRecords.selectAll().map { toVisitRecord(it) }
    }

    override suspend fun updatevisitrecord(id: Int, newdate: String, newtimein: String, newvisitedwhom: String) =
        dbQuery {
            VisitRecords.update({ VisitRecords.id eq id }) {
                it[date] = newdate
                it[timein] = newtimein
                it[visitedwhom] = newvisitedwhom
            }
            Unit
        }

    override suspend fun removevisitrecord(id: Int): Boolean {
        if (visitrecord(id) == null) {
            throw IllegalArgumentException("No phrase found for id $id.")
        }
        return dbQuery {
            VisitRecords.deleteWhere { VisitRecords.id eq id } > 0
        }    }

    override suspend fun removevisitrecord(id: String): Boolean {
        return removevisitrecord(id.toInt())
    }


    //VISITINGSCHEDULE
    override suspend fun addvisitingschedule(
        userId: String,
        officeValue: String,
        dateValue: String,
        durationValue: String
    ) {
        transaction {
            VisitingSchedules.insert {
                it[user] = userId
                it[office] = officeValue
                it[date] = dateValue
                it[timeduration] = durationValue

            }
        }
    }

    override suspend fun visitingschedule(id: Int): VisitingSchedule? = dbQuery {
        VisitingSchedules.select {
            (VisitingSchedules.id eq id)
        }.mapNotNull { toVisitingSchedule(it) }
            .singleOrNull()
    }

    override suspend fun visitingschedule(userId: String): List<VisitingSchedule> = dbQuery {
        VisitingSchedules.select {
            (VisitingSchedules.user eq userId)
        }.map { toVisitingSchedule(it) }
    }

    override suspend fun visitingschedule(): List<VisitingSchedule> = dbQuery {
        VisitingSchedules.selectAll().map { toVisitingSchedule(it) }
    }

    override suspend fun updatevisitingschedule(id: Int, newoffice: String, newdate: String, newduration: String) =
        dbQuery {
            VisitingSchedules.update({ VisitingSchedules.id eq id }) {
                it[office] = newoffice
                it[date] = newdate
                it[timeduration] = newduration
            }
            Unit
        }


    override suspend fun removevisitschedule(id: Int): Boolean {
        if (visitingschedule(id) == null) {
            throw IllegalArgumentException("No phrase found for id $id.")
        }
        return dbQuery {
            VisitingSchedules.deleteWhere { VisitingSchedules.id eq id } > 0
        }
    }

    override suspend fun removevisitschedule(id: String): Boolean {
        return removevisitschedule(id.toInt())
    }


    private fun toVisitBooking(row: ResultRow): VisitBooking =
        VisitBooking(
            id = row[VisitBookings.id].value,
            userId = row[VisitBookings.user],
            whomtovisit = row[VisitBookings.whomtovisit],
            visitreason = row[VisitBookings.visitreason],
            visitingdate = row[VisitBookings.visitingdate],
            visittime = row[VisitBookings.visittime],
            bookingstatus = row[VisitBookings.bookingstatus],
            date = row[VisitBookings.date]

        )

    private fun toUser(row: ResultRow): User =
        User(
            userId = row[Users.id],
            surName = row[Users.surName],
            firstName = row[Users.firstName],
            otherName = row[Users.otherName],
            emailAddress = row[Users.emailAddress],
            phoneNumber = row[Users.phoneNumber],
            address = row[Users.role],
            role = row[Users.address],
            passwordHash = row[Users.passwordHash]
        )

    private fun toProfilePix(row: ResultRow): ProfilePix =
        ProfilePix(
            id = row[ProfilePixs.id].value,
            userId = row[ProfilePixs.user],
            imageUrl = row[ProfilePixs.imageUrl]
        )

    private fun toActive(row: ResultRow): Active =
        Active(
            id = row[Actives.id].value,
            userId = row[Actives.user],
            status = row[Actives.status]
        )

    private fun toVisitRecord(row: ResultRow): VisitRecord =
        VisitRecord(
            id = row[VisitRecords.id].value,
            userId = row[VisitRecords.user],
            date = row[VisitRecords.date],
            timein = row[VisitRecords.timein],
            visitedwhom = row[VisitRecords.visitedwhom]
        )


    private fun toVisitingSchedule(row: ResultRow): VisitingSchedule =
        VisitingSchedule(
            id = row[VisitingSchedules.id].value,
            userId = row[VisitingSchedules.user],
            office = row[VisitingSchedules.office],
            date = row[VisitingSchedules.date],
            timeduration = row[VisitingSchedules.timeduration]
        )
}