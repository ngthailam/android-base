package vn.thailam.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import vn.thailam.data.models.ExampleEntity.Companion.TBL_NAME
import vn.thailam.domain.models.Example

@Entity(tableName = TBL_NAME)
data class ExampleEntity(
    @PrimaryKey
    @ColumnInfo(name = COL_ID_NAME)
    val id: String,
) {
    fun toModel(): Example {
        return Example(
            id = id.orEmpty()
        )
    }

    companion object {
        const val TBL_NAME = "example"
        const val COL_ID_NAME = "id"
    }
}