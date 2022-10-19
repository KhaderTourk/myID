package com.example.tryaq.util

import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec

private const val SECRET_KEY = "56682156973"
private const val ALGORITHM = "HmacSHA1"

private val  HASH_KEY = hex(SECRET_KEY)
private val HMAC_KEY = SecretKeySpec(HASH_KEY, ALGORITHM)

fun hash(password: String): String{
    val hmac = Mac.getInstance(ALGORITHM)
    hmac.init(HMAC_KEY)
    return hex(hmac.doFinal(password.toByteArray(Charsets.UTF_8)))
}

 fun hex(s: String): ByteArray {
    val result = ByteArray(s.length / 2)
    for (idx in result.indices) {
        val srcIdx = idx * 2
        val high = s[srcIdx].toString().toInt(16) shl 4
        val low = s[srcIdx + 1].toString().toInt(16)
        result[idx] = (high or low).toByte()
    }

    return result
}

private val digits = "0123456789abcdef".toCharArray()

 fun hex(bytes: ByteArray): String {
    val result = CharArray(bytes.size * 2)
    var resultIndex = 0
    val digits = digits

    for (index in 0 until bytes.size) {
        val b = bytes[index].toInt() and 0xff
        result[resultIndex++] = digits[b shr 4]
        result[resultIndex++] = digits[b and 0x0f]
    }

    return result.concatToString()
}