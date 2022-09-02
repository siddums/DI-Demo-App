package com.my.sampleapp.network


import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * @Author: Siddu M S
 * @Email: siddushikkerimath@gmail.com
 * @Date: 02-09-2022
 */

@RunWith(JUnit4::class)
class ValidatorTest {

    @Test
    fun whenInputIsValid() {
        val query = "India"

        val result = Validator.validateGetDataFromAPI(query)
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun whenInputIsEmpty() {

        val query = ""
        val result = Validator.validateGetDataFromAPI(query)
        assertThat(result).isEqualTo(false)

    }

    @Test
    fun whenInputExceedsLength() {

        val query = "cassa das  sa adsdmvlsk felf fnal nfoasf "
        val result = Validator.validateGetDataFromAPI(query)
        assertThat(result).isEqualTo(false)

    }

}