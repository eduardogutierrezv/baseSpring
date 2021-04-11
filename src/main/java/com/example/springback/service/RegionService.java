package com.example.springback.service;

import java.util.List;

import com.example.springback.entity.tecashe.Region;
import com.example.springback.repository.tecashe.RegionRepository;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepo;

    public ResponseRestController<List<Region>> getAllRegion() {
        ResponseRestController<List<Region>> resp = new ResponseRestController<List<Region>>();
        resp.setCode(CodeResponse.SUCCESS);
        resp.setMessage("regiones");
        resp.setBody(this.regionRepo.findAll());

        return resp;
    }

}
