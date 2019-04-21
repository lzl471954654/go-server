package utils;

public class IntConvertUtils {

    public static byte[] getShortBytes(short data){
        byte[] s = {(byte)0xff,(byte)0xff};
        s[1] = (byte)((data)&s[1]);
        data = (short)(data>>>8);
        s[0] = (byte)((data)&s[0]);
        return s;
    }

    public static short getShortByByteArray(byte[] bytes){
        short s = 0;
        s = (short)(s|bytes[0]);
        s = (short)(s << 8);
        s = (short)(s|bytes[1]);
        return s;
    }

    public static byte[] getIntegerBytes(int data){
        byte[] s = {(byte)0xff,(byte)0xff,(byte)0xff,(byte)0xff};
        s[3] = (byte)((data)&s[3]);
        for(int i = 2;i>=0;i--){
            data = data>>8;
            s[i] = (byte)((data)&s[i]);
        }
        return s;
    }

    public static int getIntegerByByteArray(byte[] data){
        int s = 0;
        s = (s|(data[0]&0xff));
        for (int i = 1; i <data.length; i++) {
            s = s<<8;
            s = s|(data[i]&0xff);
        }
        return s;
    }
}
