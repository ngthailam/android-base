package vn.thailam.presentation.example.model

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import vn.thailam.domain.models.Example
import vn.thailam.presentation.R
import javax.inject.Inject

/**
 * Like ExampleUiModel, do not use mapper everytime
 * Mapper is only helpful if you need to inject other classes or context
 */
class ExampleUiModelMapper @Inject constructor(
    @ApplicationContext
    private val context: Context,
) {
    /**
     * If you want to add headers or footers, either this
     * or list in view model is a good place
     */
    fun mapListToUiModel(models: List<Example>): List<ExampleUiModel> {
        return models.map { mapToUiModel(it) }
    }

    fun mapToUiModel(model: Example): ExampleUiModel {
        return ExampleUiModel(
            title = model.id,
            color = context.getColor(R.color.purple_200),
        )
    }
}
