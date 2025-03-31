package com.bookxpert.assignment.core.di

import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.preferencesDataStoreFile
import com.bookxpert.assignment.BuildConfig
import com.bookxpert.assignment.core.networking.ApiInterface
import com.bookxpert.assignment.core.constants.AppConstants
import com.bookxpert.assignment.core.roomdb.ObjectsDao
import com.bookxpert.assignment.core.roomdb.ObjectsDatabase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApiInterfaceProvider {
    @Singleton
    @Provides
    fun providesApiInterface(): ApiInterface {

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        return Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient.build())
            .build()
            .create(ApiInterface::class.java)
    }

    @Singleton
    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = PreferenceDataStoreFactory.create {
        context.preferencesDataStoreFile(AppConstants.NOTIFICATION_PREFERENCES)
    }

    @Singleton
    @Provides
    fun providesRoomDao(@ApplicationContext context: Context): ObjectsDao{
        return ObjectsDatabase.getInstance(context).objectsDao()
    }
}