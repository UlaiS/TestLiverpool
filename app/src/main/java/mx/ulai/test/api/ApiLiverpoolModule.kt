package mx.ulai.test.api

import android.app.Application
import dagger.Module
import dagger.Provides
import mx.ulai.test.util.Constants
import mx.ulai.test.util.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class ApiLiverpoolModule {
    @Provides
    @Singleton
    fun provideApiLiverpool(context: Application): ApiLiverpool {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)


        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .client(httpClient.build()).build()
            .create(ApiLiverpool::class.java)
    }
}