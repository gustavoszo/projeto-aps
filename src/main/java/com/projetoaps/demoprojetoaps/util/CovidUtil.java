package com.projetoaps.demoprojetoaps.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.projetoaps.demoprojetoaps.entity.Covid;
import com.projetoaps.demoprojetoaps.entity.Local;
import com.projetoaps.demoprojetoaps.service.CovidService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CovidUtil {

    private final CovidService covidService;
    
    public Page<Covid> getLocalData(Pageable pageable, String local, ModelMap model) {
        for (Local element : Local.values()) {
            if (element.getPathString().equals(local)) {
                model.addAttribute("pagina", element);
                model.addAttribute("qtCasos", covidService.buscarQtCasosPorLocal(element));
                model.addAttribute("qtObitos", covidService.buscarQtObitosPorLocal(element));
                model.addAttribute("taxaLetalidade", getTaxaLetalidade(covidService.buscarQtCasosPorLocal(element), covidService.buscarQtObitosPorLocal(element)));
                return covidService.buscarTodosPorLocal(pageable, element);
            }
        }
        return null;
    }
    
    public Page<Covid> getLocalData(Pageable pageable, String local, ModelMap model, Integer comeco, Integer fim) {
        for (Local element : Local.values()) {
            if (element.getPathString().equals(local)) {
                model.addAttribute("pagina", element);
                model.addAttribute("qtCasos", covidService.buscarQtCasosPorLocalEPeriodo(element, comeco, fim));
                model.addAttribute("qtObitos", covidService.buscarQtObitosPorLocalEPeriodo(element, comeco, fim));
                model.addAttribute("taxaLetalidade", getTaxaLetalidade(covidService.buscarQtCasosPorLocalEPeriodo(element, comeco, fim), covidService.buscarQtObitosPorLocalEPeriodo(element, comeco, fim)));
                return covidService.buscarTodosPorLocalEPeriodo(pageable, element, comeco, fim);
            }
        }
        return null;
    }

    private static String getTaxaLetalidade(Long qtCasos, Long qtObitos) {
        double taxa = (double) qtObitos / qtCasos * 100;
        DecimalFormat df = new DecimalFormat("#.##");
        String valor = df.format(taxa);
        return valor;
    }

    public void setAttributesChart(List<Object[]> dtCasos, List<Object[]> dtObitos, ModelMap model) {
        List<Long> casos = new ArrayList<>();
        List<Long> obitos = new ArrayList<>();
        addValue(dtObitos, obitos);
        addValue(dtCasos, casos);
        List<String> locais = new ArrayList<>();
        addLocais(dtObitos, locais);
        model.addAttribute("casos", casos);
        model.addAttribute("obitos", obitos);
        model.addAttribute("locais", locais);
    }

    private static void addValue(List<Object[]> data, List<Long> values) {
        for (Object[] obj : data) {
            values.add((Long) obj[1]);
        }
    }

    private static void addLocais(List<Object[]> data, List<String> values) {
        for (Object[] obj : data) {
           Local local = (Local) obj[0];
           values.add(local.getDescricao());
        }
    }

}
