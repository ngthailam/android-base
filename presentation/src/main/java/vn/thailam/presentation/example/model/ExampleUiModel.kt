package vn.thailam.presentation.example.model

import androidx.annotation.ColorInt

/**
 * Note:
 * Only use UiModel if you feel necessary
 * E.g 1 model but can have different uis, the ui and model has different mappable properties
 * that can be reused, etc
 *
 * Do not use this everytime, this would increase boilerplate code by a bunch
 */
data class ExampleUiModel(
    val title: String,
    @ColorInt
    val color: Int,
)
