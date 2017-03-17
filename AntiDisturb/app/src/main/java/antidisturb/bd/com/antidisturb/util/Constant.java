package antidisturb.bd.com.antidisturb.util;

/**
 * Created by Administrator on 2017/3/13.
 */
public class Constant {
    public static String convertPhoneNum(String num){
        if(num == null || "".equals(num))
            return "";
        else{
            num = num.replace(" ", "");
            num = num.replace("-", "");
        }
        return num;
    }
}
