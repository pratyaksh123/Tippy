package com.example.tipcalculator

import android.animation.ArgbEvaluator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

private const val TAG = "MainActivity"
private const val INITIAL_TIP_PERCENTAGE = 15

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvPercentSelectedLabel: TextView
    private lateinit var tvPercentSelected: TextView
    private lateinit var button1:Button
    private lateinit var button2:Button
    private lateinit var button3:Button
    private lateinit var customTipButton:Button
//    private lateinit var tvTipDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvPercentSelectedLabel = findViewById(R.id.tvPercentSelectedLabel)
        tvPercentSelected = findViewById(R.id.tvPercentSelected)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)

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
        etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                calculateTip()
            }
        })

        button1.setOnClickListener{
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "10%"
        }

        button2.setOnClickListener{
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "15%"
        }

        button3.setOnClickListener{
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "20%"
        }
    }

    private fun updateTipDescription(tipPercent:Int) {
        val tipDesc = when (tipPercent){
            in 0..9 -> "Poor"
            in 10..14 -> "Acceptable"
            in 15..19 -> "Good"
            in 20..24 -> "Great"
            else -> "Amazing!"
        }
//        tvTipDescription.text = tipDesc
        // Update the tip based on tipPercent
//        val color = ArgbEvaluator().evaluate((tipPercent.toFloat() / seekBarTip.max),
//            ContextCompat.getColor(this, R.color.bestTip),
//            ContextCompat.getColor(this, R.color.worstTip)) as Int

//        Log.i(TAG, "$color")
//        tvTipDescription.setTextColor(color)
    }

    private fun calculateTip() {
        if (etBaseAmount.text.isNullOrBlank()) {
            tvTipAmount.text = "$000"
            tvTotalAmount.text = "$000"
            return
        }
        val baseAmount = etBaseAmount.text.toString().toDouble()
//        val tipPercent = seekBarTip.progress.toDouble()
//        val tip = (baseAmount * (tipPercent / 100))
//        val tipTotal = tip + baseAmount
//        tvTipAmount.text = String.format("$%.2f", tip)
//        tvTotalAmount.text = String.format("$%.2f", tipTotal)
    }
}