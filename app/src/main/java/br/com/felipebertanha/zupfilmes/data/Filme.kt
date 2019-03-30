package br.com.felipebertanha.zupfilmes.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Filme(
    @SerializedName("Title") val titulo: String,
    @SerializedName("Year") val anoLancamento: String,
    @SerializedName("Director") val diretor: String,
    @SerializedName("Plot") val resumo: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbRating") val imdbNota: String,
    @SerializedName("imdbID") val imdbID: String
) : Serializable


