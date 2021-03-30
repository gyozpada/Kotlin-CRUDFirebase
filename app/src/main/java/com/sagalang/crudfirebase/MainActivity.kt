package com.sagalang.crudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var etNama : EditText
    private lateinit var etAlamat : EditText
    private lateinit var btnSave : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etNama = findViewById(R.id.et_name)
        etAlamat = findViewById(R.id.et_alamat)
        btnSave = findViewById(R.id.btn_save)

        btnSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        saveData()
    }

    private fun saveData (){
        val nama = etNama.text.toString().trim()
        val alamat = etAlamat.text.toString().trim()

        if(nama.isEmpty()){
            etNama.error = "Isi Nama!"
            return
        }

        if(alamat.isEmpty()){
            etAlamat.error = "Isi Alamat!"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("mahasiswa")

        val mhsId = ref.push().key

        val mhs = Mahasiswa(mhsId, nama, alamat)

        if (mhsId != null) {
            ref.child(mhsId).setValue(mhs).addOnCompleteListener{
                Toast.makeText(applicationContext,"data berhasil ditambahkan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}