package com.example.idftest.config;

import com.example.idftest.entity.EntityConfig;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CryptoHelper {

    private static final String PROPERTY_ID = "crypto.id";
    private static final String PROPERTY_SYMBOL = "crypto.symbol";

    private static final Properties prop = new Properties();

    private static CryptoHelper instance;

    private static List<EntityConfig> entityConfigList;

    private CryptoHelper() {
    }

    public static CryptoHelper getInstance() {
        if(instance == null)
        {
            instance = new CryptoHelper();
            init();
        }
        return instance;
    }

    private static void init()
    {
        InputStream inputProp = Thread.currentThread().getContextClassLoader().getResourceAsStream("crypto.properties");
        entityConfigList = new ArrayList<>();
        EntityConfig entityConfig;
        try {
            prop.load(inputProp);
            for(int i=1;i<4;i++)
            {
                entityConfig = new EntityConfig();
                entityConfig.setId(Integer.parseInt(prop.getProperty(PROPERTY_ID+i)));
                entityConfig.setSymbol(prop.getProperty(PROPERTY_SYMBOL+i));
                entityConfigList.add(entityConfig);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<EntityConfig> getEntityConfigList() {
        return entityConfigList;
    }
}
