package org.example.controllers;

import org.example.api.AspectApi;
import org.example.aspects.NewsAspect;
import org.example.model.AspectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AspectController implements AspectApi {

    @Autowired
    NewsAspect newsAspect;

    @Override
    public ResponseEntity<AspectDTO> getAverageNewsContentLength() {

        AspectDTO aspectDTO = new AspectDTO();
        aspectDTO.setMethod("Average news content's length");
        aspectDTO.setResult(newsAspect.getNewsAverageLength());

        return new ResponseEntity<>(aspectDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AspectDTO> getLast15MinNewsCount() {
        AspectDTO aspectDTO = new AspectDTO();
        aspectDTO.setMethod("Last 15 min news count");
        aspectDTO.setResult(newsAspect.getLastMinCount());

        return new ResponseEntity<>(aspectDTO, HttpStatus.OK);
    }
}
