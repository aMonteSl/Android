package com.example.ejerciciodatabinding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel : ViewModel() {

    private val _scoreA = MutableLiveData<Int>().apply { value = 0 }
    val scoreA: LiveData<Int>
        get() = _scoreA

    private val _scoreB = MutableLiveData<Int>().apply { value = 0 }
    val scoreB: LiveData<Int>
        get() = _scoreB

    fun addPointsTeamA(points: Int) {
        _scoreA.value = (_scoreA.value ?: 0) + points
    }

    fun addPointsTeamB(points: Int) {
        _scoreB.value = (_scoreB.value ?: 0) + points
    }

    fun resetScores() {
        _scoreA.value = 0
        _scoreB.value = 0
    }
}