package com.example.myapplication

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class Feature1ViewModel(private val repository: Feature1Repository): ViewModel() {

    private val _specialData = MutableLiveData<SpecialData?>()
    val specialData: LiveData<SpecialData?> = _specialData

    fun loadData() {
        viewModelScope.launch {
            _specialData.value = repository.fetchSpecialData()
        }
    }
}
