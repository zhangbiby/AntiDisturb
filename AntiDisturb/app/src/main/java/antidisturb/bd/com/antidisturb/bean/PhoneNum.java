package antidisturb.bd.com.antidisturb.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2017/3/11.
 */
public class PhoneNum implements Parcelable {

    public static final int FLAG_NONE = 0;//
    public static final int FLAG_WHITE = 1;//
    public static final int FLAG_BLACK = 2;//

    private String phoneNum ="";//
    private String userName ="";
    private String remark ="";
    private int flag;//

    public PhoneNum() {

    }

    public PhoneNum(String phoneNum, String userName, int flag) {
        this.phoneNum = phoneNum == null ? "" :phoneNum;
        this.userName = userName == null ? "" :userName;
        this.flag = flag;
    }

    public PhoneNum(String phoneNum, String userName, String remark, int flag) {
        this.phoneNum = phoneNum == null ? "" :phoneNum;
        this.userName = userName == null ? "" :userName;
        this.remark = remark == null ? "" :remark;
        this.flag = flag;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(phoneNum);
        dest.writeString(userName);
        dest.writeString(remark);
        dest.writeInt(flag);
    }
    public PhoneNum(Parcel source) {
        phoneNum = source.readString();
        userName = source.readString();
        remark = source.readString();
        flag = source.readInt();
    }
    public static final Parcelable.Creator<PhoneNum> CREATOR = new Creator<PhoneNum>() {
        @Override
        public PhoneNum[] newArray(int size) {
            return new PhoneNum[size];
        }

        @Override
        public PhoneNum createFromParcel(Parcel source) {
            return new PhoneNum(source);
        }
    };
}
