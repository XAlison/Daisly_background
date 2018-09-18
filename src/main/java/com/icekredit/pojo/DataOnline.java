package com.icekredit.pojo;

/**
 * Created by Administrator on 2017/4/11.
 *
 * data_desc 表中的数据
 * 用模型查page
 * 用page对应的did去查 service_data 表
 */

public class DataOnline {

    private int did;

    private String page;

    public void setDid(int did) {
        this.did = did;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getDid() {
        return did;
    }

    public String getPage() {
        return page;
    }
}
