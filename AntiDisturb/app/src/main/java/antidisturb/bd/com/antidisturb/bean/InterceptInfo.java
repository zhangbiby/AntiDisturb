package antidisturb.bd.com.antidisturb.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/13.
 */
public class InterceptInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final int TYPE_CALL = 2;// the call
    public static final int TYPE_SMS = 1;// the sms
    public static final int TYPE_ERROR = 0;//

    private String itemNum = "";// phoneNum
    private String itemName = "";// the user name
    private String itemInfo = "";// content
    private Long time = (long) 0;// time
    private int type;// the type

    public InterceptInfo() {

    }

    public InterceptInfo(String itemNum, String itemName) {
        this.itemNum = itemNum == null ? "" :itemNum;
        this.itemName = itemName == null ? "" :itemName;
    }

    public InterceptInfo(String itemNum, String itemName, String itemInfo,
                         Long time, int type) {
        this.itemNum = itemNum == null ? "" :itemNum;
        this.itemInfo = itemInfo == null ? "" :itemInfo;
        this.itemName = itemName == null ? "" :itemName;
        this.time = time;
        this.type = type;
    }
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? "" :itemName;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo == null ? "" :itemInfo;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum == null ? "" :itemNum;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public Long getTime() {
        return time;
    }

    public void setType(int type) {
        this.type = type;
    }
}
