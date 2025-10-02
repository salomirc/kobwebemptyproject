package org.example.kobwebemptyproject.di

import org.example.kobwebemptyproject.repositories.BlogRepository
import org.example.kobwebemptyproject.view_models.CustomBackendDemoViewModel

class AppContainerLayoutScope {
    init {
        console.log("AppContainerLayoutScope init = ${this.hashCode()}")
    }

    fun provideCustomBackendDemoViewModel(): CustomBackendDemoViewModel {
        val blogRepository = BlogRepository()
        return CustomBackendDemoViewModel(blogRepository)
    }
}