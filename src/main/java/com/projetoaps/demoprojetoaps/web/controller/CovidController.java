package com.projetoaps.demoprojetoaps.web.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projetoaps.demoprojetoaps.entity.Covid;
import com.projetoaps.demoprojetoaps.entity.Usuario;
import com.projetoaps.demoprojetoaps.security.SecurityLoggedUser;
import com.projetoaps.demoprojetoaps.service.CovidService;
import com.projetoaps.demoprojetoaps.util.CovidUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
@RequiredArgsConstructor
@RequestMapping("/covid")
public class CovidController {
    
    private final CovidUtil covidUtil;
    private final SecurityLoggedUser loggedUser;
    private final CovidService covidService;
    
    @GetMapping("/dashboard")
    public String dashboard(ModelMap model) {
        covidUtil.setAttributesChart(covidService.buscarQuantidadeCasos(), covidService.buscarQuantidadeObitos(), model);
        return "content/dashboard";
    }
    
    @GetMapping("/{local}")
    public String localData(@PathVariable("local") String local, @PageableDefault(size = 5) Pageable pageable, ModelMap model) {
        Page<Covid> data = covidUtil.getLocalData(pageable, local, model);
        if (data != null) model.addAttribute("paginacao", data);
        model.addAttribute("semanas", covidService.buscarSemanas());
        model.addAttribute("menorSemana", covidService.buscarMenorSemana());
        model.addAttribute("maiorSemana", covidService.buscarMaiorSemana());
        return "content/data";
    }
    
    @GetMapping("/{local}/periodo")
    public String localData(@PathVariable("local") String local, @RequestParam("comeco") Integer comeco, @RequestParam("fim") Integer fim,  @PageableDefault(size = 5) Pageable pageable, ModelMap model) {
        Page<Covid> data = covidUtil.getLocalData(pageable, local, model, comeco, fim);
        if (data != null) model.addAttribute("paginacao", data);
        model.addAttribute("semanas", covidService.buscarSemanas());
        model.addAttribute("menorSemana", comeco);
        model.addAttribute("maiorSemana", fim);
        return "content/data";
    }

    @ModelAttribute("user")
    public Usuario usuarioLogado() {
        return loggedUser.BuscarPorUsuarioLogado();
    }
    
}
