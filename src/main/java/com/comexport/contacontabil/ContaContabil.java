package com.comexport.contacontabil;

import java.time.LocalDate;

/**
 * Created by jean on 4/15/18.
 */
public class ContaContabil {

    private Long contaContabil;
    private LocalDate data;
    private Double valor;

    public ContaContabil() {
    }

    public ContaContabil( Long contaContabil, LocalDate data, Double valor ) {
        this.contaContabil = contaContabil;
        this.data = data;
        this.valor = valor;
    }

    // GETTERS AND SETTERS //
    public Long getContaContabil() {
        return contaContabil;
    }

    public LocalDate getData() {
        return data;
    }

    public Double getValor() {
        return valor;
    }
}
