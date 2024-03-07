package com.staywell.controller;

import com.staywell.dto.request.RentCarRequest;
import com.staywell.dto.response.RentCarResponse;
import com.staywell.service.RentCarService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/staywell/rentCar")
@AllArgsConstructor
public class RentCarController {
//
//    @Autowired
//    RentCarService rentCarService;
//    @PostMapping("/rent")
//    public ResponseEntity<RentCarResponse> rentCar(@RequestBody RentCarRequest rentCarRequest) {
//        RentCarResponse res = rentCarService.rentCar(rentCarRequest);
//        return new ResponseEntity<>(res, HttpStatus.CREATED);
//    }
}
