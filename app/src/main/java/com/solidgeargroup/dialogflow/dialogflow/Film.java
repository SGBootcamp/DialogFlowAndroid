package com.solidgeargroup.dialogflow.dialogflow;

import java.io.Serializable;

/**
 * Created by siro on 15/05/2018.
 */

public class Film implements Serializable {
    private int mId;
    private String mTitle;
    private String mDesc;
    private int mImage;

    public Film(int id, String title, int image, String desc) {
        mTitle = title;
        mDesc = desc;
        mImage = image;
        mId = id;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        this.mId = id;
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
