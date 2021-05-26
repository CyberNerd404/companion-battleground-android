package com.cybernerd.companionBattleground.utils

import android.content.Context
import android.util.Log
import android.widget.Toast


fun debug(message: String) {
    Log.d("debug", message)
}

fun validate(message: String){
    Log.d("validate", message)
}

fun errorDebug(message: String) {
    Log.d("error", message)
}

fun showToast(context: Context, message: String){
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}


