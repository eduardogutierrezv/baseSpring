package com.example.springback.service;

import com.example.springback.util.CodeResponse;
import com.example.springback.util.ResponseRestController;
import com.example.springback.vo.GetRutVerificadorRespVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

@Service
public class ObtenerDigitoVerificadorService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${url.completa.microservicio.externo}")
    private String urlMicroservicio;

    @Value("${password.header}")
    private String passHeader;

    @Value("${token.header}")
    private String tokenHeader;

    private static final String PASSWORD_HEADER = "passwordHeader";

    private static final String TOKEN_HEADER = "tokenHeader";

    public ResponseRestController<GetRutVerificadorRespVO> getDigitoVerificador(String rut, String numeroSerie) {

        ParameterizedTypeReference<ResponseRestController<GetRutVerificadorRespVO>> parameterizedTypeReference = new ParameterizedTypeReference<ResponseRestController<GetRutVerificadorRespVO>>() {
        };

        ResponseRestController<GetRutVerificadorRespVO> resp = new ResponseRestController<GetRutVerificadorRespVO>();

        try {

            resp = this.restTemplate.exchange(this.rutaRutNumeroSerie(rut, numeroSerie), HttpMethod.GET,
                    this.getHttpEntity(), parameterizedTypeReference).getBody();

        } catch (Exception e) {
            resp.setCode(CodeResponse.ERROR_SERVIDOR);
            resp.setMessage("ERROR DEL SERVIDOR " + e);
        }

        return resp;

    }

    private String rutaRutNumeroSerie(String rut, String nSerie) {
        return urlMicroservicio + "/" + rut + "/" + nSerie;
    }

    private HttpEntity<Object> getHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add(PASSWORD_HEADER, passHeader);
        headers.add(TOKEN_HEADER, tokenHeader);
        return new HttpEntity<>(headers);
    }
}
