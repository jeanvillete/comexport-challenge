package com.comexport.contacontabil;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by jean on 4/15/18.
 */
@Repository
public class ContaContabilRepository implements InitializingBean {

    @Autowired
    @Qualifier( "dataSource" )
    private ConcurrentHashMap< String, ContaContabil > dataSource;

    @Override
    public void afterPropertiesSet() throws Exception {
        if ( this.dataSource == null ) {
            throw new IllegalStateException( "The property 'dataSource' has not been properly initialized." );
        }
    }

    public String insert( ContaContabil contaContabil ) {
        String uuid = UUID.randomUUID().toString();
        this.dataSource.put( uuid, contaContabil );
        return uuid;
    }

    public ContaContabil get( String uuid ) {
        return this.dataSource.get( uuid );
    }

    public List< ContaContabil > list() {
        return new ArrayList<>( this.dataSource.values() );
    }
}
