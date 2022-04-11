package th.ac.kmutnb.aseancovid_19tracking;

import java.io.Serializable;

public class Data implements Serializable {
    private String mText1;
    private String mText2 = "จำนวนผู้ติดเชื้อ : ";
    private int mNum;
    private int todayInfected;
    private int death;
    private int todayDeath;
    private int recovered;
    private int todayRecovered;
    private int mIcon;

    public Data(String mText1, String mText2, int mNum, int todayInfected, int death, int todayDeath, int recovered, int todayRecovered, int mIcon) {
        this.mText1 = mText1;
        this.mText2 = mText2;
        this.mNum = mNum;
        this.todayInfected = todayInfected;
        this.death = death;
        this.todayDeath = todayDeath;
        this.recovered = recovered;
        this.todayRecovered = todayRecovered;
        this.mIcon = mIcon;
    }

    public Data(){}

    public String getmText1() {
        return mText1;
    }

    public void setmText1(String mText1) {
        this.mText1 = mText1;
    }

    public String getmText2() {
        return mText2;
    }

    public void setmText2(String mText2) {
        this.mText2 = mText2;
    }

    public int getmNum() {
        return mNum;
    }

    public void setmNum(int mNum) {
        this.mNum = mNum;
    }

    public int getTodayInfected() {
        return todayInfected;
    }

    public void setTodayInfected(int todayInfected) {
        this.todayInfected = todayInfected;
    }

    public int getDeath() {
        return death;
    }

    public void setDeath(int death) {
        this.death = death;
    }

    public int getTodayDeath() {
        return todayDeath;
    }

    public void setTodayDeath(int todayDeath) {
        this.todayDeath = todayDeath;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(int todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public int getmIcon() {
        return mIcon;
    }

    public void setmIcon(int mIcon) {
        this.mIcon = mIcon;
    }
//    public Data(String mText1, /*String mText2*/ String mNum , int mIcon) {
//        this.mText1 = mText1;
//        //this.mText2 = mText2;
//        this.mNum = mNum;
//        this.mIcon = mIcon;
//    }
//
//    public String getmNum() {
//        return mNum;
//    }
//
//    public void setmNum(String mNum) {
//        this.mNum = mNum;
//    }
//
//    public String getmText1() {
//        return mText1;
//    }
//
//    public void setmText1(String mText1) {
//        this.mText1 = mText1;
//    }
//
//    public String getmText2() {
//        return mText2;
//    }
//
//    public void setmText2(String mText2) {
//        this.mText2 = mText2;
//    }
//
//    public int getmIcon() {
//        return mIcon;
//    }
//
//    public void setmIcon(int mIcon) {
//        this.mIcon = mIcon;
//    }
}
