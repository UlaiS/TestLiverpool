package mx.ulai.test.db

import android.app.Application
import androidx.annotation.NonNull
import androidx.room.Room
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDataBaseModule {

    @Provides
    @Singleton
    fun provideAppDataBase(context: Application): AppTestDB{
        return Room.databaseBuilder(context.applicationContext, AppTestDB::class.java, "super_app_baz_db")
            .allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRecordDaoDao(@NonNull AppTestDB: AppTestDB) = AppTestDB.recordDao()

    @Provides
    @Singleton
    fun provideShoppAppDaoDao(@NonNull AppTestDB: AppTestDB) = AppTestDB.shoppAppDao()


}