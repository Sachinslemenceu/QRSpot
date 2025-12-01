package com.example.qrspot.core.database.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SettingsEntity: RealmObject {
    @PrimaryKey
    var id: String = "APP_SETTINGS"
    var vibrate: Boolean = true
    var beep: Boolean = true
}
