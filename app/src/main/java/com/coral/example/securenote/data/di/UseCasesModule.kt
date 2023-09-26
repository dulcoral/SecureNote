package com.coral.example.securenote.data.di

import com.coral.example.securenote.domain.repositories.IRepository
import com.coral.example.securenote.domain.usecases.AddNoteUseCase
import com.coral.example.securenote.domain.usecases.DeleteNoteUseCase
import com.coral.example.securenote.domain.usecases.EditNoteUseCase
import com.coral.example.securenote.domain.usecases.GetAllNotesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
object UseCasesModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetAllNotesUseCase(repository: IRepository): GetAllNotesUseCase {
        return GetAllNotesUseCase(repository)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideAddNoteUseCase(repository: IRepository): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideDeleteNoteUseCase(repository: IRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    @Provides
    @ActivityRetainedScoped
    fun provideEditNoteUseCase(repository: IRepository): EditNoteUseCase {
        return EditNoteUseCase(repository)
    }
}