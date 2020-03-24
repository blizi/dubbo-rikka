package com.rikka.common.utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rikka.common.constant.SecretConstant;

import java.util.Date;
import java.util.List;

public class JwtUtil {
    /**
     * 生成jwt token
     * @param body  token存放的内容（user主键）
     * @return
     */

    public static String getToken(Long body) {
        String token="";
        token= JWT.create().withAudience(String.valueOf(body))
                .withExpiresAt(DateUtil.offsetSecond(new Date(),50))        //超时时间
                .sign(Algorithm.HMAC256(SecretConstant.SECRET.getName()));
        return token;
    }

    /**
     * 解析   jwt token
     * @param token
     * @param secret    密钥
     * @return
     */
    public static List<String> analyToken(String token, String secret){
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
        DecodedJWT verify = null;
        try {
            verify = jwtVerifier.verify(token);
            //System.out.println(verify.getNotBefore());
        } catch (JWTVerificationException e) {
            e.printStackTrace();
            throw new RuntimeException("token 解析失败");
        }
        return verify.getAudience();
    }

//    public static void main(String[] args) {
//        getToken(1221L);
//    }

}
