package com.example.springback.service;

import java.util.List;

import com.example.springback.entity.tecashe.Provincia;
import com.example.springback.repository.tecashe.ProvinciaRepository;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaService {

    @Autowired
    private ProvinciaRepository provinciaRepo;

    public ResponseRestController<List<Provincia>> getProvinByIdRegion(Integer idRegion) {
        ResponseRestController<List<Provincia>> resp = new ResponseRestController<List<Provincia>>();

        try {

            resp.setCode(CodeResponse.SUCCESS);
            resp.setMessage("provincias");
            resp.setBody(this.provinciaRepo.findByIdRegion(idRegion).orElse(null));

        } catch (Exception e) {

            resp.setCode(CodeResponse.ERROR_SERVIDOR);
            resp.setMessage("ERROR DEL SERVIDOR " + e);
        }

        return resp;

    }

}
