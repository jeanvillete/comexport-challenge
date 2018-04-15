package com.comexport.contacontabil;

import com.comexport.exception.InvalidDataException;

import java.util.List;

/**
 * Created by jean on 4/15/18.
 */
public interface ContaContabilService {

    String insert( ContaContabil contaContabil ) throws InvalidDataException;

    ContaContabil get( String uuid ) throws InvalidDataException;

    List< ContaContabil > list();

    List< ContaContabil > list( Long contaContabil ) throws InvalidDataException;

}
