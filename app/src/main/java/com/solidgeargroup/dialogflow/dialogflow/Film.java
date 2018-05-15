package com.solidgeargroup.dialogflow.dialogflow;

/**
 * Created by siro on 15/05/2018.
 */

public class Film {
    private String mTitle;
    private String mDesc;
    private int mImage;

    public Film(String title, int image, String desc) {
        mTitle = title;
        mDesc = desc;
        mImage = image;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDesc;
    }

    public void setDescription(String desc) {
        this.mDesc = desc;
    }

    public int getImage() {
        return mImage;
    }

    public void setImage(int image) {
        this.mImage = mImage;
    }
}
