package com.projetoaps.demoprojetoaps.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.projetoaps.demoprojetoaps.entity.Covid;
import com.projetoaps.demoprojetoaps.entity.Local;
import com.projetoaps.demoprojetoaps.entity.Usuario;
import com.projetoaps.demoprojetoaps.entity.Usuario.Role;
import com.projetoaps.demoprojetoaps.security.SecurityLoggedUser;
import com.projetoaps.demoprojetoaps.service.CovidService;
import com.projetoaps.demoprojetoaps.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final SecurityLoggedUser loggedUser;
    private final UsuarioService usuarioService; 
    private final CovidService covidService;
    
    @GetMapping()
    public String home() {
        return "/admin/home";
    }
    
    // Usuarios
    @GetMapping("/usuarios")
    public String listarUsuarios(ModelMap model) {
        model.addAttribute("usuarios", usuarioService.buscarTodos());
        return "/admin/user/listar";
    }
    
    @GetMapping("/usuarios/cadastrar")
    public String adicionarUsuario(Usuario usuario, ModelMap model) {
        model.addAttribute("usuarios", "pagina");
        return "/admin/user/cadastrar";
    }
    
    @PostMapping("/usuarios/cadastrar")
    public String salvarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/admin/user/cadastrar";
        }

        Usuario usuarioUsername = usuarioService.buscarPorUsername(usuario.getUsername());
        if (usuarioUsername != null) {
            attr.addAttribute("error", "E-mail já cadastrado. Tente outro");
            return "redirect:/admin/usuarios/cadastrar";
        }

        usuarioService.salvar(usuario);
        attr.addFlashAttribute("success", "Usuário cadastrado com sucesso");
        return "redirect:/admin/usuarios";
    }

    @GetMapping("/usuarios/editar/{id}")
    public String preEditarUsuario(@PathVariable("id") Long id, ModelMap model) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "/admin/user/cadastrar";
    }

    @PostMapping("/usuarios/editar")
    public String editarUsuario(@Valid Usuario usuario, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/admin/user/cadastrar";
        }

        usuarioService.salvar(usuario);
        attr.addFlashAttribute("success", String.format("Usuário %s alterado com sucesso", usuario.getNome()));
        return "redirect:/admin/usuarios";
    }

    @PostMapping("/usuarios/deletar")
    public String deletarUsuario(@RequestParam("id") Long id, RedirectAttributes attr) {
        Usuario usuario = usuarioService.buscarPorId(id).orElse(null);

        if (usuario == null) return "redirect:/admin/usuarios";

        usuarioService.deletar(usuario);
        attr.addFlashAttribute("success", "Usuário deletado com sucesso");
        return "redirect:/admin/usuarios";
    }
    
    // Covid-data
    @GetMapping("/covid")
    public String listarCovid(ModelMap model) {
        model.addAttribute("covid", covidService.buscarTodos());
        return "/admin/covid/listar";
    }
    
    @GetMapping("/covid/cadastrar")
    public String adcionarSemana(Covid covid) {
        return "/admin/covid/cadastrar";
    }
    
    @PostMapping("/covid/cadastrar")
    public String salvarSemana(@Valid Covid covid, BindingResult result, ModelMap model, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "redirect:/admin/covid/cadastrar";
        }

        Covid jaExiste = covidService.buscarPorLocalESemana(covid);
        if (jaExiste != null) {
            attr.addFlashAttribute("error", String.format("%s já tem a semana %s registrado", covid.getLocal().getDescricao(), covid.getSemana()));
            return "redirect:/admin/covid/cadastrar";
        }

        covidService.salvar(covid);
        attr.addFlashAttribute("success", "Registro adcionado com sucesso");
        return "redirect:/admin/covid";
    }

    @GetMapping("/covid/editar/{id}")
    public String preEditarCovid(@PathVariable("id") Long id, ModelMap model) {
        Covid covid = covidService.buscarPorId(id).orElse(null);
        model.addAttribute("covid", covid);
        return "/admin/covid/cadastrar";
    }

    @PostMapping("/covid/editar")
    public String editarCovid(@Valid Covid covid, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return "/admin/covid/cadastrar";
        }

        covidService.salvar(covid);
        attr.addFlashAttribute("success", "Registro alterado com sucesso");
        return "redirect:/admin/covid";
    }

    @PostMapping("/covid/deletar")
    public String deletarCovid(@RequestParam("id") Long id, RedirectAttributes attr) {
        Covid covid = covidService.buscarPorId(id).orElse(null);

        if (covid == null) return "redirect:/admin/covid";

        covidService.deletar(covid);
        attr.addFlashAttribute("success", "Registro deletado com suceso");
        return "redirect:/admin/covid";
    }
    
    @ModelAttribute("user")
    public Usuario usuarioLogado() {
        return loggedUser.BuscarPorUsuarioLogado();
    }
    
    @ModelAttribute("perfis")
    public Role[] perfisUsuario() {
        return Role.values();
    }
    
    @ModelAttribute("locais")
    public Local[] locaisCovid() {
        return Local.values();
    }

}
