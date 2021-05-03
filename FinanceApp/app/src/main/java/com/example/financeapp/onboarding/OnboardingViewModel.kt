package com.example.financeapp.onboarding

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financeapp.R

const val LAST_STEP = 2

class OnboardingViewModel : ViewModel() {

    private val _currentStep = MutableLiveData<Int>()
    val currentStep: LiveData<Int>
        get() = _currentStep

    val icons: List<MutableLiveData<Int>>

    val data: List<OnboardingData>

    private val _currentData = MutableLiveData<OnboardingData>()
    val currentData: LiveData<OnboardingData>
        get() = _currentData

    private val _navigateToSignUp = MutableLiveData<Boolean>()
    val navigateToSignUp: LiveData<Boolean>
        get() = _navigateToSignUp

    init {
        _currentStep.value = 0

        data = arrayListOf(
            OnboardingData("Stay in the green", "Mauris quis orci faucibus, egestas nibh sed" +
                    "vestibulum elit. Nam est dui, accumsan a lorem tincidunt, pellentesque pulvinar lorem.", 0),
            OnboardingData("We'll keep you on track", "Mauris quis orci faucibus, egestas nibh sed" +
                    "vestibulum elit. Nam est dui, accumsan a lorem tincidunt, pellentesque pulvinar lorem.", 1),
            OnboardingData("Double Flower Power", "Mauris quis orci faucibus, egestas nibh sed" +
                    "vestibulum elit. Nam est dui, accumsan a lorem tincidunt, pellentesque pulvinar lorem.", 2)
        )
        _currentData.value = data[0]

        icons = arrayListOf(
            MutableLiveData(R.drawable.ic_baseline_circle_24),
            MutableLiveData(R.drawable.ic_baseline_circle_grey),
            MutableLiveData(R.drawable.ic_baseline_circle_grey)
        )
    }

    fun onAdvance() {
        Log.i("OnboardingViewModel", "onAdvance called; ${currentStep.value}")

        if (isLastStep()) {
            Log.i("OnboardingViewModel", "Should navigate to SignUp/Login")
            navigateToSignUp()
            return
        }

        _currentStep.value = _currentStep.value?.plus(1)
        _currentData.value = data[_currentStep.value!!]

        Log.i("OnboardingViewModel", currentData.value.toString());

        refreshCurrentActiveIcon()
    }

    fun goToStep(step: Int) {
        _currentStep.value = step
        _currentData.value = data[step]

        refreshCurrentActiveIcon()
    }

    private fun refreshCurrentActiveIcon() {
        for (index in icons.indices) {
            if (isActive(index)) {
                Log.i("OnboardingViewModel", "$index")
                icons[index].value = R.drawable.ic_baseline_circle_24
            } else {
                icons[index].value = R.drawable.ic_baseline_circle_grey
            }
        }
    }

    fun navigateToSignUp() {
        _navigateToSignUp.value = true
    }

    fun onNavigatedToSignUp() {
        _navigateToSignUp.value = false
    }

    private fun isLastStep() = currentStep.value == LAST_STEP

    private fun isActive(index: Int) = index == currentStep.value
}
