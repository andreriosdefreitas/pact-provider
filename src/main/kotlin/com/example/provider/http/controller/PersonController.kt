package com.example.provider.http.controller

import com.example.provider.http.controller.api.request.CreatePersonRequest
import com.example.provider.http.controller.api.response.CreatePersonResponse
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController("/person")
class PersonController {
    @PostMapping(produces = [APPLICATION_JSON_VALUE])
    fun createPerson(@RequestBody createPersonRequest: CreatePersonRequest): CreatePersonResponse =
        createPersonRequest.toResponse()

    fun CreatePersonRequest.toResponse() = CreatePersonResponse(
        id = UUID.randomUUID(),
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}