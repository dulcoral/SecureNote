package com.coral.example.securenote.data.di

import com.coral.example.securenote.data.IDataSource
import com.coral.example.securenote.data.local.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataSourceModule {

    @Binds
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSource): IDataSource
}