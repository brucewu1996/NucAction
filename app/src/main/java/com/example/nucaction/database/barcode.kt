package com.example.nucaction.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "barcode_model")
class BarcodeModel(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int,

    @ColumnInfo(name = "barcode_value")
    var barcode: String,

    @ColumnInfo(name = "item")
    var item: String,

    @ColumnInfo(name = "energy")
    var energy: Double,

    @ColumnInfo(name = "protein")
    var protein: Double,

    @ColumnInfo(name = "total_lipid")
    var total_lipid: Double,

    @ColumnInfo(name = "carbohydrate")
    var carbohydrate: Double,

    @ColumnInfo(name = "fiber")
    var fiber: Double,

    @ColumnInfo(name = "sugar")
    var sugar: Double,

    @ColumnInfo(name="Na")
    var Na: Double,

    @ColumnInfo(name = "saturated_fatty_acids")
    var saturated_fatty_acids: Double,

    @ColumnInfo(name = "trans_fatty_acids")
    var trans_fatty_acids: Double,

    @ColumnInfo(name = "cholesterol")
    var cholesterol: Double,
)