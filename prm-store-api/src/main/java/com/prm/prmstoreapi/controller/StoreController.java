package com.prm.prmstoreapi.controller;

import com.prm.prmstoreapi.common.UrlConstant;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RequiredArgsConstructor
@RestController
@RequestMapping(UrlConstant.API)
public class StoreController {
}
