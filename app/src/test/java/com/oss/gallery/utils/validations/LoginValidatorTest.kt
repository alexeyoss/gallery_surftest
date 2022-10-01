package com.oss.gallery.utils.validations

import com.oss.gallery.R
import com.oss.gallery.ui.states.auth_activity_states.ValidationState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class LoginValidatorTest {

    private lateinit var loginValidator: LoginValidator
    private lateinit var result: ValidationState

    @Before
    fun initResources() {
        loginValidator = LoginValidator()
    }

    private fun runFlow(data: String) {
        runBlocking {
            val flow = loginValidator.validate(data)
            flow.collectLatest {
                result = it
            }
        }
    }

    @Test
    fun phoneIsEmpty() {
        runFlow("")
        assertEquals(ValidationState.EmptyFiledError(R.string.empty_field_error), result)
    }

    @Test
    fun phoneFormatIsIncorrect() {
        runFlow("71237894560")
        assertEquals(ValidationState.IncorrectFiledError(R.string.incorrect_phone_format), result)
    }

    @Test
    fun phoneIsNotEmptyAndCorrect() {
        runFlow("+71234567890")
        assertEquals(ValidationState.Successful, result)
    }
}