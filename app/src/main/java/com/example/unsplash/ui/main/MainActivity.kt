package com.example.unsplash.ui.main

import android.Manifest
import android.content.Context
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import com.example.unsplash.R
import com.example.unsplash.databinding.ActivityMainBinding
import com.example.unsplash.utils.CheckPermission

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Unsplash)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        if(!CheckPermission(this).isPermissionGranted()) {
            requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), REQUEST_CODE)
        }
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        currentFocus?.let {
            if (it is SearchView.SearchAutoComplete) {
                val viewRect = Rect()
                it.getGlobalVisibleRect(viewRect)
                if (!viewRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                    val imm: InputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                    it.clearFocus()
                }
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    companion object {
        const val REQUEST_CODE = 0
    }
}
