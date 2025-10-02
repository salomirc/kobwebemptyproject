package org.example.kobwebemptyproject.helpers

private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"

fun String.toEndpointUrl() = "$BASE_URL$this"