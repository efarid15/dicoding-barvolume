package com.dicoding.picodiploma.barvolume

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.NumberFormatException

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtPanjang: EditText
    private lateinit var edtTinggi: EditText
    private lateinit var edtLebar: EditText
    private lateinit var btnCalculate: Button
    private lateinit var txtResult: TextView

    companion object{
        private const val STATE_RESULT = "state_result"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, txtResult.text.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtPanjang = findViewById(R.id.edtPanjang)
        edtTinggi = findViewById(R.id.edtTinggi)
        edtLebar = findViewById(R.id.edtLebar)
        btnCalculate = findViewById(R.id.btnCalculate)
        txtResult = findViewById(R.id.txtResult)

        btnCalculate.setOnClickListener(this)

        if (savedInstanceState != null) {
            val result = savedInstanceState.getString(STATE_RESULT) as String
            txtResult.text = result
        }
    }

    override fun onClick(v: View) {

        if (v.id == R.id.btnCalculate) {

            val inputLength = edtPanjang.text.toString().trim()
            val inputWidth = edtLebar.text.toString().trim()
            val inputHeight = edtTinggi.text.toString().trim()

            var isEmptyFields = false
            var isInvalidDouble = false

            if (inputLength.isEmpty()) {
                isEmptyFields = true
                edtPanjang.error = "Field ini tidak boleh kosong"
            }

            if (inputWidth.isEmpty()) {
                isEmptyFields = true
                edtLebar.error = "Field ini tidak boleh kosong"
            }

            if (inputHeight.isEmpty()) {
                isEmptyFields = true
                edtTinggi.error = "Field ini tidak boleh kosong"
            }

            val length = toDouble(inputLength)
            val width = toDouble(inputWidth)
            val height = toDouble(inputHeight)

            if (length == null) {
                isInvalidDouble = true
                edtPanjang.error = "Field ini harus berupa nomor yang valid"
            }

            if (width == null) {
                isInvalidDouble = true
                edtLebar.error = "Field ini harus berupa nomor yang valid"
            }

            if (height == null) {
                isInvalidDouble = true
                edtTinggi.error = "Field ini harus berupa nomor yang valid"
            }

            if (!isEmptyFields && !isInvalidDouble) {
                val volume = length as Double * width as Double * height as Double
                txtResult.text = volume.toString()
            }

        }
    }

    private fun toDouble(str: String): Double? {
        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }


}
