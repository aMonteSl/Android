package com.example.ejerciciodatabinding

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {
    // Usamos LiveData para que DataBinding se actualice automáticamente
    val scoreA = MutableLiveData<Int>().apply { value = 0 }
    val scoreB = MutableLiveData<Int>().apply { value = 0 }

    fun addPointsTeamA(points: Int) {
        scoreA.value = (scoreA.value ?: 0) + points
    }

    fun addPointsTeamB(points: Int) {
        scoreB.value = (scoreB.value ?: 0) + points
    }

    fun resetScores() {
        scoreA.value = 0
        scoreB.value = 0
    }
}

