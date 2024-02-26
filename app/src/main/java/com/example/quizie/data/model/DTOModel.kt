package com.example.quizie.data.model

import android.util.Log

abstract class DTOModel<RESP> {
    protected abstract fun toValidResponse():RESP
    fun getResponse(): RESP? {
        return try {
            toValidResponse()
        } catch (e: NullPointerException) {
            Log.d(e.toString(), "${this::class.java.simpleName} is missing the some properties}" )
            null
        }
    }
}
