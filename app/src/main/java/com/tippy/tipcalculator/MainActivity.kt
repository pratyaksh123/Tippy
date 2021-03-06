package com.tippy.tipcalculator

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.tippy.tipcalculator.databinding.ActivityMainBinding

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
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        etBaseAmount = binding.etBaseAmount
        tvTipAmount = binding.tvTipAmount
        tvTotalAmount = binding.tvTotalAmount
        tvPercentSelectedLabel = binding.tvPercentSelectedLabel
        tvPercentSelected = binding.tvPercentSelected
        button1 = binding.button1
        button2 = binding.button2
        button3 = binding.button3
        customTipButton = binding.customTipButton
        plusButton = binding.plusButton
        subtractButton = binding.subtractButton
        tvPersonsSplit = binding.tvPersonsSplit
        tvTotalAmountPerPerson = binding.tvTotalAmountPerPerson


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
            val alert = AlertDialog.Builder(this)
            alert.setTitle("Enter the tip in percent")
            val input = EditText(this)
            input.inputType = InputType.TYPE_CLASS_NUMBER
            alert.setView(input)

            alert.setPositiveButton(
                "Ok"
            ) { _, _ ->
                val value = input.text
                if (!value.isNullOrBlank()) {
                    tvPercentSelected.text = value.toString() + "%"
                    calculateTip()
                }
            }

            alert.setNegativeButton(
                "Cancel"
            ) { _, _ -> }

            alert.show()
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
    
    private fun resetField(){
        tvTipAmount.text = "$000"
        tvTotalAmount.text = "$000"
        tvTotalAmountPerPerson.text = "000"
    }

    private fun calculateTip() {
        // Special Char check
        val matches = arrayOf(",", "-", "..")
        var foundSpecialChars = false
        var temp = etBaseAmount.text.toString().replace(" ","")

        for (s in matches) {
            if (temp.contains(s) || temp == ".") {
                foundSpecialChars = true
            }
        }
        
        if(foundSpecialChars){
            Toast.makeText(this, getString(R.string.SpecCharWarning), Toast.LENGTH_SHORT).show()
            resetField()
            etBaseAmount.text.clear()
            return
        }
        
        if (etBaseAmount.text.isNullOrBlank()) {
            resetField()
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