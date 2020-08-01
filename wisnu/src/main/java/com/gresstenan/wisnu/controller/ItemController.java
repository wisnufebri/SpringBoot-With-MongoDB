package com.gresstenan.wisnu.controller;


import com.gresstenan.wisnu.models.ItemModel;
import com.gresstenan.wisnu.repository.ItemRepository;
import com.gresstenan.wisnu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    ItemService itemService;

    @GetMapping
    public List<ItemModel> getAll(@RequestParam(value = "page", defaultValue = "0") Integer pageNo,
                                  @RequestParam(value = "sortKey", defaultValue = "username") String sortKey) {
        return itemService.getAllItem(pageNo, sortKey);
    }

    @GetMapping("/{id}")
    public Optional<ItemModel> getById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

//    @PostMapping("")
//    public ItemModel insertItem(@RequestBody ItemModel item) {
//        itemService.insertItem(item);
//        return item;
//    }

    @PostMapping("/insert")
    public ItemModel createItem(@RequestBody ItemModel item) {
        ItemModel result = itemRepository.save(item);
        return result;
    }

    @PutMapping("")
    public Map<String, Object> updateUser(@RequestBody ItemModel item){
        return itemService.updateItem(item);
    }
}