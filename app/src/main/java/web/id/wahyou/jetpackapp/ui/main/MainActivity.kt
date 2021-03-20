package web.id.wahyou.jetpackapp.ui.main

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.android.support.DaggerAppCompatActivity
import web.id.wahyou.jetpackapp.R
import web.id.wahyou.jetpackapp.databinding.ActivityMainBinding

class MainActivity : DaggerAppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        with(binding) {
            val navController = findNavController(R.id.navHostFragment)
            navView.setupWithNavController(navController)
        }
    }
}