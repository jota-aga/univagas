package com.br.UniVagas.dto;

import com.br.UniVagas.entity.Role;

public record RegisterDTO(String email, String senha, String confirmarSenha, Role.Value role ) {}
