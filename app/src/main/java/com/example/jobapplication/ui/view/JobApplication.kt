package com.example.jobapplication.ui.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jobapplication.databinding.ActivityJobApplicationBinding
import com.example.jobapplication.data.model.JobForm
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class JobApplication : AppCompatActivity() {

    private lateinit var binding: ActivityJobApplicationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJobApplicationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = Json.decodeFromString<JobForm>(intent.getStringExtra("User").toString())

        fillInputs(user)

        val intentLogin = Intent(this, Login::class.java)

        binding.logoutButton.setOnClickListener {
            startActivity(intentLogin)
        }
    }

    private fun fillInputs(user: JobForm) {
        binding.firstName.setText(user.first_name)
        binding.lastName.setText(user.last_name)
        binding.streetAddress.setText(user.street_address)
        binding.streetAddressLineTwo.setText(user.street_address_2)
        binding.city.setText(user.city)
        binding.state.setText(user.state)
        binding.postal.setText(user.zip_code)
        binding.country.setText(user.country)
        binding.email.setText(user.email)
        binding.areaCode.setText(user.area_code)
        binding.phoneNumber.setText(user.phone_number)
        binding.position.setText(user.position)
        binding.date.setText(user.date)
    }
}