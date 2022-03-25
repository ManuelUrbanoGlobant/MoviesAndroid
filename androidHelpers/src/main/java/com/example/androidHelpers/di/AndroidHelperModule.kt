package com.example.androidHelpers.di

import android.content.Context
import com.example.androidHelpers.preferences.StoreHelper
import com.example.androidHelpers.preferences.StoreHelperImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AndroidHelperModule {

    @Singleton
    @Provides
    fun provideStoreHelper(@ApplicationContext context: Context) : StoreHelper = StoreHelperImpl(context)

}