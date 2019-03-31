package br.com.felipebertanha.zupfilmes.retrofit.services

import br.com.felipebertanha.zupfilmes.data.model.Filme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeService {
    @GET(".")
    fun getFilmePorNome(
        @Query("apikey") apikey: String,
        @Query("t") title: String
    ): Call<Filme>
}