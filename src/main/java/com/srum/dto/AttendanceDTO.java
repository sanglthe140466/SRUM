package com.srum.dto;

import com.srum.util.type.TypeAttendance;

import java.util.Date;

public class AttendanceDTO {
    private Long id;
    private TypeAttendance type;
    private Date date;
    private String note;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeAttendance getType() {
        return type;
    }

    public void setType(TypeAttendance type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
