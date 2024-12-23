package com.example.datedays

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextSurname: EditText
    private lateinit var editTextBirthDate: EditText
    private lateinit var imageViewPhoto: ImageView
    private val PICK_IMAGE = 1
    private var selectedImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextName = findViewById(R.id.editTextName)
        editTextSurname = findViewById(R.id.editTextSurname)
        editTextBirthDate = findViewById(R.id.editTextBirthDate)
        imageViewPhoto = findViewById(R.id.imageViewPhoto)

        findViewById<Button>(R.id.buttonSelectPhoto).setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent, PICK_IMAGE)
            finish()
        }

        findViewById<Button>(R.id.buttonSave).setOnClickListener {
            val name = editTextName.text.toString()
            val surname = editTextSurname.text.toString()
            val birthDate = editTextBirthDate.text.toString()

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra("name", name)
                putExtra("surname", surname)
                putExtra("birthDate", birthDate)
                putExtra("photoUri", selectedImageUri.toString())
            }
            startActivity(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            selectedImageUri = data?.data
            imageViewPhoto.setImageURI(selectedImageUri)
        }
    }
}