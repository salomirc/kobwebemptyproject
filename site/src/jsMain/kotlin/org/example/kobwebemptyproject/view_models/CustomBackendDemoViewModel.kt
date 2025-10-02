package org.example.kobwebemptyproject.view_models

import org.example.kobwebemptyproject.models.domain.UserModel
import org.example.kobwebemptyproject.repositories.BlogRepository

class CustomBackendDemoViewModel(
    private val blogRepository: BlogRepository
) {
    init {
        console.log("CustomBackendDemoViewModel init = ${this.hashCode()}")
    }

    val randomNUmber: Int = (0..100).random()

    suspend fun getUsers(): List<UserModel>? {
        return blogRepository.getUsers()
    }
}