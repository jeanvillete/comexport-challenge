package com.comexport.contacontabil;

import com.comexport.databind.LocalDateDeserializer;
import com.comexport.databind.LocalDateSerializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

/**
 * Created by jean on 4/15/18.
 */
public class ContaContabil {

    @JsonProperty
    private Long contaContabil;

    @JsonProperty
    @JsonDeserialize( using = LocalDateDeserializer.class )
    @JsonSerialize( using = LocalDateSerializer.class )
    private LocalDate data;

    @JsonProperty
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
