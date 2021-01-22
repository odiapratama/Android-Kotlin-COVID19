package com.covid19.monitoring.services

import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.covid19.monitoring.di.CoroutineContextProviders
import com.covid19.monitoring.utils.isOnline
import com.covid19.monitoring.utils.launchOn
import com.covid19.monitoring.utils.safeApiCall

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val coroutineContext: CoroutineContextProviders
) {
    val data = MutableLiveData<Resource<ResultType>>()

    abstract fun loadFromDB(): ResultType?

    abstract fun setLatestFetch()

    abstract fun getFetchDate(data: ResultType?): String

    abstract fun shouldFetchData(data: ResultType?, fetchDate: String): Boolean

    @WorkerThread
    abstract suspend fun remoteCall(): ResultType

    abstract fun saveRemoteData(data: ResultType)

    abstract fun clearData()

    init {
        execute()
    }

    private fun execute() {
        val dataDb = loadFromDB()
        val dateFetchDate = getFetchDate(dataDb)
        data.postValue(Resource.loading(dataDb))
        if (isOnline()) {
            if (shouldFetchData(dataDb, dateFetchDate)) fetchRemote()
            else data.postValue(Resource.success(dataDb))
        } else data.postValue(Resource.cached(dataDb))
    }

    private fun fetchRemote() {
        launchOn(coroutineContext.io) {
            safeApiCall({
                remoteCall().also { response ->
                    data.postValue(Resource.success(response))
                    saveRemoteData(response)
                    setLatestFetch()
                }
            }, {
                clearData()
                data.postValue(Resource.error(it.message ?: "Unexpected error"))
            })
        }
    }
}