package com.example.idftest.service;

import com.example.idftest.dao.CryptoCrud;

import com.example.idftest.entity.CoinApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CryptoService {

    @Autowired
    private CryptoCrud cryptoCrud;

    public CoinApi findCoinByIdApi(int idApi){
        return cryptoCrud.findCoinByIdApi(idApi);
    }

    public void save(CoinApi coinApi)
    {
        cryptoCrud.save(coinApi);
    }

    public CoinApi getByIdApi(int idApi)
    {
        return cryptoCrud.findCoinByIdApi(idApi);
    }

    public CoinApi getBySymbol(String symbol)
    {
        return cryptoCrud.findCoinApiBySymbol(symbol);
    }


}
