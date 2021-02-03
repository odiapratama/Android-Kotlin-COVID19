package com.covid19.monitoring.services

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.covid19.monitoring.utils.isOnline
import com.covid19.monitoring.utils.launchOn
import com.covid19.monitoring.utils.safeApiCall
import kotlinx.coroutines.launch

abstract class NetworkBoundResource<RequestType, ResultType>(
    private val coroutineContext: CoroutineContextProviders
) {
    val data = MutableLiveData<Resource<ResultType>>()

    @WorkerThread
    abstract suspend fun loadFromDB(): ResultType?

    abstract fun setLatestFetch()

    abstract fun getFetchDate(data: ResultType?): String

    abstract fun shouldFetchData(data: ResultType?, fetchDate: String): Boolean

    @WorkerThread
    abstract suspend fun remoteCall(): RequestType

    @WorkerThread
    abstract suspend fun saveRemoteData(data: RequestType)

    @WorkerThread
    abstract suspend fun clearData()

    init {
        execute()
    }

    private fun execute() {
        launchOn(coroutineContext.io) {
            launch {
                val dataDb = loadFromDB()
                val dateFetchDate = getFetchDate(dataDb)
                data.postValue(Resource.loading(dataDb))
                if (isOnline()) {
                    if (shouldFetchData(dataDb, dateFetchDate)) {
                        safeApiCall({
                            remoteCall().also { response ->
                                saveRemoteData(response)
                                data.postValue(Resource.success(loadFromDB()))
                                setLatestFetch()
                            }
                        }, {
                            launch { clearData() }
                            data.postValue(Resource.error(it.message ?: "Unexpected error"))
                        })
                    } else data.postValue(Resource.success(dataDb))
                } else data.postValue(Resource.cached(dataDb))
            }
        }
    }
}