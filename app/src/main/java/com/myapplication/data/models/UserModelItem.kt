package com.myapplication.data.models

data class UserModelItem(
    var age: Int,
    var child: List<Child>,
    var email: String,
    var id: String,
    var level: Int,
    var name: String,
    var parent: String
)