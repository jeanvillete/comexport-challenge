package com.comexport.contacontabil;

import com.comexport.exception.InvalidDataException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

/**
 * Created by jean on 4/15/18.
 */
@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
public class ContaContabilServiceTest {

    @Autowired
    private ContaContabilService service;

    @Before
    public void init() {
        Assert.notNull( this.service, "The property 'service' has not been properly initialized." );
    }

    /**
     * Cenário 1: Dados obrigatórios não fornecidos na inserção
     * Cenário 2: Dados fornecidos na inserção não são válidos
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_recordForInsertion_one() throws InvalidDataException {
        this.service.insert( null );
    }

    /**
     * Cenário 1: Dados obrigatórios não fornecidos na inserção
     * Cenário 2: Dados fornecidos na inserção não são válidos
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_recordForInsertion_two() throws InvalidDataException {
        this.service.insert( new ContaContabil() );
    }

    /**
     * Cenário 3: Inserção com sucesso
     * Cenário 6: Recuperar com sucesso, um registro ContaContabil baseado num UUID
     */
    @Test
    public void success_insert() throws InvalidDataException {
        Long _contaContabil = 1111001L;
        LocalDate _data = LocalDate.parse( "20170130", DateTimeFormatter.ofPattern( "yyyyMMdd" ) );
        Double _valor = 25000.15;

        String uuid = this.service.insert(
            new ContaContabil(
                _contaContabil,
                _data,
                _valor
            )
        );

        Assert.notNull( uuid, "It was expected a valid UUID string value, but we got null at this point." );
        Assert.notNull( UUID.fromString( uuid ), "A wrong string value was provided to the 'fromString' method; " + uuid );

        ContaContabil contaContabil = this.service.get( uuid );

        Assert.notNull( contaContabil, "It was expected a valid 'ContaContabil' instance at this point." );
        Assert.state( contaContabil.getContaContabil().equals( _contaContabil ), "The values for parameter 'contaContabil' should be equals." );
        Assert.state( contaContabil.getData().equals( _data ), "The values for parameter 'data' should be equals." );
        Assert.state( contaContabil.getValor().equals( _valor ), "The values for parameter 'data' should be equals." );
    }

    /**
     * Cenário 4: Recuperação com falha, de um registro ContaContabil com UUID inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_getRecordByUUID_one() throws InvalidDataException {
        this.service.get( null );
    }

    /**
     * Cenário 4: Recuperação com falha, de um registro ContaContabil com UUID inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_getRecordByUUID_two() throws InvalidDataException {
        this.service.get( "" );
    }

    /**
     * Cenário 4: Recuperação com falha, de um registro ContaContabil com UUID inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_getRecordByUUID_three() throws InvalidDataException {
        this.service.get( "notAValidUUID" );
    }

    /**
     * Cenário 5: Recuperação com falha, de um registro ContaContabil com UUID válido porém não existe um registro ContaContabil correspondente
     */
    @Test
    public void valid_getRecordByUUID_getNull() throws InvalidDataException {
        String uuid = "cb803640-41c2-453c-99ea-e74d7c84ecf3";
        Assert.isNull(
            this.service.get( "cb803640-41c2-453c-99ea-e74d7c84ecf3" ),
            "This is a fake UUID and shouldn't bring any valid reference from repository."
        );
    }

    /**
     * Cenário 7: Listagem de registros ContaContabil
     */
    @Test
    public void valid_listRecords() throws InvalidDataException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyyMMdd" );
        Long _contaContabil = 1111001L;

        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170130", dtf ), 25000.15 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170131", dtf ), -250.0 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170131", dtf ), 25.01 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170201", dtf ), -35.15 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170201", dtf ), -27.95 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170201", dtf ), 45.65 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), 983.65 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), 91.75 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), -11.77 ) );

        List< ContaContabil > _list = this.service.list();

        Assert.notNull( _list, "It was expected a valid List instance on the variable '_list' at this point." );
        Assert.state( !_list.isEmpty(), "The List instance on variable '_list' shouldn't be empty at this point." );
    }

    /**
     * Cenário 8: Listagem com falha, de registros ContaContabil filtrados pelo parâmetro "contaContabil" inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_listRecordsWithFilter_one() throws InvalidDataException {
        this.service.list( null );
    }

    /**
     * Cenário 8: Listagem com falha, de registros ContaContabil filtrados pelo parâmetro "contaContabil" inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_listRecordsWithFilter_two() throws InvalidDataException {
        this.service.list( -1L );
    }

    /**
     * Cenário 9: Listagem com sucesso, de registros ContaContabil filtrados pelo parâmetro "contaContabil"
     */
    @Test
    public void valid_listRecordsWithFilter() throws InvalidDataException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyyMMdd" );
        Long _contaContabil = 1111002L;

        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170201", dtf ), 45.65 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), 983.65 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), 91.75 ) );
        this.service.insert( new ContaContabil( _contaContabil, LocalDate.parse( "20170202", dtf ), -11.77 ) );

        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170201", dtf ), 45.65 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", dtf ), 983.65 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", dtf ), 91.75 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", dtf ), -11.77 ) );

        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170130", dtf ), 25000.15 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170131", dtf ), -250.0 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170131", dtf ), 25.01 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170201", dtf ), -35.15 ) );

        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170201", dtf ), -27.95 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170201", dtf ), 45.65 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", dtf ), 983.65 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", dtf ), 91.75 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", dtf ), -11.77 ) );

        List< ContaContabil > _list = this.service.list( _contaContabil );

        Assert.notNull( _list, "It was expected a valid List instance on the variable '_list' at this point." );
        Assert.state( !_list.isEmpty(), "The List instance on variable '_list' shouldn't be empty at this point." );
    }
}
