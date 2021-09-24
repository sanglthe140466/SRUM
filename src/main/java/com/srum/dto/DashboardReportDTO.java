package com.srum.dto;

/**
 * AUTHOR:TuNT26
 * CREATED DATE: 14/09/2021
 */
public class DashboardReportDTO {

    private int countWaiting;

    private int countRunning;

    private int countRelease;

    public DashboardReportDTO() {
    }

    public DashboardReportDTO(int countWaiting, int countRunning, int countRelease) {
        this.countWaiting = countWaiting;
        this.countRunning = countRunning;
        this.countRelease = countRelease;
    }

    public int getCountWaiting() {
        return countWaiting;
    }

    public void setCountWaiting(int countWaiting) {
        this.countWaiting = countWaiting;
    }

    public int getCountRunning() {
        return countRunning;
    }

    public void setCountRunning(int countRunning) {
        this.countRunning = countRunning;
    }

    public int getCountRelease() {
        return countRelease;
    }

    public void setCountRelease(int countRelease) {
        this.countRelease = countRelease;
    }
}
