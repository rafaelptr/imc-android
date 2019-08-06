package dev.petronilio.imc

fun Double.toFixed(digits: Int) = String.format("%.${digits}f",this)
