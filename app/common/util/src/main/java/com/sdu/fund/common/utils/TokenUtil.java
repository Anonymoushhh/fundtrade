package com.sdu.fund.common.utils;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

import com.google.common.collect.Maps;
import com.sdu.fund.common.exception.CommonException;
import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.UUID;

public class TokenUtil {

    /**
     * token 过期时间, 单位: 毫秒. 这个值表示 5分钟
     */
    public static final long TOKEN_EXPIRED_TIME = 5 * 60 * 1000;

    /**
     * 由字符串生成加密Token
     *
     * @return
     */
    private static String genToken(Map<String, Object> claims) {
        StringBuilder token = new StringBuilder(UUID.randomUUID().toString());
        for (Map.Entry<String, Object> entry : claims.entrySet()) {
            token.append(entry.getValue());
        }
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte md5[] = md.digest(token.toString().getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 根据userId和openid生成token
     */
    public static String generateToken(String openId, Long userId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("userId", userId);
        map.put("openId", openId);
        return genToken(map);
    }

    public static void main(String[] args) {
        String token = TokenUtil.generateToken("1", 2L);
        System.out.println(token);
    }
}

