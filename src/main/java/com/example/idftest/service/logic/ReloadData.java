package com.example.idftest.service.logic;

import com.example.idftest.config.CryptoHelper;
import com.example.idftest.entity.EntityConfig;
import com.example.idftest.entity.CoinApi;
import com.example.idftest.service.CryptoService;
import com.example.idftest.service.PriceService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class ReloadData {

    private static final String ID_API_STRING = "id";
    private static final String SYMBOL_API_STRING = "symbol";
    private static final String PRICE_API_STRING = "price_usd";

    private PriceService priceService;

    private CryptoService cryptoService;

    private CoinApi coinApi;

    private CryptoHelper cryptoHelper;

    private JsonNode jsonNode;

    private ReloadData() {
        cryptoHelper = CryptoHelper.getInstance();
    }

    @Autowired
    public void setPriceService(PriceService priceService) {
        this.priceService = priceService;
    }

    @Autowired
    public void setCryptoService(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    @Scheduled(fixedRate = 60000)
    void reloadData() {
        for (EntityConfig entityConfig : cryptoHelper.getEntityConfigList()) {
            jsonNode = priceService.getPrice("" + entityConfig.getId());
            reloadDataBase();
        }

    }

    private void reloadDataBase() {
        int idApi = Integer.parseInt(jsonNode.get(0).get(ID_API_STRING).asText());
        coinApi = cryptoService.getByIdApi(idApi);
        if (coinApi == null) {
            coinApi = new CoinApi();
            coinApi.setId(0L);
            coinApi.setIdApi(Integer.parseInt(jsonNode.get(0).get(ID_API_STRING).asText()));
            coinApi.setSymbol(jsonNode.get(0).get(SYMBOL_API_STRING).asText());
        }
        coinApi.setPrice(Double.parseDouble(jsonNode.get(0).get(PRICE_API_STRING).asText()));
        cryptoService.save(coinApi);
    }


}
