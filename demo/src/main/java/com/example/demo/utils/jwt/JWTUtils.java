package com.example.demo.utils.jwt;


import com.example.demo.exception.CommonException;
import com.example.demo.utils.JSONUtil;
import com.example.demo.utils.StatusUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * JWT工具
 */
@Component
public class JWTUtils {
    //服务器的key 用于做加解密的key数据，如果可以使用客服端生成的key，当前定义可以不使用
    @Value("${jwt.config.key}")
    private String key;//私钥
    @Value("${jwt.config.ttl}")
    private long ttl;//token有效期

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 生成加私钥
     * @return
     */
    public SecretKey generalKey() {
        try {
            byte[] encodedKey = key.getBytes("utf-8");
            SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
            return key;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 签发JWT，创建token的方法
     *
     * @param id        jwt 唯一的身份标识，主要用来作为一次性token
     * @param iss       jwt签发者
     * @param subject   jwt所面向的用户，payload中记录public claims  （用户）
     * @return token 一次性的 是为一个用户的有效登陆周期准备的一个token，用户退出 超时 token 失效
     * @throws Exception
     */
    public String createJWT(String id, String iss, String subject) {
        //加密算法
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        //当前时间
        long nowMillis = System.currentTimeMillis();
        //当前时间的日期格式
        Date now = new Date(nowMillis);
        SecretKey secretKey = generalKey();
        //创建JWT的构建器，就是使用指定的信息和加密算法，生成token的工具

        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .setIssuer(iss)
                .setId(id)//设置身份标识（唯一）可以使用主键id/客户端ip/服务器生成的随机数据
                .setIssuedAt(now) //token 生成的时间
                .signWith(signatureAlgorithm, secretKey); //设定密匙和算法

        if (ttl >= 0) {//有效期
            long expMillis = nowMillis + ttl;
            Date expDate = new Date(expMillis);//失效时间
            builder.setExpiration(expDate);
        }
        return builder.compact();//生成token
    }

    /**
     * 验证JWT
     *
     * @param token
     * @return
     */
    public Claims validateJWT(String token) {

        try {
            return parseJWT(token);
        } catch (ExpiredJwtException e) {//超时
            e.printStackTrace();
            throw new CommonException(StatusUtil.TOKEN_TIMEOUT,"登录超时");
        } catch (SignatureException e) {//失败
            e.printStackTrace();
            throw new CommonException(StatusUtil.TOKEN_ERROR,"签名无效");
        } catch (Exception e) {
            e.printStackTrace();
            throw new CommonException(StatusUtil.TOKEN_ERROR,"签名解析失败");
        }
    }

    /**
     * 解析JWT字符串
     *
     * @param token
     * @return
     */
    public Claims parseJWT(String token) {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();//token中记录的payload数据（claims）
    }


    /**
     * 解析Token获取Subject数据
     * @param token
     * @return
     */
    public UserSubject getUserSubject(String token){
        UserSubject userSubject = JSONUtil.jsonToPojo(parseJWT(token).getSubject(), UserSubject.class);
        return userSubject;
    }

    /**
     * 生成Subject信息
     *
     * @param subObj 要转换的对象
     * @return java对象-》json字符串出错时返回null
     */
    public String generalSubject(Object subObj) {
        try {
            return MAPPER.writeValueAsString(subObj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
