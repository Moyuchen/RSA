package com.rsa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        HashMap<String, Object> map = null;
        try {
            map = RSAUtils.getKeys();

        //生成公钥和私钥
        RSAPublicKey publicKey = (RSAPublicKey) map.get("public");
        RSAPrivateKey privateKey = (RSAPrivateKey) map.get("private");

        //模
        String modulus = publicKey.getModulus().toString();
        //公钥指数
        String public_exponent = publicKey.getPublicExponent().toString();
        //私钥指数
        String private_exponent = privateKey.getPrivateExponent().toString();
        //明文
        String ming = "你好";
            Log.i("=======================明文", "onCreate: "+ming);

        //使用模和指数生成公钥和私钥
        RSAPublicKey pubKey = RSAUtils.getPublicKey(modulus, public_exponent);
        RSAPrivateKey priKey = RSAUtils.getPrivateKey(modulus, private_exponent);
        //加密后的密文
        String mi = RSAUtils.encryptByPublicKey(ming, pubKey);
            Log.i("==============加密后的密文", "onCreate: "+mi);
            Log.i("==============加密后的密文的长度：", "onCreate: length:"+mi.length());
        //解密后的明文
        ming = RSAUtils.decryptByPrivateKey(mi, priKey);
            Log.i("==================解密后的明文", "onCreate: "+ming);
        System.err.println(ming);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
