package dev.petronilio.imc

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnCalculate.setOnClickListener {
            val nextActivity = Intent(this,ResultActivity::class.java)
            nextActivity.putExtra("WEIGHT",inputWeight.text.toString())
            nextActivity.putExtra("HEIGHT",inputHeight.text.toString())
            nextActivity.putExtra("GENRE",spGenre.selectedItem.toString())
            startActivity(nextActivity)
        }

        btnClear.setOnClickListener {
            inputHeight.setText("")
            inputWeight.setText("")
            spGenre.setSelection(0)
        }
    }
}
