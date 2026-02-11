package com.br.UniVagas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.JobDTO;
import com.br.UniVagas.entity.Company;
import com.br.UniVagas.entity.Job;
import com.br.UniVagas.mappers.JobMapper;
import com.br.UniVagas.repository.JobRepository;

@Service
public class JobService {
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public void save(Job job) {		
		jobRepository.save(job);
	}
	
	public void create(JobDTO jobDTO, JwtAuthenticationToken token) {
		Job job = JobMapper.toEntity(jobDTO);
		
		Company company = tokenService.findCompanyByToken(token);
		
		job.setCompany(company);
		
		save(job);
	}
	
	public void delete(Integer id , JwtAuthenticationToken token) {
		Job job = findById(id);
		
		tokenService.verifyCompanyByToken(job.getCompany(), token);
		
		jobRepository.delete(job);
	}
	
	public List<Job> findAll(){
		return jobRepository.findAll();
	}

	public void update(JobDTO jobDTO, Integer id, JwtAuthenticationToken token) {
		Job job = findById(id);
		
		tokenService.verifyCompanyByToken(job.getCompany(), token);
		
		job = JobMapper.update(job, jobDTO);
		
		jobRepository.save(job);
	}
	
	public List<Job> findByTermoDePesquisa(String termoDePesquisa) {
		List<Job> jobs = new ArrayList<>();
		
		jobs = jobRepository.findAllByTituloOrDescricao(termoDePesquisa);
		
		return jobs;
	}
	
	public List<Job> findAllByCompany(JwtAuthenticationToken token) {
		Company company = tokenService.findCompanyByToken(token);
		
		List<Job> jobs = jobRepository.findAllByCompanyId(company.getId());
		
		return jobs;
	}
	
	private Job findById(Integer id){
		Optional<Job> optionalJob = jobRepository.findById(id);
		
		Job job = optionalJob.orElseThrow();
		
		return job;
	}

	
}
