package dev.petronilio.imc.extensions

fun Double.toFixed(digits: Int) = String.format("%.${digits}f",this)
