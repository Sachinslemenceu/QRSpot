package com.example.qrspot

import android.app.Application
import com.example.qrspot.core.database.models.QrCodeEntity
import com.example.qrspot.core.database.models.SettingsEntity
import com.example.qrspot.di.appModule
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class MyApp: Application() {

    companion object{
        lateinit var realm: Realm
    }
    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    QrCodeEntity::class,
                    SettingsEntity::class
                )
            )
        )
        startKoin {
            androidContext(this@MyApp)
            modules(appModule)
        }
    }
}