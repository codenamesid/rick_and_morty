package com.example.rickandmorty.model.repository

import com.example.rickandmorty.model.api.RickAndMortyApi
import com.example.rickandmorty.model.data.RickAndMortyResponse
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterRepositoryTest {

    private lateinit var characterRepository: CharacterRepository

    @MockK
    lateinit var api: RickAndMortyApi

    @MockK
    lateinit var rickAndMortyResponse: RickAndMortyResponse

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        characterRepository = CharacterRepository(api)
    }

    @Test
    fun `test getCharacters with valid response`() = runTest {
        coEvery { api.getCharacters("Rick") } returns rickAndMortyResponse

        val result = characterRepository.getCharacters("Rick")
        assert(result is Result.Success)
        assertEquals(rickAndMortyResponse, (result as Result.Success).data)
    }

    @Test
    fun `test getCharacters with error response`() = runTest {
        val exception = Exception("Error")
        coEvery { api.getCharacters("Rick") } throws exception

        val result = characterRepository.getCharacters("Rick")
        assert(result is Result.Error)
        assertEquals("Error fetching characters: Unknown error", (result as Result.Error).message)
    }
}