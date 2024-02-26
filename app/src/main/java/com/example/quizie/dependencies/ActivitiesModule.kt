package com.example.quizie.dependencies

import com.example.quizie.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class ActivitiesModule {
    @ContributesAndroidInjector
    abstract fun contributesMainActivity():MainActivity
}
