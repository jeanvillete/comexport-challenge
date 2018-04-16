package com.comexport.contacontabil;

import com.comexport.common._Created;
import com.comexport.exception.InvalidDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jean on 4/15/18.
 */
@RestController( "/lancamentos-contabeis" )
public class ContaContabilController {

    @Autowired
    private ContaContabilService service;

    @RequestMapping( method = RequestMethod.GET )
    public ResponseEntity list() {
        try {
            return ResponseEntity
                .status( HttpStatus.OK )
                .body( this.service.list() );
        } catch ( Exception e ) {
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

    @RequestMapping( method = RequestMethod.POST )
    public ResponseEntity insert( @RequestBody ContaContabil contaContabil ) {
        try {
            return ResponseEntity
                .status( HttpStatus.CREATED )
                .body(
                    new _Created(
                        this.service.insert( contaContabil )
                    )
                );
        } catch ( InvalidDataException e ) {
            return ResponseEntity
                .status( HttpStatus.BAD_REQUEST )
                .body( e.getMessages() );
        } catch ( Exception e ) {
            return ResponseEntity
                .status( HttpStatus.INTERNAL_SERVER_ERROR )
                .body( "A serious 'server error' happened. Please, contact the system administrator!" );
        }
    }

}
