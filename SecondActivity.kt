package com.example.datedays

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class SecondActivity : AppCompatActivity() {

    private lateinit var imageViewPhoto: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewSurname: TextView
    private lateinit var textViewAge: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        imageViewPhoto = findViewById(R.id.imageViewPhoto)
        textViewName = findViewById(R.id.textViewName)
        textViewSurname = findViewById(R.id.textViewSurname)
        textViewAge = findViewById(R.id.textViewAge)

        val name = intent.getStringExtra("name")
        val surname = intent.getStringExtra("surname")
        val birthDate = intent.getStringExtra("birthDate")
        val photoUriString = intent.getStringExtra("photoUri")


        textViewName.text = "Имя: $name"
        textViewSurname.text = "Фамилия: $surname"


        val age = calculateAge(birthDate)
        textViewAge.text = "Возраст: $age лет"


        val photoUri = Uri.parse(photoUriString)
        imageViewPhoto.setImageURI(photoUri)


        findViewById<Button>(R.id.buttonExit).setOnClickListener {
            finish()
        }
    }

    private fun calculateAge(birthDate: String?): String {

        val dateParts = birthDate?.split(".")
        if (dateParts != null && dateParts.size == 3) {
            val day = dateParts[0].toInt()
            val month = dateParts[1].toInt()
            val year = dateParts[2].toInt()

            val birth = Calendar.getInstance().apply {
                set(year, month - 1, day)
            }
            val today = Calendar.getInstance()

            val age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)
            return age.toString()
        }
        return "Неизвестно"
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.exit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_exit -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}