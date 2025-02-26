package com.example.rickandmorty.viewmodel

import com.example.rickandmorty.model.data.Character
import com.example.rickandmorty.model.data.RickAndMortyResponse
import com.example.rickandmorty.model.repository.CharacterRepository
import com.example.rickandmorty.model.repository.Result
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CharacterViewModelTest {

    private lateinit var characterViewModel: CharacterViewModel

    @MockK
    lateinit var characterRepository: CharacterRepository

    @MockK
    lateinit var rickAndMortyResponse: RickAndMortyResponse

    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        characterViewModel = CharacterViewModel(characterRepository)
        every { rickAndMortyResponse.results } returns emptyList()
        coEvery { characterRepository.getCharacters("Rick") } returns Result.Success(rickAndMortyResponse)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test searchCharacters with valid response`() = runTest {
        characterViewModel.searchCharacters("Rick")
        assertEquals(LoadingState.Success, characterViewModel.loadingState.value)
        assertEquals(emptyList<Character>(), characterViewModel.characters.value)
    }

    @Test
    fun `test searchCharacters with error response`() = runTest {
        coEvery { characterRepository.getCharacters("Rick") } returns Result.Error("Error fetching characters: Unknown error")
        characterViewModel.searchCharacters("Rick")
        assertEquals(LoadingState.Error("Error fetching characters: Unknown error"), characterViewModel.loadingState.value)
    }
}