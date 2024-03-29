package com.example.quizie.dependencies

import android.app.Application
import com.example.quizie.QuizieApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        NetworkModule::class,
        DataStoreModule::class,
        ViewModelModule::class,
        ActivitiesModule::class,
        FragmentModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

    fun inject(quizieApp: QuizieApp)
}