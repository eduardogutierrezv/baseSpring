package com.example.springback.service;

import java.util.List;

import com.example.springback.entity.tecashe.Comuna;
import com.example.springback.repository.tecashe.ComunaRepository;
import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepo;

    public ResponseRestController<List<Comuna>> getComunaByIdProvincia(Integer idProvincia) {

        ResponseRestController<List<Comuna>> resp = new ResponseRestController<List<Comuna>>();

        try {

            resp.setCode(CodeResponse.SUCCESS);
            resp.setMessage("comuna");
            resp.setBody(this.comunaRepo.findByIdProvincia(idProvincia).orElse(null));

        } catch (Exception e) {
            resp.setCode(CodeResponse.ERROR_SERVIDOR);
            resp.setMessage("ERROR EN EL SERVIDOR " + e);
        }

        return resp;
    }

}
