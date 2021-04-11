package com.example.springback.controllers;

import java.util.List;

import javax.validation.Valid;

import com.example.springback.entity.tecashe.Comuna;
import com.example.springback.entity.tecashe.Provincia;
import com.example.springback.entity.tecashe.Region;
import com.example.springback.service.ComunaService;
import com.example.springback.service.ProvinciaService;
import com.example.springback.service.RegionService;
import com.example.springback.util.ResponseRestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "")
@RequestMapping(value = "/public/v1")
public class GeolocalizacionRestController {

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProvinciaService provinciaService;

    @Autowired
    private ComunaService comunaService;

    @GetMapping(value = "/geolocalizacion/region")
    public ResponseRestController<List<Region>> traerValores() {
        return this.regionService.getAllRegion();
    }

    @GetMapping(value = "/geolocalizacion/provincia/{idRegion}")
    public ResponseRestController<List<Provincia>> getProvinciaById(@Valid @PathVariable Integer idRegion) {
        return this.provinciaService.getProvinByIdRegion(idRegion);
    }

    @GetMapping(value = "/geolocalizacion/comuna/{idProvincia}")
    public ResponseRestController<List<Comuna>> getComunaById(@Valid @PathVariable Integer idProvincia) {
        return this.comunaService.getComunaByIdProvincia(idProvincia);
    }

}
