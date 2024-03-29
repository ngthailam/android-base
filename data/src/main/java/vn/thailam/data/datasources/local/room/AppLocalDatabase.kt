package vn.thailam.data.datasources.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import vn.thailam.data.datasources.local.room.AppLocalDatabase.Companion.VERSION
import vn.thailam.data.datasources.local.room.dao.ExampleDao
import vn.thailam.data.models.ExampleEntity

@Database(entities = [ExampleEntity::class], version = VERSION)
abstract class AppLocalDatabase : RoomDatabase() {
    abstract fun exampleDao(): ExampleDao

    companion object {
        const val DB_NAME = "app_db"
        const val VERSION = 1
    }
}
