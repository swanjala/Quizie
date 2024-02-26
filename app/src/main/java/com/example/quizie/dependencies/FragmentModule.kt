package com.example.quizie.dependencies

import com.example.quizie.fragments.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun contributesMainFragment(): MainFragment
}