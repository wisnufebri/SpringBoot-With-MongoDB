package com.gresstenan.wisnu.controller;

import com.gresstenan.wisnu.models.LayananKurirModel;
import com.gresstenan.wisnu.repository.LayananKurirRepository;
import com.gresstenan.wisnu.service.LayananKurirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/layanan")
public class LayananKurirController {

    @Autowired
    LayananKurirRepository layananKurirRepository;

    @Autowired
    LayananKurirService layananKurirService;

    @GetMapping
    public List<LayananKurirModel> getAllLayanan(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                                 @RequestParam(value = "sortKey", defaultValue = "username") String sortKey) {
        return layananKurirService.getAllLayananKurir(pageNo, sortKey);
    }

    @PostMapping("/insert")
    public LayananKurirModel insertLayanan(@RequestBody LayananKurirModel layananKurirModel) {
        layananKurirService.insertLayananKurir(layananKurirModel);
        return layananKurirModel;
    }
}
