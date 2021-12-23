package com.jhj.myapplication3.ui.main;

public class ListVO {

    String worker_name;
    String hm_lock;
    String education;
    String worker_joindate;
    String worker_id;

    public ListVO(String worker_name,String worker_joindate,String hm_lock, String education, String worker_id){
        this.worker_name=worker_name;
        this.worker_joindate=worker_joindate;
        this.hm_lock=hm_lock;
        this.education=education;
        this.worker_id=worker_id;
    }

    public String getWorker_name() {
        return worker_name;
    }

    public String getHm_lock() {
        return hm_lock;
    }

    public String getEducation() {
        return education;
    }

    public String getWorker_joindate() {
        return worker_joindate;
    }

    public String getWorker_id() {
        return worker_id;
    }
}
