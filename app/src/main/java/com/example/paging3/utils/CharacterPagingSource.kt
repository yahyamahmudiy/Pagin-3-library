package com.example.paging3.utils

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.paging3.model.CharacterData
import com.example.paging3.network.RetroService

class CharacterPagingSource(var apiService: RetroService) : PagingSource<Int, CharacterData>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterData>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterData> {
       return try {
           val nextPage:Int = params.key ?: FIRST_PAGE_INDEX
           val response = apiService.getDataFromApi(nextPage)
           var nextPageNumber:Int? = null
           if (response.info.next !=null){
               val uri = Uri.parse(response.info.next)
               val nextPageQuery = uri.getQueryParameter("page")
               nextPageNumber = nextPageQuery!!.toInt()
           }

           LoadResult.Page(data = response.results, prevKey = null, nextKey = nextPageNumber)
       }catch (e:Exception){
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }


}