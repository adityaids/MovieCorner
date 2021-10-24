package com.indeep.core.di

import androidx.room.Room
import com.indeep.core.BuildConfig
import com.indeep.core.data.MovieRepository
import com.indeep.core.data.domain.repository.IMovieRepository
import com.indeep.core.data.source.local.LocalDataSource
import com.indeep.core.data.source.local.room.MovieDb
import com.indeep.core.data.source.remote.RemoteDataSource
import com.indeep.core.data.source.remote.network.ApiService
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieDb>().movieDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("gamingcorner".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            MovieDb::class.java, "movieapp.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}

val networkModule = module {
    single {
        val hostname = "api.rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/+WSYXXW0rd5TnILDGuJvshU5aExcOMlLxvQBPOT4PS0=")
            .add(hostname, "sha256/hS5jJ4P+iQUErBkvoWBQOd1T7VOAYlOVegvv1iMzpxA=")
            .add(hostname, "sha256/RI9CUmPUOpUk2vdVMSZDWj+wtoQO5k9MSCSM9w4grmU=")
            .add(hostname,"sha256/ FEzVOUp4dF3gI0ZVPRJhFbSJVXR+uQmMH65xhs1glH4=")
            .build()

        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_API)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<IMovieRepository> { MovieRepository(get(), get()) }
}