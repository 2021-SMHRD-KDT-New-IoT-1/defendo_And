package com.jhj.myapplication3.ui.main;

public class AttendanceVO {

    String worker_id;
    String start_time;
    String end_time;
    String att_type;

    public AttendanceVO(String worker_id, String start_time, String end_time, String att_type) {
        this.worker_id = worker_id;
        this.start_time = start_time;
        this.end_time = end_time;
        this.att_type = att_type;
    }

    public String getWorker_id() {
        return worker_id;
    }
    public String getStart_time() {
        return start_time;
    }
    public String getEnd_time() {
        return end_time;
    }
    public String getAtt_type() {
        return att_type;
    }
}
