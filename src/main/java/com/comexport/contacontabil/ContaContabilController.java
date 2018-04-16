package com.comexport.contacontabil;

import com.comexport.common._Created;
import com.comexport.exception.InvalidDataException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jean on 4/15/18.
 */
@RestController
public class ContaContabilController {

    private static final Logger LOGGER = Logger.getLogger( ContaContabilController.class );

    @Autowired
    private ContaContabilService service;

    @RequestMapping( value = "/lancamentos-contabeis/", method = RequestMethod.GET )
    public ResponseEntity list( @RequestParam( required = false ) Long contaContabil ) {
        try {
            return ResponseEntity
                .status( HttpStatus.OK )
                .body(
                    contaContabil != null ?
                        this.service.list( contaContabil ) :
                        this.service.list()
                );
        } catch ( InvalidDataException e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( e.getMessages() );
        } catch ( Exception e ) {
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

    @RequestMapping( value = "/lancamentos-contabeis/", method = RequestMethod.POST )
    public ResponseEntity insert( @RequestBody ContaContabil contaContabil ) {
        try {
            return ResponseEntity
                .status( HttpStatus.CREATED )
                .body(
                    new _Created( this.service.insert( contaContabil ) )
                );
        } catch ( InvalidDataException e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( e.getMessages() );
        } catch ( Exception e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

    @RequestMapping( value = "/lancamentos-contabeis/{uuid}", method = RequestMethod.GET )
    public ResponseEntity get( @PathVariable String uuid ) {
        try {
            return ResponseEntity
                .status( HttpStatus.OK )
                .body( this.service.get( uuid ) );
        } catch ( InvalidDataException e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( e.getMessages() );
        } catch ( Exception e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

    @RequestMapping( value = "/lancamentos-contabeis/_stats/", method = RequestMethod.GET )
    public ResponseEntity _stats( @RequestParam( required = false ) Long contaContabil ) {
        try {
            return ResponseEntity
                .status( HttpStatus.OK )
                .body(
                    contaContabil != null ?
                        this.service._stats( contaContabil ) :
                        this.service._stats()
                );
        } catch ( InvalidDataException e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( e.getMessages() );
        } catch ( Exception e ) {
            LOGGER.error( e );
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

}
