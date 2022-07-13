package com.example.idftest.dao;


import com.example.idftest.entity.CoinApi;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoCrud extends CrudRepository<CoinApi,Long> {

    CoinApi findCoinByIdApi(int idApi);

    CoinApi findCoinApiBySymbol(String symbol);

}
