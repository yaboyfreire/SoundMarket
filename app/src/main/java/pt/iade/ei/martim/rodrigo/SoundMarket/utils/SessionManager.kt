package pt.iade.ei.martim.rodrigo.SoundMarket.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SessionManager(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)
    private val editor = sharedPreferences.edit()

    // Method to save token
    fun saveAuthToken(token: String) {
        editor.putString("auth_token", token)
        editor.apply()
    }

    // Method to save userId
    fun saveUserId(userId: String) {
        editor.putString("user_id", userId)
        editor.apply()
    }

    // Method to get userId
    fun getUserId(): String? {
        val userId = sharedPreferences.getString("user_id", null)
        if (userId == null) {
            Log.e("SessionManager", "User ID not found in shared preferences!")
        }
        return userId
    }

    // Method to get token
    fun getAuthToken(): String? {
        return sharedPreferences.getString("auth_token", null)
    }
}

