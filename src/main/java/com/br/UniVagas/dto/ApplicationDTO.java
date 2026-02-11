package com.br.UniVagas.dto;

import java.time.LocalDate;

import com.br.UniVagas.entity.Application;
import com.br.UniVagas.enums.ApplicationStatus;

public record ApplicationDTO(LocalDate dataAplicacao, ApplicationStatus status, Integer candidateId, Integer jobId) {}
