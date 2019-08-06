package dev.petronilio.imc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    // val - immutable
    // var - mutable
    //lateinit

    var height = 0.0
    var weight = 0.0
    var genre  = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        loadExtra()
        calculate()
    }

    private fun calculate() {
        var imc = weight / (height * height)
        tvIMC.text = imc.toFixed(1)
        when {
            imc < 18.5 -> {
                tvIMCStatus.text = getString(R.string.label_below_weight)
                setImg(R.drawable.fem_abaixo)
            }
            imc < 24.9 -> {
                tvIMCStatus.text = getString(R.string.label_ideal_weight)
                setImg(R.drawable.fem_ideal)
            }
            imc < 29.9 -> {
                tvIMCStatus.text = getString(R.string.label_above_weight)
                setImg(R.drawable.fem_sobre)
            }
            imc < 34.9 -> {
                tvIMCStatus.text = getString(R.string.label_obese_weight)
                setImg(R.drawable.fem_obeso)
            }
            else -> {
                tvIMCStatus.text = getString(R.string.label_extremely_obese_weight)
                setImg(R.drawable.fem_extremo_obeso)
            }
        }
    }

    fun setImg(resourceId: Int) {

        val male = getString(R.string.label_male)
        val female = getString(R.string.label_female)

        /*when{
            genre == male  {

            }
            genre == female {

            }
        }*/

        ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this,
                resourceId))
    }

    private fun loadExtra(){
        weight = intent.getStringExtra("WEIGHT").toDouble()
        height = intent.getStringExtra("HEIGHT").toDouble()
        genre = intent.getStringExtra("GENRE")

        tvIMCMsg.text = "weight: "+weight+"  height: "+height+" genre: "+genre
    }
}
