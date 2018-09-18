package com.icekredit.pojo;

/**
 * Created by Administrator on 2017/4/11.
 *
 * suggestion_config 表中的数据
 * 用类名查sid
 */
public class SuggestionOnline {


    private String service_name;

    private int sid;

    private String suggestion_class;

    private String desc;

    public void setSid(int sid) {
        this.sid = sid;
    }


    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public void setSuggestion_class(String suggestion_class) {
        this.suggestion_class = suggestion_class;
    }

    public String getService_name() {
        return service_name;
    }


    public int getSid() {
        return sid;
    }

    public String getDesc() {
        return desc;
    }

    public String getSuggestion_class() {
        return suggestion_class;
    }
}
