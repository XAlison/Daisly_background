package com.icekredit.pojo;

import java.util.List;

public class ApiBodies {
    /** flag */
    String flag;

    /** Form Data */
    List<FormData> formData;

    /** Raw */
    Raw  raw;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<FormData> getFormData() {
        return formData;
    }

    public void setFormData(List<FormData> formData) {
        this.formData = formData;
    }

    public Raw getRaw() {
        return raw;
    }

    public void setRaw(Raw raw) {
        this.raw = raw;
    }
}
