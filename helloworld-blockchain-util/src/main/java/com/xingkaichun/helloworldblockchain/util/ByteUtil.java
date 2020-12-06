package com.xingkaichun.helloworldblockchain.util;

import com.google.common.primitives.Bytes;

import java.util.List;

/**
 * Bytes工具类
 *
 * @author 邢开春 微信HelloworldBlockchain 邮箱xingkaichun@qq.com
 */
public class ByteUtil {

    /**
     * long转换为(大端模式)32个字节的字节数组(32*8=256个bit)。
     */
    public static byte[] longToBytes32BigEndian(long value) {
        byte[] bytes = new byte[32];
        bytes[31] = (byte)(0xFF & (value));
        bytes[30] = (byte)(0xFF & (value >> 8));
        bytes[29] = (byte)(0xFF & (value >> 16));
        bytes[28] = (byte)(0xFF & (value >> 24));
        bytes[27] = (byte)(0xFF & (value >> 32));
        bytes[26] = (byte)(0xFF & (value >> 40));
        bytes[25] = (byte)(0xFF & (value >> 48));
        bytes[24] = (byte)(0xFF & (value >> 56));
        return bytes;
    }

    /**
     * 拼接字节数组。计算[传入字节数组]的长度，然后将长度转为4个字节的字节数组(大端)，然后将长度字节数组拼接在[传入字节数组]前，然后返回。
     */
    public static byte[] concatLengthBytes(byte[] value) {
        return Bytes.concat(longToBytes32BigEndian(value.length),value);
    }

    /**
     * 拼接字节数组。
     */
    public static byte[] concatLengthBytes(List<byte[]> values) {
        byte[] concatBytes = longToBytes32BigEndian(values.size());
        for(byte[] value:values){
            concatBytes = Bytes.concat(concatBytes,value);
        }
        return concatBytes;
    }


    /**
     * 字节数组所占比特的位数
     */
    public static long bitCount(byte[] value) {
        if(value == null){
            return 0;
        }
        return value.length * 8;
    }
}
