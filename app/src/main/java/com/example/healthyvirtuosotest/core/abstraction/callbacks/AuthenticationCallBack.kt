package com.example.healthyvirtuosotest.core.abstraction.callbacks

import android.os.Bundle

/**
 * Wieven Authentication interface
 *
 * @param <TUserEntity> Generic type of user object model
 * @author Agustin Giannoni
 * @version
</TUserEntity> */
interface AuthenticationCallBack<TUserEntity> {
    /**
     * Load Authenticator Response
     */
    fun loadAuthenticatorResponse()

    /**
     * Set account Authentication result
     */
    fun setAccountAuthenticatorResult(result: Bundle?)

    /**
     * finish authentication response and set movieResults before finish the activity
     */
    fun finishAuthenticatorResponse()

    /**
     * Finish authentication and set result on this function
     */
    fun finishAuthentication(user: TUserEntity)
}