package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.BehaviorLogEntity;
import com.appdevg5.geeks.service.BehaviorLogService;

@RestController
@RequestMapping("/api/behaviorlog")
public class BehaviorLogController {

    @Autowired
    BehaviorLogService bserv;

    @PostMapping("/insertBehaviorLogRecord")
    public BehaviorLogEntity insertBehaviorLogRecord(@RequestBody BehaviorLogEntity behaviorLog) {
        return bserv.insertBehaviorLogRecord(behaviorLog);
    }

    @GetMapping("/getAllBehaviorLogs")
    public List<BehaviorLogEntity> getAllBehaviorLogs() {
        return bserv.getAllBehaviorLogs();
    }

    @PutMapping("/updateBehaviorLog")
    public BehaviorLogEntity updateBehaviorLog(@RequestParam int behaviorId, @RequestBody BehaviorLogEntity newBehaviorLogDetails) {
        return bserv.updateBehaviorLog(behaviorId, newBehaviorLogDetails);
    }

    @DeleteMapping("/deleteBehaviorLog/{behaviorId}")
    public String deleteBehaviorLog(@PathVariable int behaviorId) {
        return bserv.deleteBehaviorLog(behaviorId);
    }
}

