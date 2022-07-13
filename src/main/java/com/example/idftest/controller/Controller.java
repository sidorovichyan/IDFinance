package com.example.idftest.controller;

import com.example.idftest.config.CryptoHelper;
import com.example.idftest.entity.EntityConfig;
import com.example.idftest.entity.CoinApi;
import com.example.idftest.service.CryptoService;
import com.example.idftest.service.logic.RegUserNotify;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CryptoService cryptoService;

    @GetMapping("/")
    List<EntityConfig> doGet() {
        CryptoHelper cryptoHelper = CryptoHelper.getInstance();
        return cryptoHelper.getEntityConfigList();
    }


    @GetMapping("/crypto/id/{idCrypto}")
    CoinApi doGetCoinInfoById(@PathVariable String idCrypto) {
        return cryptoService.findCoinByIdApi(Integer.parseInt(idCrypto));
    }

    @GetMapping("/crypto/symbol/{symbol}")
    CoinApi doGetCoinInfoBySymbol(@PathVariable String symbol) {
        return cryptoService.getBySymbol(symbol);
    }

    @GetMapping("/crypto/user/{username}/{symbol}")
    void doRegUser(@PathVariable String username, @PathVariable String symbol) {
        RegUserNotify regUserNotify = new RegUserNotify(cryptoService, username, symbol);
        new Thread(regUserNotify).start();
    }


}
