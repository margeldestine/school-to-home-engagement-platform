package com.appdevg5.geeks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.appdevg5.geeks.entity.ParentEntity;
import com.appdevg5.geeks.service.ParentService;

@RestController
@RequestMapping("/api/parent")
public class ParentController {

    @Autowired
    ParentService pserv;

    @PostMapping("/insertParentRecord")
    public ParentEntity insertParentRecord(@RequestBody ParentEntity parent) {
        return pserv.insertParentRecord(parent);
    }

    @GetMapping("/getAllParents")
    public List<ParentEntity> getAllParents() {
        return pserv.getAllParents();
    }

    @PutMapping("/updateParent")
    public ParentEntity updateParent(@RequestParam int parentId, @RequestBody ParentEntity newParentDetails) {
        return pserv.updateParent(parentId, newParentDetails);
    }

    @DeleteMapping("/deleteParent/{parentId}")
    public String deleteParent(@PathVariable int parentId) {
        return pserv.deleteParent(parentId);
    }
}
