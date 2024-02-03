package com.biometric.dc.viewmodels

import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.biometric.BiometricManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.biometric.dc.R

class MainViewModel: ViewModel() {

    val resultCheckBiometric = MutableLiveData<Int>()

    //No permkte chequear que se puede ver biometricos
     fun checkBiometric(context: Context) {

        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_SUCCESS)

            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                resultCheckBiometric.postValue(BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE)

            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE ->{
                resultCheckBiometric.postValue( BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE)
               }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                resultCheckBiometric.postValue( BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED) //Devuelve un codigo

            }
        }
    }
}