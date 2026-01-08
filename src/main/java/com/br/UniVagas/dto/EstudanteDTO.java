package com.br.UniVagas.dto;

import java.time.LocalDate;

public record EstudanteDTO(String nome, LocalDate dataNascimento, String cpf, String telefone) {}
