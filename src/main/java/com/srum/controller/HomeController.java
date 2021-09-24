package com.srum.controller;

import com.srum.dto.DashboardReportDTO;
import com.srum.service.DashboardService;
import com.srum.util.type.TypeClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

/**
 * @author ThoNN1
 */
@Controller
public class HomeController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/")
    public String index() {
        return "dashboard";
    }

    @GetMapping("/report-fresher-class-default")
    public ResponseEntity<DashboardReportDTO> loadFresherClassDefault() {
        return ResponseEntity.ok(dashboardService.countAllClassByStatusAndType(TypeClass.FRESHER));
    }

    @GetMapping("/report-fresher-class")
    public ResponseEntity<DashboardReportDTO> loadFresherClass(@RequestParam("startDate")
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date openDate,
                                                               @RequestParam("endDate")
                                                               @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(dashboardService.countAllClassByStatusAndTypeInPeriod(TypeClass.FRESHER, openDate, endDate));
    }

    @GetMapping("/report-fresher-trainee-default")
    public ResponseEntity<DashboardReportDTO> loadFresherTraineeDefault() {
        return ResponseEntity.ok(dashboardService.countAllTraineeByClassStatusAndClassType(TypeClass.FRESHER));
    }

    @GetMapping("/report-fresher-trainee")
    public ResponseEntity<DashboardReportDTO> loadFresherTrainee(@RequestParam("startDate")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date openDate,
                                                                 @RequestParam("endDate")
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(dashboardService.countAllTraineeByClassStatusAndClassTypeInPeriod(TypeClass.FRESHER, openDate, endDate));
    }

    @GetMapping("/report-internship-class-default")
    public ResponseEntity<DashboardReportDTO> loadInternshipClassDefault() {
        return ResponseEntity.ok(dashboardService.countAllClassByStatusAndType(TypeClass.INTERN));
    }

    @GetMapping("/report-internship-class")
    public ResponseEntity<DashboardReportDTO> loadInternshipClass(@RequestParam("startDate")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date openDate,
                                                                  @RequestParam("endDate")
                                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(dashboardService.countAllClassByStatusAndTypeInPeriod(TypeClass.INTERN, openDate, endDate));
    }

    @GetMapping("/report-internship-trainee-default")
    public ResponseEntity<DashboardReportDTO> loadInternshipTraineeDefault() {
        return ResponseEntity.ok(dashboardService.countAllTraineeByClassStatusAndClassType(TypeClass.INTERN));
    }

    @GetMapping("/report-internship-trainee")
    public ResponseEntity<DashboardReportDTO> loadInternshipTrainee(@RequestParam("startDate")
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date openDate,
                                                                    @RequestParam("endDate")
                                                                    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        return ResponseEntity.ok(dashboardService.countAllTraineeByClassStatusAndClassTypeInPeriod(TypeClass.INTERN, openDate, endDate));
    }
}
