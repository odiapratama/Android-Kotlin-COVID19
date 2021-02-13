package com.covid19.monitoring.views.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.covid19.monitoring.data.repository.Repository
import com.covid19.monitoring.utils.TestCoroutineRule
import com.covid19.monitoring.utils.observeTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var viewModel: HomeViewModel

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        initMocks(this)
        viewModel = HomeViewModel(repository)
    }

    @Test
    fun `get list region data test`() {
        viewModel.fetch()
        viewModel.listRegionData.observeTest {
            verify(repository).getRegionData()
        }
    }

    @Test
    fun `get region data source`() {
        // TODO: inline fun does not tested
        /*viewModel.fetch()
        testCoroutineRule.runBlockingTest {
            verify(repository).getRegionDataSource()
        }*/
    }

    @Test
    fun `fetch data test`() {
        viewModel.fetch()
        viewModel.fetchingLiveData.observeTest {
            verify(it).onChanged(true)
        }
    }
}