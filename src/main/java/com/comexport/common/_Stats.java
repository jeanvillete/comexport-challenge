package com.comexport.common;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

/**
 * Created by jean on 4/15/18.
 */
@JsonPropertyOrder( { "soma", "min", "max", "media", "qtde" } )
public class _Stats {

    @JsonIgnore
    private List< Double > _source;

    public _Stats( List< Double > values ) {
        if ( values == null ) {
            throw new IllegalArgumentException( "The argument 'values' cannot neither be null nor empty." );
        }
        this._source = values;
    }

    @JsonGetter( "soma" )
    public Double sum() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .sum();
    }

    @JsonGetter( "min" )
    public Double min() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .min()
            .orElse( 0 );
    }

    @JsonGetter( "max" )
    public Double max() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .max()
            .orElse( 0 );
    }

    @JsonGetter( "media" )
    public Double avarage() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .average()
            .orElse( 0 );
    }

    @JsonGetter( "qtde" )
    public Integer count() {
        return this._source.size();
    }

}
