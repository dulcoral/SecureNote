package com.coral.example.securenote.data.di

import com.coral.example.securenote.domain.repositories.IRepository
import com.coral.example.securenote.domain.repositories.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(implRepository: Repository): IRepository
}