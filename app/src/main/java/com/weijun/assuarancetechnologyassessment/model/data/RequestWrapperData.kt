package com.weijun.assuarancetechnologyassessment.model.data

import android.os.Build
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.util.Base64
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

data class RequestWrapperData(
    val Search: List<MovieListData>,
    val totalResults: String,
    val Response: String
)

@RequiresApi(Build.VERSION_CODES.O)
fun decryptData(encryptedData: String, key: String, iv: String): MovieData? {
    try {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        val secretKeySpec = SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES")
        val ivParameterSpec = IvParameterSpec(iv.toByteArray(charset("UTF-8")))

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec)

        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedData))
        val decryptedJson = String(decryptedBytes, Charsets.UTF_8)

        // Deserialize decrypted JSON into your data class
        return Gson().fromJson(decryptedJson, MovieData::class.java)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}