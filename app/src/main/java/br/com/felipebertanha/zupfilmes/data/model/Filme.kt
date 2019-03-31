package br.com.felipebertanha.zupfilmes.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity
data class Filme(
    @PrimaryKey
    @SerializedName("imdbID")
    val imdbID: String,
    @SerializedName("Title")
    val titulo: String,
    @SerializedName("Year")
    val anoLancamento: String,
    @SerializedName("Director")
    val diretor: String,
    @SerializedName("Plot")
    val resumo: String,
    @SerializedName("Poster")
    val poster: String,
    @SerializedName("imdbRating")
    val imdbNota: String,
    var isFavorito: Boolean
) : Serializable


