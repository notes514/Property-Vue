package com.guet.property.controller;

import com.alibaba.fastjson.JSONObject;
import com.guet.property.service.*;
import com.guet.property.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台报表处理控制器
 *
 * @author dhxstart
 * @date 2022/1/16 17:45
 */
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private OwnerService ownerService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private ComplaintService complaintService;
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private HouseService houseService;
    @Autowired
    private ChargeDetailService chargeDetailService;
    @Autowired
    private SupplyService supplyService;
    @Autowired
    private ActivityService activityService;

    @GetMapping("/getOwnerCount")
    public JSONObject getOwnerCount() {
        return CommonUtils.successJson(ownerService.count());
    }

    @GetMapping("/getNoticeCount")
    public JSONObject getNoticeCount() {
        return CommonUtils.successJson(noticeService.count());
    }

    @GetMapping("/getRepairCount")
    public JSONObject getRepairCount() {
        return CommonUtils.successJson(repairService.count());
    }

    @GetMapping("/getComplaintCount")
    public JSONObject getComplaintCount() {
        return CommonUtils.successJson(complaintService.count());
    }

    @GetMapping("/getBuildingCount")
    public JSONObject getBuildingCount() {
        return buildingService.getBuildingCount();
    }

    @GetMapping("/getHouseCount")
    public JSONObject getHouseCount() {
        return houseService.getHouseCount();
    }

    @GetMapping("/getChargeDetailCount")
    public JSONObject getChargeDetailCount() {
        return chargeDetailService.getChargeDetailCount();
    }

    @GetMapping("/getSupplyCount")
    public JSONObject getSupplyCount() {
        return supplyService.getSupplyCount();
    }

    @GetMapping("/getLatestActivity")
    public JSONObject getLatestActivity() {
        return activityService.getLatestActivity();
    }

    @GetMapping("/getLatestSupply")
    public JSONObject getLatestSupply() {
        return supplyService.getLatestSupply();
    }

    @GetMapping("/getLatestRepair")
    public JSONObject getLatestRepair() {
        return repairService.getLatestRepair();
    }
}
