package com.gresstenan.wisnu.service;

import com.gresstenan.wisnu.models.KurirModel;
import com.gresstenan.wisnu.repository.KurirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KurirService {

    @Autowired
    KurirRepository kurirRepository;

    public Optional<KurirModel> getKurirById(final String id) {
        return kurirRepository.findById(id);
    }

    public List<KurirModel> getAllKurir(final Integer pageNo, final String sortKey) {
        final int noOfRecord = 5;
        final Pageable page = PageRequest.of(pageNo, noOfRecord, Sort.by(sortKey));
        final Page<KurirModel> pagedResult = kurirRepository.findAll(page);
        return pagedResult.getContent();
    }

    public void insertKurir(final KurirModel kurir) {
        System.out.println();
        kurirRepository.save(kurir);
    }

    public boolean updateKurir(final KurirModel body) {
        final Optional<KurirModel> result = kurirRepository.findById(body.getId());
        if (result != null) {
            try {
                kurirRepository.save(body);
                return true;
            } catch (final Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean deleteById(final String id) {
        final Optional<KurirModel> result = kurirRepository.findById(id);
        if (result != null) {
            try {
                kurirRepository.deleteById(id);
                return true;
            } catch (final Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}
