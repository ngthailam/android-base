package vn.thailam.core

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
sealed class ResultState {
    object Loading : ResultState()
    data class Success<T>(val data: T) : ResultState()
    data class Failure(val throwable: Throwable) : ResultState()

    fun isLoading(): Boolean {
        contract {
            returns(true) implies (this@ResultState is Loading)
        }
        return this is Loading
    }

    fun isSuccess(): Boolean {
        contract {
            returns(true) implies (this@ResultState is Success<*>)
        }
        return this is Success<*>
    }

    fun isFailure(): Boolean {
        contract {
            returns(true) implies (this@ResultState is Failure)
        }
        return this is Failure
    }
}
