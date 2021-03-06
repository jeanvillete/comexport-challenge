package com.comexport.contacontabil;

import com.comexport.common._Stats;
import com.comexport.exception.InvalidDataException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created by jean on 4/15/18.
 */
@Service
public class ContaContabilServiceImpl implements ContaContabilService, InitializingBean {

    @Autowired
    private ContaContabilRepository repository;

    @Override
    public void afterPropertiesSet() throws Exception {
        if ( this.repository == null ) {
            throw new IllegalStateException( "Property 'repository' has not been properly initialized." );
        }
    }

    @Override
    public String insert( ContaContabil contaContabil ) throws InvalidDataException {
        if ( contaContabil == null ) {
            throw new InvalidDataException( "The argument 'contaContabil' cannot be null." );
        }

        InvalidDataException checkInvalid = new InvalidDataException();
        if ( contaContabil.getContaContabil() == null || contaContabil.getContaContabil() < 0 ) {
            checkInvalid.addMessage( "The parameter 'contaContabil' must be equals or greater than 0." );
        }
        if ( contaContabil.getData() == null ) {
            checkInvalid.addMessage( "The parameter 'data' cannot be null." );
        }
        if ( contaContabil.getValor() == null ) {
            checkInvalid.addMessage( "The parameter 'valor' cannot be null." );
        }
        checkInvalid.throwIfExists();

        return this.repository.insert( contaContabil );
    }

    @Override
    public ContaContabil get( String uuid ) throws InvalidDataException {
        if ( StringUtils.isEmpty( uuid ) ) {
            throw new InvalidDataException( "The argument 'uuid' cannot neither be null nor empty." );
        }

        try {
            UUID.fromString( uuid );
        } catch ( IllegalArgumentException e ) {
            throw new InvalidDataException( "The argument 'uuid' doesn't represent a valid UUID; provided uuid value; " + uuid );
        }

        return this.repository.get( uuid );
    }

    @Override
    public List< ContaContabil > list() {
        return this.repository.list();
    }

    @Override
    public List< ContaContabil > list( Long contaContabil ) throws InvalidDataException {
        if ( contaContabil == null || contaContabil < 0 ) {
            throw new InvalidDataException( "The argument 'contaContabil' cannot be null and must be equals or greater than 0." );
        }

        return this.repository.list()
            .stream()
            .filter( _contaContabil -> _contaContabil.getContaContabil().equals( contaContabil ) )
            .collect( Collectors.toList() );
    }

    @Override
    public _Stats _stats() {
        return new _Stats(
            this.repository.list()
                .stream()
                .map( ContaContabil::getValor )
                .collect( Collectors.toList() )
        );
    }

    @Override
    public _Stats _stats( Long contaContabil ) throws InvalidDataException {
        if ( contaContabil == null || contaContabil < 0 ) {
            throw new InvalidDataException( "The argument 'contaContabil' cannot be null and must be equals or greater than 0." );
        }

        return new _Stats(
            this.repository.list()
                .stream()
                .filter( _contaContabil -> _contaContabil.getContaContabil().equals( contaContabil ) )
                .map( ContaContabil::getValor )
                .collect( Collectors.toList() )
        );
    }

}
