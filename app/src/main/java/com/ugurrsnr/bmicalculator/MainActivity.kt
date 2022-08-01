package com.ugurrsnr.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ugurrsnr.bmicalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //setting borders of pickers
        binding.weightPicker.apply {
            minValue = 30 //min 30 kg
            maxValue = 150 //max 150 kg
        }
        binding.heightPicker.apply {
            minValue = 100 //min 100cm
            maxValue = 250 //max 250cm
        }

        binding.apply {
            weightPicker.setOnValueChangedListener { _, _, _ ->
                calculateBMI()
            }
            heightPicker.setOnValueChangedListener{_,_,_ ->
                calculateBMI()
            }
        }



    }

    private fun calculateBMI() {
        val height = binding.heightPicker.value
        val heightDouble = height.toDouble()/100
        val weight = binding.weightPicker.value

        val bmi = weight.toDouble()/(heightDouble * heightDouble)
        binding.resultTV.text = String.format("Your BMI is : %.2f", bmi)
        binding.healtyTV.text = String.format("Considered: %s", healtyMessage(bmi))
    }

    private fun healtyMessage(bmi: Double): String {
        if (bmi < 18.5)
            return "Underweight"
        if (bmi < 25.0)
            return "Healty"
        if (bmi < 30.0)
            return "Overweight"

        return "Obese"

    }
}