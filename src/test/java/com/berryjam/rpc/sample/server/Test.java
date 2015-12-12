package com.berryjam.rpc.sample.server;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author huangjinkun.
 * @date 15/12/2
 * @time 上午12:02
 */
public class Test {

    public static void main(String[] args){
        String username = "mediamax-baidu01";
        String password = "allyesbaidu123";

        StringBuilder sb = new StringBuilder();
        sb.append("Basic ");
        String rawStr = username + ":" + password;
        byte[] b = null;
        try {
            b = rawStr.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (b != null) {
            System.out.println(sb.append(new BASE64Encoder().encode(b)).toString());
        } else {
            System.out.println("false");
        }
    }
}
