package mx.ulai.test.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mx.ulai.test.db.dao.*
import mx.ulai.test.model.*

@Database(
    entities = [
        ShoppAppResponse::class,
        Records::class,
    ], version = 2
)
@TypeConverters(TypeConverter::class)
abstract class AppTestDB: RoomDatabase() {
    abstract fun recordDao(): RecordDao
    abstract fun shoppAppDao(): ShoppAppDao

}