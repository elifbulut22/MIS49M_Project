package com.example.travelbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText

class ActivityDetailsBinding private constructor(
    val root: View,
    val cityNameText: EditText,
    val countryNameText: EditText,
    val travelDetailsText: EditText,
    val button: Button
) {
    companion object {
        fun inflate(inflater: LayoutInflater, parent: ViewGroup? = null): ActivityDetailsBinding {
            val root = inflater.inflate(R.layout.activity_details, parent, false)
            val cityNameText: EditText = root.findViewById(R.id.cityNameText)
            val countryNameText: EditText = root.findViewById(R.id.countryNameText)
            val travelDetailsText: EditText = root.findViewById(R.id.travelDetailsText)
            val button: Button = root.findViewById(R.id.button)
            return ActivityDetailsBinding(root, cityNameText, countryNameText, travelDetailsText, button)
        }
    }
}
