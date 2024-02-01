package com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.topanime.TopAnimes

import retrofit2.Response
import retrofit2.http.GET

interface TopAnimesEndPoint {
    @GET("top/anime")
    suspend fun getAllTopAnimes(): Response<TopAnimes>

}