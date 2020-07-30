package com.gresstenan.wisnu.controller;

import com.gresstenan.wisnu.models.KurirModel;
import com.gresstenan.wisnu.repository.KurirRepository;
import com.gresstenan.wisnu.service.KurirService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/kurir")
public class KurirController {

    @Autowired
    KurirRepository kurirRepository;

    @Autowired
    KurirService kurirService;

    @GetMapping
    public List<KurirModel> getAllKurir(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                        @RequestParam(value = "sortKey", defaultValue = "username") String sortKey) {
        return kurirService.getAllKurir(pageNo, sortKey);
    }

    @GetMapping("/{id}")
    public Optional<KurirModel> getKurirById(@PathVariable String id){
        return kurirService.getKurirById(id);
    }

    @PostMapping("/insert")
    public KurirModel insertKurir(@RequestBody KurirModel kurir) {
        kurirService.insertKurir(kurir);
        return kurir;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody KurirModel body) {
        Map<String, Object> result = new HashMap<>();
        if (kurirService.updateKurir(body)) {
            result.put("Status", true);
            result.put("Massage", "Berhasil Update data");
        } else {
            result.put("Status", false);
            result.put("Massage", "Gagal Update");
        }
        return result;
    }

    @DeleteMapping("/delete")
    public Map<String, Object> deleteById(@RequestParam String id) {
        Map<String, Object> result = new HashMap<>();
        if (kurirService.deleteById(id)){
            result.put("Status", true);
            result.put("Massage", "Success");
        } else {
            result.put("Status", false);
            result.put("Massage", "Failed");
        }
        return result;
    }
}
