package com.example.jobapplication.data.model

import kotlinx.serialization.Serializable
import java.io.Serializable as JavaSerial

@Serializable
data class JobForm(
    val first_name: String,
    val last_name: String,
    val street_address: String,
    val street_address_2: String,
    val city: String,
    val state: String,
    val zip_code: String,
    val country: String,
    val email: String,
    val area_code: String,
    val phone_number: String,
    val position: String,
    val date: String,
    val usuarioLinkeado: String,
): JavaSerial {
    init {
        serializer()
    }
}