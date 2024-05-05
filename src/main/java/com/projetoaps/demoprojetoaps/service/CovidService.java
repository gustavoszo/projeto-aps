package com.projetoaps.demoprojetoaps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.projetoaps.demoprojetoaps.entity.Covid;
import com.projetoaps.demoprojetoaps.entity.Local;
import com.projetoaps.demoprojetoaps.repository.CovidRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CovidService {
    
    private final CovidRepository covidRepository;

    @Transactional(readOnly = true)
    public Page<Covid> buscarTodosPorLocal(Pageable pageable, Local local) {
        return covidRepository.findAllByLocal(pageable, local);
    }
    
    @Transactional(readOnly = true)
    public List<Covid> buscarTodos() {
        return covidRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public Optional<Covid> buscarPorId(Long id) {
        return covidRepository.findById(id);
    }
    
    @Transactional(readOnly = true)
    public Covid buscarPorLocalESemana(Covid covid) {
        return covidRepository.findByLocalAndSemana(covid.getLocal(), covid.getSemana()).orElse(null);
    }

    @Transactional
    public Covid salvar(Covid covid) {
        return covidRepository.save(covid);
    }

    @Transactional
    public void deletar(Covid covid) {
        covidRepository.delete(covid);
    }

    @Transactional(readOnly = true)
    public Long buscarQtCasosPorLocal(Local local) {
        return covidRepository.findTotalQtCasosByLocal(local);
    }

    @Transactional(readOnly = true)
    public Long buscarQtObitosPorLocal(Local local) {
        return covidRepository.findTotalQtObitosByLocal(local);
    }

    @Transactional(readOnly = true)
    public List<Integer> buscarSemanas(Local local) {
        return covidRepository.findAllSemanaByLocal(local);
    }

    @Transactional(readOnly = true)
    public Integer buscarMenorSemana() {
        return covidRepository.findFirstByOrderBySemanaAsc();
    }

    @Transactional(readOnly = true)
    public Integer buscarMaiorSemana() {
        return covidRepository.findFirstByOrderBySemanaDesc();
    }

    @Transactional(readOnly = true)
    public List<Integer> buscarSemanasPorPeriodo(Integer comeco, Integer fim) {
        return covidRepository.findSemanaByPeriodo(comeco, fim);
    }

    @Transactional(readOnly = true)
    public Page<Covid> buscarTodosPorLocalEPeriodo(Pageable pageable, Local local, Integer comeco, Integer fim) {
        return covidRepository.findAllByLocalAndPeriodo(pageable, local, comeco, fim);
    }

    @Transactional(readOnly = true)
    public Long buscarQtCasosPorLocalEPeriodo(Local local, Integer comeco, Integer fim) {
        return covidRepository.findTotalQtCasosByLocalAndPeriodo(local, comeco, fim);
    }

    @Transactional(readOnly = true)
    public Long buscarQtObitosPorLocalEPeriodo(Local local, Integer comeco, Integer fim) {
        return covidRepository.findTotalQtObitosByLocalAndPeriodo(local, comeco, fim);
    }

    @Transactional(readOnly = true)
    public List<Object[]> buscarQuantidadeCasos() {
        return covidRepository.findAllQtCasos();
    }

    @Transactional(readOnly = true)
    public List<Object[]> buscarQuantidadeObitos() {
        return covidRepository.findAllQtObitos();
    }

    @Transactional(readOnly = true) 
    public Long buscarTotalCasos() {
        return covidRepository.getTotalCasos();
    }

    @Transactional(readOnly = true) 
    public Long buscarTotalObitos() {
        return covidRepository.getTotalObitos();
    }

}
