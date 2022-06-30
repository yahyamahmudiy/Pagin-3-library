package com.example.paging3.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.paging3.model.CharacterData
import com.example.paging3.network.RetroService
import com.example.paging3.network.Retroinstance
import com.example.paging3.utils.CharacterPagingSource
import kotlinx.coroutines.flow.Flow


class MainActivityViewModel : ViewModel() {
    lateinit var retroService: RetroService

    init {
        retroService = Retroinstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(pageSize = 34, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(
            viewModelScope
        )
    }

}