package dev.petronilio.imc.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import dev.petronilio.imc.R
import dev.petronilio.imc.enums.Obesity
import dev.petronilio.imc.extensions.toFixed
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
                setImg(Obesity.BELOW)
            }
            imc < 24.9 -> {
                tvIMCStatus.text = getString(R.string.label_ideal_weight)
                setImg(Obesity.IDEAL)
            }
            imc < 29.9 -> {
                tvIMCStatus.text = getString(R.string.label_above_weight)
                setImg(Obesity.ABOVE)
            }
            imc < 34.9 -> {
                tvIMCStatus.text = getString(R.string.label_obese_weight)
                setImg(Obesity.OBESE)
            }
            else -> {
                tvIMCStatus.text = getString(R.string.label_extremely_obese_weight)
                setImg(Obesity.EXTREMELY_OBESE)
            }
        }
    }

    private fun setImg(obesity: Obesity) {

        var resourceId = 0
        val male = getString(R.string.label_male)
        val female = getString(R.string.label_female)

        when(genre){
            male ->  {
                resourceId = when (obesity){
                    Obesity.BELOW -> R.drawable.masc_abaixo
                    Obesity.IDEAL -> R.drawable.masc_ideal
                    Obesity.ABOVE -> R.drawable.masc_sobre
                    Obesity.OBESE -> R.drawable.masc_obeso
                    Obesity.EXTREMELY_OBESE -> R.drawable.masc_extremo_obeso
                }
            }
            female -> {
                resourceId = when (obesity){
                    Obesity.BELOW -> R.drawable.fem_abaixo
                    Obesity.IDEAL -> R.drawable.fem_ideal
                    Obesity.ABOVE -> R.drawable.fem_sobre
                    Obesity.OBESE -> R.drawable.fem_obeso
                    Obesity.EXTREMELY_OBESE -> R.drawable.fem_extremo_obeso
                }
            }
        }
        ivIMCStatus.setImageDrawable(
            ContextCompat.getDrawable(this,resourceId))

    }

    private fun loadExtra(){
        weight = intent.getStringExtra("WEIGHT").toDouble()
        height = intent.getStringExtra("HEIGHT").toDouble()
        genre = intent.getStringExtra("GENRE")

        tvIMCMsg.text = "weight: "+weight+"  height: "+height+" genre: "+genre
    }
}
