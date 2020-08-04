package com.gresstenan.wisnu.service;

import com.gresstenan.wisnu.models.User;
import com.gresstenan.wisnu.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> getAllUser(final Integer pageNo, final String sortKey) {
        final int noOfRecord = 5;
        final Pageable page = PageRequest.of(pageNo, noOfRecord, Sort.by(sortKey));
        final Page<User> pagedResult = userRepository.findAll(page);
        return pagedResult.getContent();
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

    public boolean updateUser(User user) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            Query query = new Query(new Criteria("id").is(user.getId()));
            Map<String, Object> objectMap = Utility.objectToMap(user);
            Update updateQuery = new Update();
            objectMap.forEach((key, value) -> {
                if (value != null) {
                    updateQuery.set(key, value);
                }
            });
            mongoTemplate.findAndModify(query, updateQuery, User.class);
            resultMap.put("success", true);
            resultMap.put("message", "user updated");
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("success", false);
            resultMap.put("message", "user update failed");
        }
        return false;
    }

}