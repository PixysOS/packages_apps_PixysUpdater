package org.pixysos.updater.core.network.retrofit

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.pixysos.updater.BuildConfig
import org.pixysos.updater.core.data.model.UpdatePackage
import org.pixysos.updater.core.network.UpdaterNetworkDataSource
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Inject
import javax.inject.Singleton

private interface RetrofitUpdaterNetworkApi {
    @GET(value = "{device}/${BuildConfig.ANDROID_VERSION_NAME}/{buildEdition}")
    suspend fun getUpdatePackageInfo(
        @Path(value = "device") device: String,
        @Path(value = "buildEdition") buildEdition: String
    ): UpdatePackage
}

private const val UpdaterBaseUrl = BuildConfig.BASE_URL

@Singleton
class RetrofitUpdaterNetwork @Inject constructor(

) : UpdaterNetworkDataSource {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    @OptIn(ExperimentalSerializationApi::class)
    private val networkApi = Retrofit.Builder()
        .baseUrl(UpdaterBaseUrl)
        .client(
            OkHttpClient.Builder().addInterceptor(
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                }
            ).build()
        )
        .addConverterFactory(
            json.asConverterFactory(
                contentType = "application/json".toMediaType()
            )
        )
        .build()
        .create(RetrofitUpdaterNetworkApi::class.java)

    override suspend fun getUpdatePackageInfo(device: String, buildEdition: String): UpdatePackage =
        networkApi.getUpdatePackageInfo(device = device, buildEdition = buildEdition)
}