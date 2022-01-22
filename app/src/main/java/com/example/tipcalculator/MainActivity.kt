package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var etBaseAmount: EditText
    private lateinit var tvTipAmount: TextView
    private lateinit var tvTotalAmount: TextView
    private lateinit var tvPercentSelectedLabel: TextView
    private lateinit var tvPercentSelected: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var customTipButton: Button
    private lateinit var plusButton: Button
    private lateinit var subtractButton: Button
    private lateinit var tvPersonsSplit: TextView
    private lateinit var tvTotalAmountPerPerson: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etBaseAmount = findViewById(R.id.etBaseAmount)
        tvTipAmount = findViewById(R.id.tvTipAmount)
        tvTotalAmount = findViewById(R.id.tvTotalAmount)
        tvPercentSelectedLabel = findViewById(R.id.tvPercentSelectedLabel)
        tvPercentSelected = findViewById(R.id.tvPercentSelected)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        customTipButton = findViewById(R.id.customTipButton)
        plusButton = findViewById(R.id.plusButton)
        subtractButton = findViewById(R.id.subtractButton)
        tvPersonsSplit = findViewById(R.id.tvPersonsSplit)
        tvTotalAmountPerPerson = findViewById(R.id.tvTotalAmountPerPerson)


        etBaseAmount.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {
                calculateTip()
            }
        })

        button1.setOnClickListener {
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "10%"
            calculateTip()
        }

        button2.setOnClickListener {
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "15%"
            calculateTip()
        }

        button3.setOnClickListener {
            tvPercentSelectedLabel.isVisible = true
            tvPercentSelected.isVisible = true
            tvPercentSelected.text = "20%"
            calculateTip()
        }

        customTipButton.setOnClickListener {
            // TODO: Implement a model to ask for custom tip %
        }

        plusButton.setOnClickListener {
            tvPersonsSplit.text = (tvPersonsSplit.text.toString().toInt() + 1).toString()
            calculateTip()
        }

        subtractButton.setOnClickListener {
            if (tvPersonsSplit.text.toString().toInt() > 1) {
                tvPersonsSplit.text = (tvPersonsSplit.text.toString().toInt() - 1).toString()
            } else {
                Toast.makeText(this, "Total persons can't be negative", Toast.LENGTH_SHORT).show()
            }
            calculateTip()
        }
    }

    private fun calculateTip() {
        if (etBaseAmount.text.isNullOrBlank()) {
            tvTipAmount.text = "$000"
            tvTotalAmount.text = "$000"
            tvTotalAmountPerPerson.text = "000"
            return
        }
        val baseAmount = etBaseAmount.text.toString().toDouble()
        val tipPercent = tvPercentSelected.text.dropLast(1).toString().toDouble()
        val tip = (baseAmount * (tipPercent / 100))
        val tipTotal = tip + baseAmount
        tvTipAmount.text = String.format("$%.2f", tip)
        tvTotalAmount.text = String.format("$%.2f", tipTotal)
        tvTotalAmountPerPerson.text =
            String.format("%.2f", tipTotal / tvPersonsSplit.text.toString().toDouble())
    }
}