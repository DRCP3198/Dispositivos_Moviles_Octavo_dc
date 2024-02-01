package com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.endpoints

import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.topanime.Data
import com.examenp.dillan.dispositivosm_recyclewie_dc.data.network.entities.anime.FullInfoAnime
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullInfoAnimeLG
import com.examenp.dillan.dispositivosm_recyclewie_dc.logic.entities.FullTopAnimeLG


//Así construyo una función de extención
fun FullInfoAnime.getFullInfoAnimeLG() = FullInfoAnimeLG(
    this.data.mal_id,
    this.data.title_english,
    this.data.images.jpg.small_image_url,
    this.data.images.jpg.large_image_url,
    this.data.synopsis
)
fun Data.getFullInfoAnimeLG() = FullInfoAnimeLG(
    this.mal_id,
    this.title_english,
    this.images.jpg.small_image_url,
    this.images.jpg.large_image_url,
    this.synopsis
)

