package com.example.somesh.chat;

/**
 * Created by Somesh on 1/28/2018.
 */

public class WitModel {

    public String _text;
    public String value;
    public double confidence;
    public String type;

    public WitModel(String _text, String value, double confidence, String type){
        this._text = _text;
        this.value = value;
        this.confidence = confidence;
        this.type = type;
    }
    public String get_text() {
        return _text;
    }

    public void set_text(String _text) {
        this._text = _text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public double getConfidence() {
        return confidence;
    }

    public void setConfidence(double confidence) {
        this.confidence = confidence;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
