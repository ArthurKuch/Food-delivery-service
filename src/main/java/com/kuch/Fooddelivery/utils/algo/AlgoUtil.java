package com.kuch.Fooddelivery.utils.algo;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * @author Artur Kuch
 */
public class AlgoUtil {

    public static Algorithm algo(){
        return Algorithm.HMAC256("secret".getBytes()); // "secret" is used to simplify security
    }

}
