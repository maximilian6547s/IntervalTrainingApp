package com.maximcuker.intervaltrainingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_b_m_i.*
import java.math.BigDecimal
import java.math.RoundingMode

class BMIActivity : AppCompatActivity() {

    val METRIC_UNITS_VIEW = "METRIC_UNIT_VIEW"
    val US_UNITS_VIEW = "US_UNIT_VIEW"

    private var currentVisibleView: String =
        METRIC_UNITS_VIEW // A variable to hold a value to make a selected view visible


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_b_m_i)

        setSupportActionBar(toolbarBMIActivity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) //set back button
        supportActionBar?.title = "CALCULATE BMI" // Setting a title in the action bar.
        toolbarBMIActivity.setNavigationOnClickListener {
            onBackPressed()
        }

        btnCalculateUnits.setOnClickListener {

            if (currentVisibleView.equals(US_UNITS_VIEW)) {
                if (validateUsUnits()) {
                    val usHeightFeet: String = etUsUnitHeightFeet.text.toString()
                    val usHeightInch: String = etUsUnitHeightInch.text.toString()
                    val usWeight: Float = etUsUnitWeight.text.toString().toFloat()

                    val heightValue = usHeightInch.toFloat() + usHeightFeet.toFloat() * 12

                    val bmi = 703 * (usWeight / (heightValue * heightValue))

                    displayBmiResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            } else {
                if (validateMetricUnits()) {
                    val height: Float = etMetricUnitHeight.text.toString().toFloat() / 100 // height in meters
                    val weight: Float = etMetricUnitWeight.text.toString().toFloat()

                    val bmi = weight / (height * height)
                    displayBmiResult(bmi)
                } else {
                    Toast.makeText(
                        this@BMIActivity,
                        "Please enter valid values.",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }

            }
        }

        makeVisibleMetricUnitsView()
        rgUnits.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rbMetricUnits) {
                makeVisibleMetricUnitsView()
            } else {
                makeVisibleUsUnitsView()
            }
        }

    }

    private fun makeVisibleUsUnitsView() {

        currentVisibleView = US_UNITS_VIEW
        tilMetricUnitHeight.visibility = View.GONE
        tilMetricUnitWeight.visibility = View.GONE

        etUsUnitHeightFeet.text?.clear()
        etUsUnitHeightInch.text?.clear()
        etUsUnitWeight.text?.clear()

        llUsUnitsHeight.visibility = View.VISIBLE
        tilUsUnitWeight.visibility = View.VISIBLE

        llDisplayBMIResult.visibility = View.INVISIBLE
    }

    private fun makeVisibleMetricUnitsView() {

        currentVisibleView = METRIC_UNITS_VIEW
        tilMetricUnitHeight.visibility = View.VISIBLE
        tilMetricUnitWeight.visibility = View.VISIBLE

        etMetricUnitWeight.text?.clear()
        etMetricUnitHeight.text?.clear()

        llUsUnitsHeight.visibility = View.GONE
        tilUsUnitWeight.visibility = View.GONE

        llDisplayBMIResult.visibility = View.INVISIBLE
    }

    private fun displayBmiResult(bmi: Float) {
        val bmiLabel: String
        val bmiDescription: String

        if (bmi.compareTo(15f) <= 0) {
            bmiLabel = "Very severely underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(15f) > 0 && bmi.compareTo(16f) <= 0
        ) {
            bmiLabel = "Severely underweight"
            bmiDescription = "Oops!You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(16f) > 0 && bmi.compareTo(18.5f) <= 0
        ) {
            bmiLabel = "Underweight"
            bmiDescription = "Oops! You really need to take better care of yourself! Eat more!"
        } else if (bmi.compareTo(18.5f) > 0 && bmi.compareTo(25f) <= 0
        ) {
            bmiLabel = "Normal"
            bmiDescription = "Congratulations! You are in a good shape!"
        } else if (bmi.compareTo(25f) > 0 && bmi.compareTo(30f) <= 0
        ) {
            bmiLabel = "Overweight"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(30f) > 0 && bmi.compareTo(35f) <= 0
        ) {
            bmiLabel = "Obese Class | (Moderately obese)"
            bmiDescription = "Oops! You really need to take care of your yourself! Workout maybe!"
        } else if (bmi.compareTo(35f) > 0 && bmi.compareTo(40f) <= 0) {
            bmiLabel = "Obese Class || (Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        } else {
            bmiLabel = "Obese Class ||| (Very Severely obese)"
            bmiDescription = "OMG! You are in a very dangerous condition! Act now!"
        }

        llDisplayBMIResult.visibility = View.VISIBLE
/*
        tvYourBMI.visibility = View.VISIBLE
        tvBMIValue.visibility = View.VISIBLE
        tvBMIType.visibility = View.VISIBLE
        tvBMIDescription.visibility = View.VISIBLE
*/

        // This is used to round the result value to 2 decimal values after "."
        val bmiValue = BigDecimal(bmi.toDouble()).setScale(2, RoundingMode.HALF_EVEN).toString()

        tvBMIValue.text = bmiValue // Value is set to TextView
        tvBMIType.text = bmiLabel // Label is set to TextView
        tvBMIDescription.text = bmiDescription // Description is set to TextView

    }

    private fun validateMetricUnits(): Boolean {
        var isValid = true
        when {
            etMetricUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
            etMetricUnitHeight.text.toString().isEmpty() -> {
                isValid = false
            }
        }

        return isValid
    }

    private fun validateUsUnits(): Boolean {
        var isValid = true
        when {
            etUsUnitHeightFeet.text.toString().isEmpty() -> {
                isValid = false
            }
            etUsUnitHeightInch.text.toString().isEmpty() -> {
                isValid = false
            }
            etUsUnitWeight.text.toString().isEmpty() -> {
                isValid = false
            }
        }

        return isValid
    }
}