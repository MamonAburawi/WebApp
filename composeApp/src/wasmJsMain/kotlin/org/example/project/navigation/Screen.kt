package org.example.project.navigation


import kotlinx.serialization.Serializable


sealed class Screen (val route: String){

   @Serializable
    data object Home: Screen("home")

    @Serializable
    data class Product(val id: String): Screen("product/$id")

    fun route(): String{
        return when(this){
            is Home -> route
            is Product -> route
        }
    }

}