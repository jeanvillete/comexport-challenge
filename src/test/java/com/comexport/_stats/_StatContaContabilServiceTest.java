package com.comexport._stats;

import com.comexport.contacontabil.ContaContabil;
import com.comexport.contacontabil.ContaContabilService;
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

/**
 * Created by jean on 4/15/18.
 */
@SpringBootTest
@RunWith( SpringJUnit4ClassRunner.class )
public class _StatContaContabilServiceTest {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern( "yyyyMMdd" );

    @Autowired
    private ContaContabilService service;

    @Before
    public void init() throws InvalidDataException {
        Assert.notNull( this.service, "The property 'service' has not been properly initialized." );

        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170130", FORMATTER), 25000.15 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170131", FORMATTER), -250.0 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170131", FORMATTER), 25.01 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170201", FORMATTER), -35.15 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170201", FORMATTER), -27.95 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170201", FORMATTER), 45.65 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170202", FORMATTER), 983.65 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170202", FORMATTER), 91.75 ) );
        this.service.insert( new ContaContabil( 1111001L, LocalDate.parse( "20170202", FORMATTER), -11.77 ) );

        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170201", FORMATTER), 45.65 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", FORMATTER), 983.65 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", FORMATTER), 91.75 ) );
        this.service.insert( new ContaContabil( 2111001L, LocalDate.parse( "20170202", FORMATTER), -11.77 ) );

        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170130", FORMATTER), 25000.15 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170131", FORMATTER), -250.0 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170131", FORMATTER), 25.01 ) );
        this.service.insert( new ContaContabil( 3111001L, LocalDate.parse( "20170201", FORMATTER), -35.15 ) );

        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170201", FORMATTER), -27.95 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170201", FORMATTER), 45.65 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", FORMATTER), 983.65 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", FORMATTER), 91.75 ) );
        this.service.insert( new ContaContabil( 4111001L, LocalDate.parse( "20170202", FORMATTER), -11.77 ) );
    }

    /**
     * Cenário 10: Aplicação de operações estatísticas sobre os lançamentos de ContaContabil atualmente armazenados
     */
    @Test
    public void valid_applyStat() {
        _Stats stats = this.service._stats();

        System.out.println( "soma; " + stats.sum() );
        System.out.println( "min; " + stats.min() );
        System.out.println( "max; " + stats.max() );
        System.out.println( "media; " + stats.avarage() );
        System.out.println( "qtde; " + stats.count() );
    }

    /**
     * Cenário 11: Aplicação com falha, de operações estatísticas sobre os lançamentos de ContaContabil, baseado no parâmetro "contaContabil" inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_applyStatWithFilter_one() throws InvalidDataException {
        this.service._stats( null );
    }

    /**
     * Cenário 11: Aplicação com falha, de operações estatísticas sobre os lançamentos de ContaContabil, baseado no parâmetro "contaContabil" inválido
     */
    @Test( expected = InvalidDataException.class )
    public void invalid_applyStatWithFilter_two() throws InvalidDataException {
        this.service._stats( -1L );
    }


    /**
     * Cenário 12: Aplicação com sucesso, de operações estatísticas sobre os lançamentos de ContaContabil atualmente armazenados, filtrado pelo parâmetro "contaContabil"
     */
    @Test
    public void valid_applyStatWithFilter() throws InvalidDataException {
        Long _contaContabil = 1111002L;

        this.service.insert( new ContaContabil( 1111002L, LocalDate.parse( "20170201", FORMATTER), 45.65 ) );
        this.service.insert( new ContaContabil( 1111002L, LocalDate.parse( "20170202", FORMATTER), 983.65 ) );
        this.service.insert( new ContaContabil( 1111002L, LocalDate.parse( "20170202", FORMATTER), 91.75 ) );
        this.service.insert( new ContaContabil( 1111002L, LocalDate.parse( "20170202", FORMATTER), -11.77 ) );

        _Stats stats = this.service._stats( _contaContabil );

        System.out.println( "soma; " + stats.sum() );
        System.out.println( "min; " + stats.min() );
        System.out.println( "max; " + stats.max() );
        System.out.println( "media; " + stats.avarage() );
        System.out.println( "qtde; " + stats.count() );
    }

}
