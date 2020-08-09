package com.gresstenan.wisnu.controller;


import com.gresstenan.wisnu.models.ItemModel;
import com.gresstenan.wisnu.repository.ItemRepository;
import com.gresstenan.wisnu.utility.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AuthenticationManager authenticationManager;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllItem(
            @RequestParam(required = false) String nama,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {

        try {
            List<ItemModel> itemModels = new ArrayList<ItemModel>();
            Pageable paging = PageRequest.of(page, size);

            Page<ItemModel> pageTuts;
            if (nama == null)
                pageTuts = itemRepository.findAll(paging);
            else
                pageTuts = itemRepository.findBynama(nama, paging);

            itemModels = pageTuts.getContent();

            if (itemModels.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            Map<String, Object> response = new HashMap<>();
            response.put("item", itemModels);
            response.put("currentPage", pageTuts.getNumber());
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemModel> getItemById(@PathVariable("id") String id) {
        Optional<ItemModel> result = itemRepository.findById(id);

        if (result.isPresent()) {
            return new ResponseEntity<>(result.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ItemModel> createItem(@RequestBody ItemModel body) {
        try {
            ItemModel result = itemRepository.save(new ItemModel(
                    body.getNama(),
                    body.getStock(),
                    body.getHarga(),
                    body.getDescription(),
                    true,
                    body.getBerat()));
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ItemModel> updateItem(@PathVariable("id") String id, @RequestBody ItemModel body) {
        Optional<ItemModel> result = itemRepository.findById(id);

        if (result.isPresent()) {
            ItemModel itemModel = result.get();
            itemModel.setNama(body.getNama());
            itemModel.setStock(body.getStock());
            itemModel.setHarga(body.getHarga());
            itemModel.setDescription(body.getDescription());
            itemModel.setTerjual(body.isTerjual());
            itemModel.setBerat(body.getBerat());
            return new ResponseEntity<>(itemRepository.save(itemModel), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteItem(@PathVariable("id") String id) {
        try {
            itemRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}