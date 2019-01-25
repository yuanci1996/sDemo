package com.utils;

import com.alibaba.fastjson.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * http请求金科基础数据签名工具类
 *
 * @author liweibing
 * @since 2018/9/11 下午2:49
 */
public class SignUtils {


    /**
     * 签名生成
     *
     * @param params
     * @return
     * @throws Exception
     */
    public static String signUp(HashMap<String, Object> params) throws Exception {
        if (null == params || params.isEmpty()) {
            return null;
        }
        LinkedHashMap<String, Object> map = sortMapByKey(params);
        StringBuffer buffer = new StringBuffer();
        //进行签名组装
        map.forEach((key, value) -> {
            //如果value为空 则不加入签名
            if (null != value) {
                buffer.append(key + "=" + value + "&");
            }
        });
        //将最后一位的&去掉
        String signString = buffer.toString().substring(0, buffer.length() - 1);
        //将排序的签名信息用MD5加密 再转换为大写输出
        return EncoderByMd5(signString).toUpperCase();
    }

    /**
     * 根据map的key进行排序
     *
     * @param map
     * @param <K> key
     * @param <V> value
     * @return
     */
    public static <K extends Comparable<? super K>, V> LinkedHashMap<K, V> sortMapByKey(Map<K, V> map) {
        LinkedHashMap<K, V> result = new LinkedHashMap<>();
        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByKey()).forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }

    /**
     * 利用MD5进行加密
     *
     * @param str 待加密的字符串
     * @return 加密后的字符串
     * @throws NoSuchAlgorithmException     没有这种产生消息摘要的算法
     */
    public static String EncoderByMd5(String str) throws NoSuchAlgorithmException {
        // 生成一个MD5加密计算摘要
        MessageDigest md = MessageDigest.getInstance("MD5");
        // 计算md5函数
        md.update(str.getBytes());
        return new BigInteger(1, md.digest()).toString(16);
    }

    public static void main(String[] args) throws Exception {
        HashMap<String, Object> map = new HashMap<>();
        map.put("appid", "tqtfohkydkh402ss91");
        map.put("mchid", "1877179068");
        map.put("timestamp", System.currentTimeMillis());
        map.put("noise",StringUtil.UUID());
        map.put("appsecret","vfCnFw1nD3mbF6PL22IO2bsC2VehGRCJ");
        String sign = signUp(map);
        System.out.println("加密后的:"+sign);
        map.put("sign",sign);
        map.remove("appsecret");
        String url="http://api-development.tq-service.com/ucenter/api/base/getcompanylist";
        String s = HttpUtils.postParam(url, map);
        JSONObject jsonObject = JSONObject.parseObject(s);
        System.out.println(jsonObject.toJSONString());
    }
}
