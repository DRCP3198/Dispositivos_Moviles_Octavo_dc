package com.examenp.dillan.dispositivosm_recyclewie_dc.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.usercase.JikanAnimeUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel: ViewModel(){

    val anime=MutableLiveData<FullInfoAnimeLG>()
    val error= MutableLiveData<String>()
    fun loadInfoAnime(animeID:Int){
        Log.d("UCE",animeID.toString())

        viewModelScope.launch(Dispatchers.Main) {

            val resp= withContext(Dispatchers.IO){
                JikanAnimeUserCase().getFullAnimeInfo(animeID)
            }

            resp.onSuccess {
                anime.postValue(it)
            }
            resp.onFailure {
                error.postValue(it.message.toString())
            }


        }
    }
}