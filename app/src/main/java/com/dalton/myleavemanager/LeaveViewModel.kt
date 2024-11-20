package com.dalton.myleavemanager

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dalton.myleavemanager.database.room.LeaveDatabase
import com.dalton.myleavemanager.database.room.entities.LeaveRecordEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LeaveRecordViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: LeaveRecordRepository

    val allLeaveRecords: Flow<List<LeaveRecordEntity>>

    private val _leaveRecordById = MutableLiveData<LeaveRecordEntity>()
    val leaveRecordById: LiveData<LeaveRecordEntity> get() = _leaveRecordById

    init {
        val dao = LeaveDatabase.getDatabase(application).leaveRecordDao()
        repository = LeaveRecordRepository(dao)
        allLeaveRecords = repository.allLeaveRecords
    }

    fun getLeaveRecordById(id: Long) = viewModelScope.launch {
        val record = repository.getLeaveRecordById(id)
        _leaveRecordById.postValue(record ?: LeaveRecordEntity())
    }

    fun insert(record: LeaveRecordEntity) = viewModelScope.launch {
        repository.insert(record)
    }

    fun delete(record: LeaveRecordEntity) = viewModelScope.launch {
        repository.delete(record)
    }

    fun update(record: LeaveRecordEntity) = viewModelScope.launch {
        repository.update(record)
    }

}




