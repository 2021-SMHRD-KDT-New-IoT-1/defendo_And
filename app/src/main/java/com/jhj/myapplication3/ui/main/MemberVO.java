package com.jhj.myapplication3.ui.main;

import java.io.Serializable;
import java.util.Date;

public class MemberVO implements Serializable {
    String worker_id;
    String worker_pw;
    String worker_dept;
    String worker_name;
    String worker_phone;
    Date worker_joindate;
    String admin_yesno;
    public MemberVO(){}

    public MemberVO(String worker_id, String worker_pw, String worker_dept, String worker_name, String worker_phone, Date worker_joindate, String admin_yesno) {
        this.worker_id = worker_id;
        this.worker_pw = worker_pw;
        this.worker_dept = worker_dept;
        this.worker_name = worker_name;
        this.worker_phone = worker_phone;
        this.worker_joindate = worker_joindate;
        this.admin_yesno = admin_yesno;
    }

    public String getWorker_id() {
        return worker_id;
    }

    public String getWorker_pw() {
        return worker_pw;
    }

    public String getWorker_dept() {
        return worker_dept;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public String getWorker_phone() {
        return worker_phone;
    }

    public Date getWorker_joindate() {
        return worker_joindate;
    }

    public String getAdmin_yesno() {
        return admin_yesno;
    }
}
