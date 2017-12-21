package cn.lhzs.common.uuid;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import cn.lhzs.common.util.StringUtil;

/**
 * UUID生成工具类
 */
public class UUIDUtil {

    private static String count = "0000";
    private static String dateValue = "20161022";
    private static String drp = "DRP";

    public static String[] chars = new String[] { "a", "b", "c", "d", "e", "f",
            "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
            "t", "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z" };

    /**
     * 产生流水号
     */
    public synchronized static String getWaybillGenerate(String str) {
        long No = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String nowdate = sdf.format(new Date());
        No = Long.parseLong(nowdate);
        if (!(String.valueOf(No)).equals(dateValue)) {
            count = "0000";
            dateValue = String.valueOf(No);
        }
        String num = String.valueOf(No);
        num += getNo(count);
        num = str + num;
        return num;
    }


    /**
     * 返回当天的订单数+1
     */
    public static String getNo(String s) {
        String rs = s;
        int i = Integer.parseInt(rs);
        i += 1;
        rs = "" + i;
        for (int j = rs.length(); j < 4; j++) {
            rs = "0" + rs;
        }
        count = rs;
        return rs;
    }


    /**
     * 获取单号(判断服务器是否重启导致可能出现重复订单号)
     */
    public synchronized static String getNewCode(String maxCode,String str) {
        if(StringUtil.isNotBlank(maxCode)){
            String nyr = maxCode.substring(1, 9); // 获取年月日字符串
            String countV = maxCode.substring(9); // 获取流水号
            if (Integer.valueOf(countV) > Integer.valueOf(count)) {
                dateValue = nyr;
                count = String.valueOf(countV);
            }
        }
        return getWaybillGenerate(str);
    }



    public static String generateUUID(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-","");
    }

    /**
     * 生成短长度uuid
     * @param length 生成id长度，范围5-32位
     * @return
     */
    public static String generateShortUuid(int length) {
        if(length > 32 || length < 5)
            throw new IllegalArgumentException();
        StringBuilder sb = new StringBuilder(length);
        String uuid = generateUUID();
        int size = uuid.length() / length;
        for (int i = 0; i < length; i++) {
            String str = uuid.substring(i * size, i * size + size);
            int x = Integer.parseInt(str, 16);
            sb.append(chars[x % 0x3E]);
        }
        return sb.toString();
    }

    public static String generateBossSubsNo() {
        return drp + generateShortUuid(11);
    }

    /**
     * 运单号生成策略
     * @Author ekun
     * @Date 2016/7/30 17:26
     */
    public static Long waybillGenerate(){
            String s = "";
            //获得当前时间的最小精度
            SimpleDateFormat format =  new SimpleDateFormat("yyyyMMddHHmmssSSS");
            s = format.format(new Date());
            //获得三位随机数
            Random random = new Random();
            for(int i = 0; i < 2; i++){
                s = s + random.nextInt(9);
            }
            return Long.valueOf(s);
    }

    public static String getCurrentTimeMillis() {
        return String.valueOf(System.currentTimeMillis());
    }

    public static final void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
//            System.out.println(getWaybillGenerate("6"));
            System.out.println(generateShortUuid(31));
        }
    }
}
