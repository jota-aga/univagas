package com.br.UniVagas.dto;

import java.time.LocalDate;

public record CandidateDTO(String email, String senha, String nome, LocalDate dataNascimento, String cpf, String telefone, String descricao, Integer usuarioId) {}
