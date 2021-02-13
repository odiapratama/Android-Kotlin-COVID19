package com.covid19.monitoring.views.ui.news

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
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.initMocks
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var viewModel: NewsViewModel

    @Mock
    private lateinit var repository: Repository

    @Before
    fun setup() {
        initMocks(this)
        viewModel = NewsViewModel(repository)
    }

    @Test
    fun `get daily update data test`() {
        viewModel.fetchData()
        viewModel.dailyUpdateData.observeTest {
            verify(repository).getDailyUpdateData()
        }
    }

    @Test
    fun `get global data test`() {
        viewModel.fetchData()
        viewModel.globalData.observeTest {
            verify(repository).getGlobalData()
        }
    }

    @Test
    fun `get daily update data source test`() {
        // TODO: inline fun does not tested
        /*viewModel.fetchData()
        viewModel.fetchingLiveData.observeTest {
            testCoroutineRule.runBlockingTest {
                verify(repository).getDailyUpdateDataSource()
            }
        }*/
    }

    @Test
    fun `fetch data test`() {
        viewModel.fetchData()
        viewModel.fetchingLiveData.observeTest {
            Mockito.verify(it).onChanged(true)
        }
    }
}