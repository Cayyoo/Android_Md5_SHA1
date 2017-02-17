package com.example.md5;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Java 自带的加密类MessageDigest类（加密MD5和SHA）
 *
 * MD5算法是不可逆的
 *
 * SHA长度要比MD5多出8个字符（32比特）
 *
 * 普遍使用的三种加密方式：
 * 1、使用位运算符，将加密后的数据转换成16进制
 * 2、使用格式化方式，将加密后的数据转换成16进制（推荐）
 * 3、使用算法，将加密后的数据转换成16进制
 */

/**
 * MD5与SHA-1区别：
 * 由于MD5与SHA-1均是从MD4发展而来，它们的结构和强度等特性有很多相似之处。
 * SHA-1与MD5的最大区别在于其摘要比MD5摘要长32比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。
 * 对于强行攻击：MD5是2128数量级的操作，SHA-1是2160数量级的操作。
 * 对于相同摘要的两个报文的难度：MD5是264数量级的操作，SHA-1是280数量级的操作。
 * 因而，SHA-1对强行攻击的强度更大。
 * 但由于SHA-1的循环步骤比MD5多（80:64）且要处理的缓存大（160比特 : 128比特），SHA-1的运行速度比MD5慢。
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_before_encrypt,et_after_encrypt_md5,et_after_encrypt_sha1;
    private Button btn_md5,btn_sha1,btn_clear;
    private TextView tv_demo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initView();
    }

    public void initView(){
        et_before_encrypt= (EditText) this.findViewById(R.id.et_before_encrypt);
        et_after_encrypt_md5= (EditText) this.findViewById(R.id.et_after_encrypt_md5);
        et_after_encrypt_sha1= (EditText) this.findViewById(R.id.et_after_encrypt_sha1);

        btn_md5= (Button) this.findViewById(R.id.btn_md5);
        btn_sha1= (Button) this.findViewById(R.id.btn_sha1);
        btn_clear= (Button) this.findViewById(R.id.btn_clear);
        tv_demo=(TextView)this.findViewById(R.id.tv_demo);

        btn_md5.setOnClickListener(this);
        btn_sha1.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        tv_demo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String str=et_before_encrypt.getText().toString();

        switch (v.getId()){
            case R.id.btn_md5:
                et_after_encrypt_md5.setText(getMd5(str));
                break;
            case R.id.btn_sha1:
                try {
                    et_after_encrypt_sha1.setText(getMd5OrSHA1(str));
                } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_clear:
                et_before_encrypt.setText("");
                et_after_encrypt_md5.setText("");
                et_after_encrypt_sha1.setText("");
                break;
            case R.id.tv_demo:
                startActivity(new Intent(this,EncryptActivity.class));
                break;
        }
    }

    /**
     * 写法一
     */
    public String getMd5(String info) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(info.getBytes("UTF-8"));//通过使用 update 方法处理数据,使指定的 byte数组更新摘要
            byte[] encryption = md5.digest();// 获得密文完成哈希计算,产生128 位的长整数

            StringBuffer strBuf = new StringBuffer();

            for (int i = 0; i < encryption.length; i++) {//以0x开始的数据表示16进制，0xff换成十进制为255。
                if (Integer.toHexString(0xff & encryption[i]).length() == 1) {//toHexString()获取16进制的字符串
                    strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                }
                else {
                    strBuf.append(Integer.toHexString(0xff & encryption[i]));
                }
            }

            return strBuf.toString();
        }
        catch (NoSuchAlgorithmException e) {
            return "";
        }
        catch (UnsupportedEncodingException e) {
            return "";
        }
    }


    /**
     * 写法二，MD5、SHA1加密算法相似
     */
    public static String getMd5OrSHA1(String string) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] hash;

        hash = MessageDigest.getInstance("SHA1").digest(string.getBytes("UTF-8"));//SHA1加密
        //hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));//MD5加密

        StringBuilder hex = new StringBuilder(hash.length * 2);// 每个字节用 16 进制表示的话，使用两个字符

        for (byte b : hash) {//以0x开始的数据表示16进制，0xFF换成十进制为255，0x10是16
            if ((b & 0xFF) < 0x10)
                hex.append("0");
            hex.append(Integer.toHexString(b & 0xFF));//toHexString()获取16进制的字符串
        }

        return hex.toString();
    }

}
