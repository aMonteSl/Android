package com.example.ejerciciodatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.example.ejerciciodatabinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // Instancia el ViewModel
    private val scoreViewModel: ScoreViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Configurar DataBinding
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        // Asignar el lifecycleOwner para que observe los LiveData
        binding.lifecycleOwner = this
        // Enlazar el ViewModel con el layout
        binding.viewModel = scoreViewModel

        // Configurar botones para Equipo A
        binding.btnFreeA.setOnClickListener { scoreViewModel.addPointsTeamA(1) }
        binding.btnTwoA.setOnClickListener { scoreViewModel.addPointsTeamA(2) }
        binding.btnThreeA.setOnClickListener { scoreViewModel.addPointsTeamA(3) }

        // Configurar botones para Equipo B
        binding.btnFreeB.setOnClickListener { scoreViewModel.addPointsTeamB(1) }
        binding.btnTwoB.setOnClickListener { scoreViewModel.addPointsTeamB(2) }
        binding.btnThreeB.setOnClickListener { scoreViewModel.addPointsTeamB(3) }

        // Configurar botón Reset
        binding.btnReset.setOnClickListener { scoreViewModel.resetScores() }
    }
}
