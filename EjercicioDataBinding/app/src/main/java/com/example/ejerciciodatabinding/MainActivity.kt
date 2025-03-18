package com.example.ejerciciodatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.ejerciciodatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewModel = scoreViewModel

        scoreViewModel.scoreA.observe(this, Observer { newScore ->
            binding.tvScoreA.text = newScore.toString()
        })

        binding.btnA1.setOnClickListener { scoreViewModel.addPointsTeamA(1) }
        binding.btnA2.setOnClickListener { scoreViewModel.addPointsTeamA(2) }
        binding.btnA3.setOnClickListener { scoreViewModel.addPointsTeamA(3) }

        binding.btnB1.setOnClickListener { scoreViewModel.addPointsTeamB(1) }
        binding.btnB2.setOnClickListener { scoreViewModel.addPointsTeamB(2) }
        binding.btnB3.setOnClickListener { scoreViewModel.addPointsTeamB(3) }

        // Configurar botón Reset
        binding.btnReset.setOnClickListener { scoreViewModel.resetScores() }
    }
}
