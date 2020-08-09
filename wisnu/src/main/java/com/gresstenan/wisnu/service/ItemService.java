package com.gresstenan.wisnu.service;

import com.gresstenan.wisnu.models.ItemModel;
import com.gresstenan.wisnu.repository.ItemRepository;
import com.gresstenan.wisnu.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public Optional<ItemModel> getItemById(final String id){
        return itemRepository.findById(id);
    }
    
    public void insertItem(final ItemModel item){
        System.out.println();
        itemRepository.save(item);
    }

    public Map<String, Object> updateItem(ItemModel item) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Query query = new Query(new Criteria("id").is(item.getId()));
            Map<String, Object> objectMap = Utility.objectToMap(item);
            Update updateQuery = new Update();
            objectMap.forEach((key, value) -> {
                if (value != null) {
                    updateQuery.set(key, value);
                }
            });
            mongoTemplate.findAndModify(query, updateQuery, ItemModel.class);
            resultMap.put("success", true);
            resultMap.put("message", "user updated");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success", false);
            resultMap.put("message", "user update failed");
        }
        return resultMap;
    }
}