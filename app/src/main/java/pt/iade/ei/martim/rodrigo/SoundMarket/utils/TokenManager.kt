package pt.iade.ei.martim.rodrigo.SoundMarket.utils

import android.content.Context
import android.content.SharedPreferences

object TokenManager {
    private const val PREFS_NAME = "user_prefs"
    private const val AUTH_TOKEN_KEY = "auth_token"

    fun saveToken(context: Context, token: String) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().putString(AUTH_TOKEN_KEY, token).apply()
    }

    fun getToken(context: Context): String? {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getString(AUTH_TOKEN_KEY, null)
    }

    fun clearToken(context: Context) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        sharedPreferences.edit().remove(AUTH_TOKEN_KEY).apply()
    }
}
