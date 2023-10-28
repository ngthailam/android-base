package vn.thailam.data.models

import vn.thailam.domain.models.Example

data class ExampleEntity(
    val id: String? = null,
) {
    fun toModel(): Example {
        return Example(
            id = id.orEmpty()
        )
    }
}