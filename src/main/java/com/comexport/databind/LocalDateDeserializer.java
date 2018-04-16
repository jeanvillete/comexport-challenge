package com.comexport.databind;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by jean on 4/15/18.
 */
public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    public LocalDateDeserializer() {
        this( null );
    }

    public LocalDateDeserializer( Class< LocalDate > clazz ) {
        super( clazz );
    }

    @Override
    public LocalDate deserialize( JsonParser jsonParser, DeserializationContext deserializationContext ) throws IOException, JsonProcessingException {
        Integer _intValue = jsonParser.getIntValue();
        if ( _intValue == null ) {
            return null;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyyMMdd" );
        return LocalDate.parse( _intValue.toString(), formatter );
    }
}
