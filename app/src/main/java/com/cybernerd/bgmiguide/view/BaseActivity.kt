package com.cybernerd.bgmiguide.view

import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

open class BaseActivity: AppCompatActivity(), CoroutineScope {

    val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}