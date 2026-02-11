package com.br.UniVagas.mappers;

import com.br.UniVagas.dto.JobDTO;
import com.br.UniVagas.entity.Job;

public class JobMapper {
	public static Job toEntity(JobDTO dto) {
		Job job = new Job();
		
		job.setBolsaSalario(dto.bolsaSalario());
		job.setCargaHoraria(dto.cargaHoraria());
		job.setDataLimite(dto.dataLimite());
		job.setDataPublicacao(dto.dataPublicacao());
		job.setDescricao(dto.descricao());
		job.setLocalizacao(dto.localizacao());
		job.setStatus(dto.status());
		job.setTipoDeJob(dto.tipoDeJob());
		job.setTitulo(dto.titulo());
		
		return job;
	}
	
	public static Job update(Job job, JobDTO dto) {	
		
		job.setBolsaSalario(dto.bolsaSalario());
		job.setCargaHoraria(dto.cargaHoraria());
		job.setDataLimite(dto.dataLimite());
		job.setDataPublicacao(dto.dataPublicacao());
		job.setDescricao(dto.descricao());
		job.setLocalizacao(dto.localizacao());
		job.setStatus(dto.status());
		job.setTipoDeJob(dto.tipoDeJob());
		job.setTitulo(dto.titulo());
		
		return job;
	}
}
