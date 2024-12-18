package pt.iade.ei.martim.rodrigo.SoundMarket.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.os.Build
import android.util.Base64
import android.util.Log
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.S)
fun decodeBase64ToBitmap(base64String: String?): Bitmap? {
    return try {
        if (base64String.isNullOrEmpty()) {
            return null
        }

        // Remove the prefix if it exists (data:image/*;base64,)
        val cleanBase64String = base64String.replaceFirst("(?<=^data:image/[^;]+;base64,)", "")
        val decodedBytes = Base64.decode(cleanBase64String, Base64.DEFAULT)

        // Use ImageDecoder for Android API 28 and above
        try {
            val source = ImageDecoder.createSource(decodedBytes)
            ImageDecoder.decodeBitmap(source)
        } catch (e: Exception) {
            Log.e("decodeBase64ToBitmap", "Error with ImageDecoder, falling back to BitmapFactory", e)
            // Fallback to BitmapFactory
            BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
        }
    } catch (e: Exception) {
        Log.e("decodeBase64ToBitmap", "Error decoding Base64 string", e)
        null
    }
}
