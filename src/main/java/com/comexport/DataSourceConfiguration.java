package com.comexport;

import com.comexport.contacontabil.ContaContabil;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jean on 4/15/18.
 */
@Configuration
public class DataSourceConfiguration {

    @Bean( name = "dataSource" )
    @Scope( value = ConfigurableBeanFactory.SCOPE_SINGLETON )
    public ConcurrentHashMap< String, ContaContabil > dataSource() {
        return new ConcurrentHashMap();
    }

}
