package com.br.UniVagas.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

import com.br.UniVagas.dto.ApplicationDTO;
import com.br.UniVagas.entity.Application;
import com.br.UniVagas.entity.Candidate;
import com.br.UniVagas.entity.Job;
import com.br.UniVagas.enums.ApplicationStatus;
import com.br.UniVagas.exception.IdNotFoundException;
import com.br.UniVagas.mappers.ApplicationMapper;
import com.br.UniVagas.repository.ApplicationRepository;
import com.br.UniVagas.repository.JobRepository;

@Service
public class ApplicationService {
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Autowired
	private JobRepository jobRepository;
	
	@Autowired
	private TokenService tokenService;
	
	public void save(Application application) {
		applicationRepository.save(application);
	}
	
	public void create(ApplicationDTO applicationDTO, JwtAuthenticationToken token) {
		Application application = new Application();
		
		Job job = jobRepository.findById(applicationDTO.jobId()).orElseThrow(() -> new IdNotFoundException());
		
		Candidate candidate = tokenService.findCandidateByToken(token);
		
		application.setJob(job);
		application.setCandidate(candidate);
		
		application.setDataAplicacao(LocalDate.now());
		application.setStatus(ApplicationStatus.ENVIADA);
		
		save(application);
	}
	
	public void update(Integer id, ApplicationDTO applicationDTO, JwtAuthenticationToken token) {
		Application application = findById(id);
		
		tokenService.verifyCompanyByToken(application.getJob().getCompany(), token);
		
		application = ApplicationMapper.update(application,applicationDTO);
		
		save(application);
	}
	
	private Application findById(Integer id) {
		Application application = applicationRepository.findById(id).orElseThrow(() -> new IdNotFoundException());
		
		return application;
	}
	
	public void delete(Integer id, JwtAuthenticationToken token) {
		Application application = findById(id);
		
		tokenService.verifyCompanyByToken(application.getJob().getCompany(), token);
		
		applicationRepository.delete(application);
	}
	
	public List<Application> findAll() {
		List<Application> applications = applicationRepository.findAll();
		
		return applications;
	}
	
	public List<Application> findByJobId(Integer jobId, JwtAuthenticationToken token) {
		Job job = jobRepository.findById(jobId).orElseThrow(() -> new IdNotFoundException());
		
		tokenService.verifyCompanyByToken(job.getCompany(), token);
		
		List<Application> applications = new ArrayList<>();
		
		applications = applicationRepository.findAllByJobId(jobId);
		
		return applications;
	}
	
	

}
