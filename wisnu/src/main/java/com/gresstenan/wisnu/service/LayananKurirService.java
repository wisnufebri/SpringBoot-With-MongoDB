package com.gresstenan.wisnu.service;

import com.gresstenan.wisnu.models.KurirModel;
import com.gresstenan.wisnu.models.LayananKurirModel;
import com.gresstenan.wisnu.repository.KurirRepository;
import com.gresstenan.wisnu.repository.LayananKurirRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class LayananKurirService {

    @Autowired
    LayananKurirRepository layananKurirRepository;

    @Autowired
    KurirRepository kurirRepository;

    public Optional<LayananKurirModel> getLayananById(final String id) {
        return layananKurirRepository.findById(id);
    }

    public List<LayananKurirModel> getAllLayananKurir(final Integer pageNo, final String sortKey) {
        final int noOfRecord = 5;
        final Pageable page = PageRequest.of(pageNo, noOfRecord, Sort.by(sortKey));
        final Page<LayananKurirModel> pagedResult = layananKurirRepository.findAll(page);
        return pagedResult.getContent();
    }

    public void insertLayananKurir(LayananKurirModel layananKurirModel) {
        layananKurirModel.getNama();
        layananKurirModel.getHarga();
        layananKurirModel.getEstimasi();

        Set<KurirModel> kurirModels = layananKurirModel.getKurir();
        Set<KurirModel> kurir = new HashSet<>();


        layananKurirRepository.save(layananKurirModel);
    }
    
    public boolean deleteById(final String id) {
        final Optional<LayananKurirModel> result = layananKurirRepository.findById(id);
        if (result != null) {
            try {
                layananKurirRepository.deleteById(id);
                return true;
            } catch (final Exception e) {
                return false;
            }
        } else {
            return false;
        }
    }
}

