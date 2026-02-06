package com.reuse.reuse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reuse.reuse.Entity.Cliente;
import com.reuse.reuse.Service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    //Listar clientes
    @GetMapping({"/listarClientes", ""})
    public String listarClientes(Model model) {
        model.addAttribute("clientes", clienteService.listar());
        return "clientes/listarClientes";
    }

    // Mostrar formulario para nuevo cliente
    @GetMapping("/formularioClientes")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "clientes/formularioClientes";
    }

    // Agregar nuevo cliente
    @PostMapping("/formularioCliente")
    public String aggCliente(
        @RequestParam String nombre,
        @RequestParam String documento,
        @RequestParam String telefono,
        @RequestParam(defaultValue = "1") boolean activo
    ) {

        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDocumento(documento);
        cliente.setTelefono(telefono);
        cliente.setActivo(activo);

        clienteService.guardar(cliente);

        return "redirect:/clientes/listarClientes";
    }

    // Editar cliente
    @GetMapping("/editarCliente/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Cliente cliente = clienteService.buscarPorId(id);
        if (cliente == null) {
            redirect.addFlashAttribute("error", "❌ Cliente no encontrado");
            return "redirect:/clientes/listarClientes";
        }
        model.addAttribute("cliente", cliente);
        return "clientes/formularioClientes";
    }

}
