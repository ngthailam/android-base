package vn.thailam.data.datasources.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import vn.thailam.data.datasources.local.ExampleLocalDataSource
import vn.thailam.data.models.ExampleEntity

@Dao
interface ExampleDao : ExampleLocalDataSource {
    @Query("SELECT * FROM ${ExampleEntity.TBL_NAME}")
    override fun getExamples(): Flow<List<ExampleEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override fun saveExamples(histories: List<ExampleEntity>)
}
