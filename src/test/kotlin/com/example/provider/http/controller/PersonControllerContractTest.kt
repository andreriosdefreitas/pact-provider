package com.example.provider.http.controller

import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.spring.spring6.PactVerificationSpring6Provider
import com.example.provider.http.controller.api.request.CreatePersonRequest
import com.example.provider.http.controller.api.response.CreatePersonResponse
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("provider-api")
@PactBroker
@PactFolder("pacts")
//@IgnoreNoPactsToVerify
@ActiveProfiles("test")
class PersonControllerContractTest {

    @MockBean
    private lateinit var personController: PersonController
    private val personId = "13bb5352-303a-485e-a75b-6b1a97727cdf"
    private val firstName = "First"
    private val lastName = "Last"
    private val email = "Email"
    private val personRequest = CreatePersonRequest(firstName, lastName, email)
    private val personResponse = CreatePersonResponse(UUID.fromString(personId), firstName, lastName, email, null)

    @TestTemplate
    @ExtendWith(PactVerificationSpring6Provider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext?) {
        context?.verifyInteraction()
    }

    @State("create a person")
    fun createPerson() {
        Mockito.`when`(personController.createPerson(personRequest)).thenReturn(personResponse)
    }
}