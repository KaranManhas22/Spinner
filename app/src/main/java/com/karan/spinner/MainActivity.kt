package com.karan.spinner

import android.app.Dialog
import android.os.Binder
import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.karan.spinner.databinding.ActivityMainBinding
import com.karan.spinner.databinding.CustomDialogboxSpinnerBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var names = mutableListOf("Karan", "Harsh", "Harman")
    lateinit var arrayAdapter: ArrayAdapter<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.StaticSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                var selectedItem = binding?.StaticSpinner?.selectedItem as String

                Toast.makeText(this@MainActivity, "$position $selectedItem", Toast.LENGTH_SHORT)
                    .show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        binding.DynamicSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long

                ) {

                    arrayAdapter =
                        ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1, names)
                    binding.DynamicSpinner.adapter = arrayAdapter

                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        binding.btnfloating.setOnClickListener {

            val dialogBinding = CustomDialogboxSpinnerBinding.inflate(layoutInflater)
            val dialog = Dialog(this).apply {
                setContentView(dialogBinding.root)
                window?.setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }


            dialogBinding.btnadd.setOnClickListener {
                names.add(dialogBinding.etname.text.toString())
                dialog.dismiss()
                startActivity(intent)
            }
            dialog.setCancelable(false)
            dialog.show()


        }


    }
}
