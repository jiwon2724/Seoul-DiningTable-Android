package com.jiwondev.seoul_diningtable.presenter.common.extensions

import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText

fun ViewGroup.gone() { visibility = View.GONE }
fun ViewGroup.visible() { visibility = View.VISIBLE }
fun ViewGroup.inVisible() { visibility = View.INVISIBLE }

fun EditText.isNotEmpty(): Boolean { return this.text.trim().toString().isNotEmpty() }