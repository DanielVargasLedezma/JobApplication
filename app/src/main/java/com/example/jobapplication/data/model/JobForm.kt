package com.example.jobapplication.data.model

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerial

@Serializable
data class JobForm(
    var first_name: String,
    var last_name: String,
    var street_address: String,
    var street_address_2: String,
    var city: String,
    var state: String,
    var zip_code: String,
    var country: String,
    var email: String,
    var area_code: String,
    var phone_number: String,
    var position: String,
    var date: String,
    val usuarioLinkeado: String,
): JavaSerial {
    init {
        serializer()
    }
}