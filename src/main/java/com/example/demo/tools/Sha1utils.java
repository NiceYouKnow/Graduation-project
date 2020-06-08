package com.example.demo.tools;
// import com.example.demo.model.Register;
public class Sha1utils {
    // public static void main(String[] args) {
    //     Register re = new Register();
    //     String x2 = re.getPassword();
    //     String hexDigest2 = Sha1utils.encrypt(x2);
    //     System.out.println("empty:"  + "\nstring:" + hexDigest2);
    // }
    // 填充
    public static StringBuffer PAD(String x) {
        // 消息长度
        int ml; 
        //  消息转换为二进制字符串
        StringBuffer mbStr = new StringBuffer(); 
        for (int i = 0; i < x.length(); ++i) {
            //二进制字符串
            StringBuffer temp = new StringBuffer(Long.toBinaryString(x.charAt(i))); 
            while (temp.length() < 8) {
                temp.insert(0, 0);
            }
            mbStr.append(temp);
        }
        ml = mbStr.length();
        //计算d值 用于补充消息中0的树木
        int d = 447 - ml; 
        while (d < 0) {
            d += 512;
        }
        //将消息长度补足到64位
        StringBuffer l = new StringBuffer(Long.toBinaryString(ml));
        while (l.length() < 64) {
            l.insert(0, 0);
        }
        //填充mbStr
        mbStr.append(1);
        for (int i = 0; i < d; ++i) {
            mbStr.append(0);
        }
        mbStr.append(l);
        return mbStr;
    }
    //循环左移
    public static StringBuffer ROTL(StringBuffer temp, int s) {
        while (temp.length() < 32) {
            temp.insert(0, 0);
        }
        //循环左移
        for (int i = 0; i < s; ++i) {
            temp.append(temp.charAt(0));
            temp.deleteCharAt(0);
        }
        return temp;
    }
    // SHA-1
    public static String encrypt(String x) {
        long h0 = 0x67452301L;
        long h1 = 0xEFCDAB89L;
        long h2 = 0x98BADCFEL;
        long h3 = 0x10325476L;
        long h4 = 0xC3D2E1F0L;
        //SHA-1-PAD
        StringBuffer mbStr = PAD(x);
        //将mbStr分组512位
        int groupNum = mbStr.length() / 512;
        StringBuffer[] mbStrGroup = new StringBuffer[groupNum];
        for (int i = 0; i < groupNum; ++i) {
            mbStrGroup[i] = new StringBuffer(mbStr.substring(i * 512, (i + 1) * 512));
        }
        //计算消息摘要
        for (int i = 0; i < groupNum; ++i) {
            StringBuffer[] w = new StringBuffer[80];
            //初始化 ABCDE
            long a = h0;
            long b = h1;
            long c = h2;
            long d = h3;
            long e = h4;
            //初始化 w0 -- w15
            for (int j = 0; j < 16; ++j) {
                w[j] = new StringBuffer(mbStrGroup[i].substring(j * 32, (j + 1) * 32));
            }
            //初始化 w16 -- w79
            for (int j = 16; j < 80; ++j) {
                w[j] = ROTL(new StringBuffer(Long
                        .toBinaryString(Long.parseLong(w[j - 3].toString(), 2) ^ Long.parseLong(w[j - 8].toString(), 2)
                                ^ Long.parseLong(w[j - 14].toString(), 2) ^ Long.parseLong(w[j - 16].toString(), 2))),
                        1);
            }
            //0-79循环
            long mod = (long) Math.pow(2, 32);
            for (int j = 0; j < 80; ++j) {
                Long f, k;
                if (j >= 0 && j <= 19) {
                    f = (b & c) | ((~b) & d);
                    k = 0x5A827999L;
                } else if (j >= 20 && j <= 39) {
                    f = b ^ c ^ d;
                    k = 0x6ED9EBA1L;

                } else if (j >= 40 && j <= 59) {
                    f = (b & c) | (b & d) | (c & d);
                    k = 0x8F1BBCDCL;
                } else {
                    f = b ^ c ^ d;
                    k = 0xCA62C1D6L;
                }
                long temp = (Long.parseLong(ROTL(new StringBuffer(Long.toBinaryString(a)), 5).toString(), 2) + f + e
                        + Long.parseLong(w[j].toString(), 2) + k) % mod;
                e = d;
                d = c;
                c = Long.parseLong(ROTL(new StringBuffer(Long.toBinaryString(b)), 30).toString(), 2);
                b = a;
                a = temp;
            }
            h0 = (h0 + a) % mod;
            h1 = (h1 + b) % mod;
            h2 = (h2 + c) % mod;
            h3 = (h3 + d) % mod;
            h4 = (h4 + e) % mod;
        }
        //返回最终的消息摘要
        return Long.toHexString(h0) + Long.toHexString(h1) + Long.toHexString(h2) + Long.toHexString(h3)
                + Long.toHexString(h4);
    }
}