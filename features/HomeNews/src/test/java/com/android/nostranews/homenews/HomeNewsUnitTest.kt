package com.android.nostranews.homenews

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.nostranews.common.utils.ViewState
import com.android.nostranews.core.AppDispatchers
import com.android.nostranews.core.di.provideApiService
import com.android.nostranews.core.di.provideOkHttpClientTest
import com.android.nostranews.core.di.provideRetrofit
import com.android.nostranews.homenews.api.HomeApi
import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import org.junit.*
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.koinApplication
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Callable

@RunWith(MockitoJUnitRunner::class)
class HomeNewsUnitTest : KoinTest {
    private lateinit var context : Context
    @get:Rule
    var rule : TestRule = InstantTaskExecutorRule()
    private var homeViewModel : HomeViewModel? = null
    @Before
    fun setup(){
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler: Callable<Scheduler?>? -> Schedulers.trampoline() }
        context = Mockito.mock(Context::class.java)
        startKoin {
            koinApplication {
                androidContext(context)
                loadKoinModules(
                    listOf(
                        module{
                            single { provideOkHttpClientTest() }
                            single { provideRetrofit(get()) }
                            single { provideApiService<HomeApi>(get()) }
                            factory { AppDispatchers(Dispatchers.Main , Dispatchers.IO) }
                            viewModel { HomeViewModel(get() , get()) }
                        }
                    )
                )
            }
        }
        homeViewModel = get<HomeViewModel>()
    }
    @Test
    fun `Fetch News API`(){
        Assert.assertNotNull(homeViewModel)
        homeViewModel?.articleResponse?.observeForever(Observer { state ->
            when (state) {
                is ViewState.Loading -> {}
                is ViewState.Success -> {
                    Assert.assertNotNull(state.data.status)
                    print("Fetch News API Succeed")
                }
                is ViewState.Failed -> {
                    Assert.fail("Fetch News API Failed")
                }
            }
        })
    }
    @After
    fun tearDown(){
        stopKoin()
    }
}