package com.prm.prmstoreapi.controller;

import com.prm.prmstoreapi.common.UrlConstant;
import com.prm.prmstoreapi.model.BrandModel;
import com.prm.prmstoreapi.service.BrandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(UrlConstant.BRANDS)
public class BrandController {
    private final BrandService brandService;

    // Get all Brands
    @GetMapping()
    public List<BrandModel> getAllBrands(){
        log.info("BrandController: Start getAllBrands()");
        List<BrandModel> brandModelList = brandService.getAllBrands();
        log.info("BrandController: End getAllBrands()");
        return  brandModelList;
    }

    // Get all Brands count
    @GetMapping(UrlConstant.COUNT)
    public long countAllBrands(){
        log.info("BrandController: Start countAllBrands()");
        long count = brandService.countAllBrands();
        log.info("BrandController: End countAllBrands()");
        return count;
    }
}
