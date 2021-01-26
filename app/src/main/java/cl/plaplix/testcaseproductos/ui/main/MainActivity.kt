package cl.plaplix.testcaseproductos.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cl.plaplix.testcaseproductos.R
import cl.plaplix.testcaseproductos.databinding.ActivityMainBinding
import cl.plaplix.testcaseproductos.utils.TimberInitializer

class MainActivity : AppCompatActivity() {
    private val timberInitializer = TimberInitializer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        timberInitializer.initTimber()
    }
}