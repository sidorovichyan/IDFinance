package com.example.idftest.service.logic;

import com.example.idftest.service.CryptoService;
import org.apache.log4j.Logger;

public class RegUserNotify implements Runnable {

    private static final int TIME_SLEEP = 6000;

    private static final int PERCENT_COMPARE = 1;                   // for testing use ~ 0.02. wait for a 1 percent change for a long time

    private final static Logger logger = Logger.getLogger(RegUserNotify.class);

    private CryptoService cryptoService;

    private String username;

    private String symbol;

    public RegUserNotify(CryptoService cryptoService, String username, String symbol) {
        this.cryptoService = cryptoService;
        this.username = username;
        this.symbol = symbol;
    }

    @Override
    public void run() {
        Double regPrice = cryptoService.getBySymbol(symbol).getPrice();
        double change = 0;
        synchronized (this) {
            while (true) {
                change = ((Math.abs((cryptoService.getBySymbol(symbol).getPrice() - regPrice)) / regPrice) * 100);
                if (change < PERCENT_COMPARE) {
                    try {
                        Thread.sleep(TIME_SLEEP);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    break;
                }
            }
            this.notify();
        }
        logger.warn("symbol: " + symbol + ", user: " + username + " %" + change);
    }
}
