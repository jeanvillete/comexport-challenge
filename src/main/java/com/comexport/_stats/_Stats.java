package com.comexport._stats;

import java.util.List;

/**
 * Created by jean on 4/15/18.
 */
public class _Stats {

    private List< Double > _source;

    public _Stats( List< Double > values ) {
        if ( values == null || values.isEmpty() ) {
            throw new IllegalArgumentException( "The argument 'values' cannot neither be null nor empty." );
        }
        this._source = values;
    }

    public Double sum() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .sum();
    }

    public Double min() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .min()
            .getAsDouble();
    }

    public Double max() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .max()
            .getAsDouble();
    }

    public Double avarage() {
        return this._source
            .stream()
            .mapToDouble( Double::new )
            .average()
            .getAsDouble();
    }

    public Integer count() {
        return this._source.size();
    }

}
