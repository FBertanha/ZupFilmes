package br.com.felipebertanha.zupfilmes.retrofit.services

import br.com.felipebertanha.zupfilmes.data.Filme
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmeService {
    @GET(".")
    fun filme(
        @Query("apikey") uid: String,
        @Query("t") title: String
    ): Call<Filme>
}