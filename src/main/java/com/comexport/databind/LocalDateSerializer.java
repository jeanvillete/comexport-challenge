package com.comexport.databind;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jean on 4/15/18.
 */
public class LocalDateSerializer extends StdSerializer< LocalDate > {

    public LocalDateSerializer() {
        this( null );
    }

    public LocalDateSerializer( Class< LocalDate > clazz ) {
        super( clazz );
    }

    @Override
    public void serialize( LocalDate value, JsonGenerator gen, SerializerProvider sp ) throws IOException, JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyyMMdd" );
        gen.writeNumber( value.format( formatter ) );
    }

}
