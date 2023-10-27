package vn.thailam.data.models

import vn.thailam.domain.models.History

data class HistoryEntity(
    val id: String? = null,
) {
    fun toModel(): History {
        return History(
            id = id.orEmpty()
        )
    }
}