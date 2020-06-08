package com.example.demo.jni;
public class Test {
    static {
        System.loadLibrary("AES");
    }
    
    
    
    // public static native String dem();
    public static native String AES_ENC(String str,String key);
    public static native String AES_DEC(String str,String key);
  
    public static void main(String[] args) {
        // String t = dem();
        // System.out.println(t);
   } 
}