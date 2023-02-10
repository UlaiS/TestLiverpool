package mx.ulai.test.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mx.ulai.test.model.ShoppAppResponse

@Dao
interface ShoppAppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(records: ShoppAppResponse)

    @Query("SELECT * FROM records WHERE page = :page")
    fun selectFromRecordsPage(page: Int): ShoppAppResponse?
    @Query("SELECT * FROM records WHERE page = :page")
    fun selectFromLiveData(page: Int): LiveData<ShoppAppResponse>
}