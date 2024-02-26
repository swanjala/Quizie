package com.example.quizie.dependencies

import com.example.quizie.data.datastore.DataRepository
import com.example.quizie.data.datastore.DataRepositoryImpl
import com.example.quizie.data.datastore.QuestionsRemoteDataStore
import com.example.quizie.data.datastore.QuestionsRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
abstract class DataStoreModule {

    @Binds
    abstract fun provideQuestionsRemoteDataStore(
        questionsRemoteDataStore: QuestionsRemoteDataStoreImpl
    ): QuestionsRemoteDataStore

    @Binds
    abstract fun dataRepository(
        dataRepository: DataRepositoryImpl
    ): DataRepository
}
