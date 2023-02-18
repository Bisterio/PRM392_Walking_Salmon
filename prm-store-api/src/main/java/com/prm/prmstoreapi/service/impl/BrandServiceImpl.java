package com.prm.prmstoreapi.service.impl;

import com.prm.prmstoreapi.model.BrandModel;
import com.prm.prmstoreapi.repository.BrandRepository;
import com.prm.prmstoreapi.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    // Get all Brands
    public List<BrandModel> getAllBrands(){
        log.info("BrandServiceImpl: Start getAllBrands()");
        List<BrandModel> brandModelList = brandRepository.findAll().stream().map(BrandModel::new).toList();
        log.info("BrandServiceImpl: End getAllBrands()");
        return brandModelList;
    }

    //Get all Brand's count
    public long countAllBrands(){
        log.info("BrandServiceImpl: Start countAllBrands()");
        long count = brandRepository.count();
        log.info("BrandServiceImpl: End countAllBrands()");
        return count;
    }
}
