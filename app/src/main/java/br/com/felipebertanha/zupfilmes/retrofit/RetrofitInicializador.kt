package br.com.felipebertanha.zupfilmes.retrofit

import br.com.felipebertanha.zupfilmes.retrofit.services.FilmeService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInicializador {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://www.omdbapi.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun filmeService(): FilmeService {
        return retrofit.create(FilmeService::class.java)
    }
}