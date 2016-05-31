package com.example.vaibhav.mvpsample.bean;

import android.text.Html;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vaibhav on 30/5/16.
 */
public class SampleBean {

    @SerializedName("joke")
    private String text;

    public String getText() {

        return text;
    }

    public void setText(String text) {

        this.text = text;
    }

    @Override
    public String toString() {

        return Html.fromHtml(text).toString();
    }
}
