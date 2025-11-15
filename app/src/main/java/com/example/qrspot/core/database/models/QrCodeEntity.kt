package com.example.qrspot.core.database.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class QrCodeEntity: RealmObject {
    @PrimaryKey var id: ObjectId = ObjectId()
    var scannedText: String = ""
    var type: String = ""
    var category: String = ""
}