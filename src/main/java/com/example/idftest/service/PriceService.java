package com.example.idftest.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "price",url = "https://api.coinlore.net/api/ticker")
public interface PriceService {

    @GetMapping("/?id={id}")
    JsonNode getPrice(@PathVariable String id);
}
