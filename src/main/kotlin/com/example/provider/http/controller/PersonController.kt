package com.example.provider.http.controller

import com.example.provider.http.controller.api.request.CreatePersonRequest
import com.example.provider.http.controller.api.response.CreatePersonResponse
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/person")
class PersonController {
    @PostMapping(produces = [APPLICATION_JSON_VALUE])
    @ResponseStatus(CREATED)
    fun createPerson(@RequestBody createPersonRequest: CreatePersonRequest): CreatePersonResponse =
        createPersonRequest.toResponse()

    fun CreatePersonRequest.toResponse() = CreatePersonResponse(
        id = UUID.randomUUID(),
        firstName = this.firstName,
        lastName = this.lastName,
        email = this.email
    )
}