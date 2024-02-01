package com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.anime.FullInfoAnime

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AnimeEndPoint {

    @GET("anime/{id}/full") //endPoint
    fun getAnimeFullInformation(@Path("id") name: Int): Response<FullInfoAnime>
    //El Response me ayuda a que me devuelva una cabecera y el cuerpo


}