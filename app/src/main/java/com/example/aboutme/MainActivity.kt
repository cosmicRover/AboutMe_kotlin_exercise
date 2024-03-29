package com.example.aboutme

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.aboutme.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //lateinit binding var
    private lateinit var binding: ActivityMainBinding

    //create a var to hold data from the data class
    private var myName: MyNameData = MyNameData(name = "Joy Paul")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        findViewById<Button>(R.id.on_edit_done_button).setOnClickListener {
//            addNickname(it)
//        }

        //use binding to access views and data. you first enable it on build.gradle on app folder
        //using binding is more performance focused
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.myName = myName

        //using binding + widget id we set on click listener
        binding.onEditDoneButton.setOnClickListener {
            addNickname(it)
        }

    }

    private fun addNickname(view: View){

        //using binding.apply we set multiple widgets
        binding.apply {
            //nicknameTextView.text = binding.nicknameEdit.text
            myName?.nickname = nicknameEdit.text.toString() //setting myName's nickname to the input text and it
            //would update it in the widget since it's all binded in the xml
            invalidateAll()//force rebind of widgets
            nicknameEdit.visibility = View.GONE
            onEditDoneButton.visibility = View.GONE
            nicknameTextView.visibility = View.VISIBLE
        }

        //hides keyboard using inout method service
        val inputManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
