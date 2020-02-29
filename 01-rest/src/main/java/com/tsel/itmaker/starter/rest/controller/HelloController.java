package com.tsel.itmaker.starter.rest.controller;

import com.tsel.itmaker.starter.rest.entity.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(value = "/subscribers/{msisdn}", produces= MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Object> getSubscriber(@PathVariable(value="msisdn") Long msisdn) {
        logger.info("TEST!");
        logger.warn("Incoming Request|No param id specified");
        if (msisdn == null) {
            logger.warn("Incoming Request|No param msisdn specified");
            return new ResponseEntity<Object>("Parameter MSISDN not supplied", HttpStatus.BAD_REQUEST);
        }
        String strMsisdn = String.valueOf(msisdn);
        // Subscriber subscriber = new Subscriber(msisdn);
        ResponseEntity<Object> resp = null;

        if (!strMsisdn.startsWith("62")) {
            resp = new ResponseEntity<Object>("MSISDN must start with 62", HttpStatus.BAD_REQUEST);
        } else if (msisdn == 628119112345l) {
            Subscriber subs = new Subscriber(628119112345l, "postpaid", 150000);
            resp = new ResponseEntity<Object>(subs, HttpStatus.OK);
        } else if (msisdn == 628129112345l) {
            Subscriber subs = new Subscriber(628129112345l, "prepaid", 50000);
            resp = new ResponseEntity<Object>(subs, HttpStatus.OK);
        } else {
            resp = new ResponseEntity<Object>("MSISDN not found", HttpStatus.NOT_FOUND);
        }
        return resp;
    }
}