package com.example.springbootdemo.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class JwtUtil {
    private static final String KEY = "ShbLogin";

    //生成jwt令牌
    public static String genToken(Map<String, Object>claims){
        return JWT.create()
                .withClaim("claims", claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 *2))
                .sign(Algorithm.HMAC256(KEY));
    }

    //检验jwt令牌
    public  static final Map<String, Object> parseToken(String token){
        return JWT.require(Algorithm.HMAC256(KEY))
                .build()
                .verify(token)
                .getClaim("claims")
                .asMap();
    }
}
