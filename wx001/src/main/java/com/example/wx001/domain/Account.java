package com.example.wx001.domain;

public class Account {
    private Integer accid;
    private String accname;
    private String accpass;
    private String accstate;

    public Account() {
    }

    public Account(Integer accid, String accname, String accpass, String accstate) {
        this.accid = accid;
        this.accname = accname;
        this.accpass = accpass;
        this.accstate = accstate;
    }

    public Integer getAccid() {
        return accid;
    }

    public void setAccid(Integer accid) {
        this.accid = accid;
    }

    public String getAccname() {
        return accname;
    }

    public void setAccname(String accname) {
        this.accname = accname;
    }

    public String getAccpass() {
        return accpass;
    }

    public void setAccpass(String accpass) {
        this.accpass = accpass;
    }

    public String getAccstate() {
        return accstate;
    }

    public void setAccstate(String accstate) {
        this.accstate = accstate;
    }
}
