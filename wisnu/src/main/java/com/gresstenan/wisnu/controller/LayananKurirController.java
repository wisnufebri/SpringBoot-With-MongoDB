package com.gresstenan.wisnu.controller;

import com.gresstenan.wisnu.models.LayananKurirModel;
import com.gresstenan.wisnu.payload.response.MessageResponse;
import com.gresstenan.wisnu.repository.LayananKurirRepository;
import com.gresstenan.wisnu.service.LayananKurirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/layanan")
public class LayananKurirController {

    @Autowired
    LayananKurirRepository layananKurirRepository;

    @Autowired
    LayananKurirService layananKurirService;

    @GetMapping("/all")
    public List<LayananKurirModel> getAllLayanan(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                                 @RequestParam(value = "sortKey", defaultValue = "username") String sortKey) {
        return layananKurirService.getAllLayananKurir(pageNo, sortKey);
    }

    @GetMapping("/{id}")
    public Optional<LayananKurirModel> getLayananById(@PathVariable String id){
        return layananKurirService.getLayananById(id);
    }

    @PostMapping("/insert")
    public LayananKurirModel insertLayanan(@RequestBody LayananKurirModel layananKurirModel) {
        layananKurirService.insertLayananKurir(layananKurirModel);
        return layananKurirModel;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> deleteById(@RequestParam String id) {
        Map<String, Object> result = new HashMap<>();
        if (layananKurirService.deleteById(id)){
            result.put("Status", true);
            result.put("Massage", "Success");
        } else {
            result.put("Status", false);
            result.put("Massage", "Failed");
        }
        return result;
    }
}
