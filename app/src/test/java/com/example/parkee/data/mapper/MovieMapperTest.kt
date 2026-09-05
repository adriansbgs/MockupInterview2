package com.example.parkee.data.mapper

import com.example.parkee.data.remote.dto.MovieDto
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class MovieMapperTest {

    private fun dto(
        id: Int = 0,
        title: String = "",
        posterPath: String? = null,
        overview: String = "",
        releaseDate: String = ""
    ) = MovieDto(
        id = id,
        title = title,
        posterPath = posterPath,
        backdropPath = null,
        overview = overview,
        releaseDate = releaseDate,
        voteAverage = 8.0
    )

    @Test
    fun `poster path null menghasilkan posterUrl null`() {
        val result = dto(posterPath = null).toDomain()
        assertNull(result.posterUrl)
    }

    @Test
    fun `overview kosong diganti teks default`() {
        val result = dto(overview = "").toDomain()
        assertEquals("No overview", result.overview)
    }

    @Test
    fun `posterPath terisi menghasilkan url lengkap`() {
        val result = dto(posterPath = "/abc.jpg").toDomain()
        assertEquals("https://image.tmdb.org/t/p/w342/abc.jpg", result.posterUrl)
    }

    @Test
    fun `release date kosong jadi dash`() {
        val result = dto(releaseDate = "").toDomain()
        assertEquals("-", result.releaseDate)
    }

    @Test
    fun `release date valid diformat`() {
        val result = dto(releaseDate = "2022-01-01").toDomain()
        assertEquals("1 Jan 2022", result.releaseDate)
    }

    @Test
    fun `releaseDate format tak dikenal dikembalikan apa adanya`() {
        val result = dto(releaseDate = "bukan tanggal").toDomain()
        assertEquals("bukan tanggal", result.releaseDate)
    }
}