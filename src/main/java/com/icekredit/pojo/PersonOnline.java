package com.icekredit.pojo;

/**
 * Created by Administrator on 2017/4/11.
 *
 * 用sid rid 查 传入数据(trans_json, sys_json, base_json)
 *
 * 用传入数据跑本地新模型
 */
public class PersonOnline {

    private int rid;

    private int uid;

    private String name;

    private String id;

    private String phone;

    private String debit;

    private String credit;

    private int sid;

    private String result;

    private String create_date;

    private String trans_json;

    private String sys_json;

    private String base_json;


    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBase_json(String base_json) {
        this.base_json = base_json;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public void setSys_json(String sys_json) {
        this.sys_json = sys_json;
    }

    public void setTrans_json(String trans_json) {
        this.trans_json = trans_json;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public int getSid() {
        return sid;
    }

    public int getUid() {
        return uid;
    }

    public String getBase_json() {
        return base_json;
    }

    public String getCreate_date() {
        return create_date;
    }

    public String getCredit() {
        return credit;
    }

    public String getDebit() {
        return debit;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getResult() {
        return result;
    }

    public String getSys_json() {
        return sys_json;
    }

    public String getTrans_json() {
        return trans_json;
    }
}
