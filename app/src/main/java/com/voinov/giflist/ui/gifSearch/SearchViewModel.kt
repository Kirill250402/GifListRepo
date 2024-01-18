package com.voinov.giflist.ui.gifSearch

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.voinov.giflist.data.ApiResult
import com.voinov.giflist.data.apiService.DataResponse
import com.voinov.giflist.ui.gifListFeature.CallError
import com.voinov.giflist.ui.usecases.SearchGifUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val searchGifUseCase: SearchGifUseCase,
) : ViewModel() {

    private val _gifList = MutableStateFlow(DataResponse(emptyList()))
    val gifList: Flow<DataResponse>
        get() = _gifList

    private val _error = MutableSharedFlow<CallError>()
    val error: Flow<CallError>
        get() = _error

    val isLoading = MutableStateFlow(false)

    fun searchGifByQuery(searchQuery: String) {
        viewModelScope.launch {
            isLoading.emit(true)
            when (val result = searchGifUseCase.invoke(searchQuery)) {
                is ApiResult.ApiSuccess -> handleSuccessResult(result.data)
                is ApiResult.ApiException -> handleExceptionResult(result.e)
                is ApiResult.ApiError -> handleError(result.code, result.message)
            }
            isLoading.emit(false)
        }
    }

    private fun handleError(code: Int, message: String?) {
        val errorMessage = "${message.toString()} with code $code"
        Log.i("Error message: ", errorMessage)
        viewModelScope.launch {
            _error.emit(CallError.CallErrorWithMessage(errorMessage))
        }
    }

    private fun handleExceptionResult(t: Throwable) {
        Log.i("Exception message: ", t.message.toString())
        viewModelScope.launch {
            _error.emit(CallError.CallErrorWithMessage(t.message.toString()))
        }
    }

    private fun handleSuccessResult(data: DataResponse) {
        viewModelScope.launch {
            _gifList.emit(data)
        }
    }
}