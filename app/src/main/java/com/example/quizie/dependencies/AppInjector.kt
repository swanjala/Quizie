package com.example.quizie.dependencies

import com.example.quizie.QuizieApp

object AppInjector {
    fun init(application: QuizieApp) {
        DaggerAppComponent.builder()
            .application(application)
            .build()
            .inject(application)
    }
}