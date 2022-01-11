package com.example.tipcalculator

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENTAGE = 15

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var seekBarTip: SeekBar
    private lateinit var tvTipPercentLabel: TextView
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvTipDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
//        etBaseAmount = findViewById(R.id.etBaseAmount)
//        seekBarTip = findViewById(R.id.seekbarTip)
//        tvTipAmount = findViewById(R.id.tvTipAmount)
//        tvTipPercentLabel = findViewById(R.id.tvTipPercentLabel)
//        tvTotalAmount = findViewById(R.id.tvTotalAmount)
//        tvTipDescription = findViewById(R.id.tvTipDescription)
//
//        updateTipDescription(INITIAL_TIP_PERCENTAGE)
//        seekBarTip.progress = INITIAL_TIP_PERCENTAGE
//        tvTipPercentLabel.text = "${INITIAL_TIP_PERCENTAGE}%"
//        seekBarTip.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
//            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
//                tvTipPercentLabel.text = "${p1}%"
//                calculateTip()
//                updateTipDescription(p1)
//            }
//
//            override fun onStartTrackingTouch(p0: SeekBar?) {}
//
//            override fun onStopTrackingTouch(p0: SeekBar?) {}
//
//        })
//
//        etBaseAmount.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//
//            override fun afterTextChanged(p0: Editable?) {
//                calculateTip()
//            }
//        })
    }

    private fun updateTipDescription(tipPercent:Int) {
        val tipDesc = when (tipPercent){
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else -> "Amazing!"
        }
        tvTipDescription.text = tipDesc
        // Update the tip based on tipPercent
        val color = ArgbEvaluator().evaluate((tipPercent.toFloat() / seekBarTip.max),
            ContextCompat.getColor(this, R.color.bestTip),
            ContextCompat.getColor(this, R.color.worstTip)) as Int

        Log.i(TAG, "$color")
        tvTipDescription.setTextColor(color)
    }

    private fun calculateTip() {
        if (etBaseAmount.text.isNullOrBlank()) {
            tvTipAmount.text = ""
            tvTotalAmount.text = ""
            return
        }
        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = seekBarTip.progress.toDouble()
        val tip = (baseAmount * (tipPercent / 100))
        val tipTotal = tip + baseAmount
        tvTipAmount.text = String.format("$%.2f", tip)
        tvTotalAmount.text = String.format("$%.2f", tipTotal)
    }
}