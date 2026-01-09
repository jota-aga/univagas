package com.br.UniVagas.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.br.UniVagas.enums.StatusDaVaga;
import com.br.UniVagas.enums.TipoDeVaga;

public record VagaDTO(String titulo, String descricao, TipoDeVaga tipoDeVaga, LocalDate dataPublicacao, LocalDate dataLimite,
BigDecimal bolsaSalario, int cargaHoraria, String localizacao, StatusDaVaga status, Integer empresaId) {}
