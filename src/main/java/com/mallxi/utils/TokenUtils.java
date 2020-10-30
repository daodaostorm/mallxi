package com.mallxi.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 *
 * @author Administrator
 *
 */
public class TokenUtils {

	static Long  extTime = 1000*60*5L;
	public static String getToken(String name, String password) {

        String token="";
        token= JWT.create()
                    .withClaim("username", name)
                    .withClaim("generatetime",System.currentTimeMillis())
                    .withClaim("exptime",extTime)
                .sign(Algorithm.HMAC256(password));// 以 password 作为 token 的密钥
        return token;
    }

	public static String getUserName(String token) {

		String returnValue = "none";
		
		if (token == null || token.equals("")) {
			return returnValue;
		}
		
		Long gTime = JWT.decode(token).getClaim("generatetime").asLong();
		Long eTime = JWT.decode(token).getClaim("exptime").asLong();
		Long currentTime = System.currentTimeMillis();
		if ((currentTime - gTime) > eTime) {
			return returnValue;
		}

		returnValue= JWT.decode(token).getClaim("username").asString();
        return returnValue;
    }
	
}