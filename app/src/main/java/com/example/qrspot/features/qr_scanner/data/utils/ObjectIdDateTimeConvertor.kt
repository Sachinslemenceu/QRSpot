package com.example.qrspot.features.qr_scanner.data.utils

import org.mongodb.kbson.ObjectId
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date

object ObjectIdDateTimeConvertor {
    // Convert ObjectId timestamp to LocalDateTime
    fun objectIdToLocalDateTime(objectId: ObjectId): LocalDateTime {
        val secondsSinceEpoch = objectId.timestamp.toLong()
        val instant = Instant.ofEpochSecond(secondsSinceEpoch)
        return instant.atZone(ZoneId.systemDefault()).toLocalDateTime()
    }

    // Convert LocalDateTime to Date
    fun localDateTimeToDate(localDateTime: LocalDateTime): Date {
        val instant: Instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant()
        return Date.from(instant)
    }
}