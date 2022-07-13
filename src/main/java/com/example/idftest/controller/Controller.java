package com.example.idftest.controller;

import com.example.idftest.config.CryptoHelper;
import com.example.idftest.entity.EntityConfig;
import com.example.idftest.entity.CoinApi;
import com.example.idftest.service.CryptoService;
import com.example.idftest.service.logic.RegUserNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/crypto")
    List<EntityConfig> doGet() {
        CryptoHelper cryptoHelper = CryptoHelper.getInstance();
        return cryptoHelper.getEntityConfigList();
    }

    @GetMapping("/")
    ResponseEntity<List<EntityConfig>> doGetRest()
    {
        CryptoHelper cryptoHelper = CryptoHelper.getInstance();
        List<EntityConfig> list = cryptoHelper.getEntityConfigList();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/crypto/id/{idCrypto}")
    ResponseEntity<CoinApi> doGetCoinInfoById(@PathVariable String idCrypto) {
        return new ResponseEntity<CoinApi>(cryptoService.findCoinByIdApi(Integer.parseInt(idCrypto)),HttpStatus.OK) ;
    }

    @GetMapping("/crypto/symbol/{symbol}")
    ResponseEntity<CoinApi> doGetCoinInfoBySymbol(@PathVariable String symbol) {
        return new ResponseEntity<CoinApi>(cryptoService.getBySymbol(symbol),HttpStatus.OK);
    }

    @GetMapping("/crypto/user/{username}/{symbol}")
    ResponseEntity<?> doRegUser(@PathVariable String username, @PathVariable String symbol) {
        RegUserNotify regUserNotify = new RegUserNotify(cryptoService, username, symbol);
        new Thread(regUserNotify).start();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
