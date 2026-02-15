package com.reuse.reuse.Controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reuse.reuse.Entity.Pedido;
import com.reuse.reuse.Service.DetallePedidoService;
import com.reuse.reuse.Service.PedidoService;
import com.reuse.reuse.Service.ProductoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;
    private final DetallePedidoService detallePedidoService;
    private final ProductoService productoService;
    
    public PedidoController(PedidoService pedidoService, DetallePedidoService detallePedidoService, ProductoService productoService) {
        this.pedidoService = pedidoService;
        this.detallePedidoService = detallePedidoService;
        this.productoService = productoService;
    }

    // Listar pedidos
    @GetMapping({"/listarPedidos", ""}) 
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoService.listar());
        return "pedidos/listarPedidos";
    }
    
    // Mostrar formulario para nuevo pedido
    @GetMapping("/formularioPedidos")
    public String mostrarFormulario(Model model) {
        model.addAttribute("pedido", new Pedido());
        return "pedidos/formularioPedidos";
    }

    // Agregar nuevo pedido
    @PostMapping("/formularioPedido")
    public String aggPedido(
        @RequestParam LocalDateTime fecha,
        @RequestParam Double total,
        @RequestParam(defaultValue = "1") boolean activo,
        @RequestParam Long clienteId
    ) {

        Pedido pedido = new Pedido();
        pedido.setFecha(LocalDateTime.now());
        pedido.setTotal(total);
        pedido.setActivo(activo);
        pedido.setClienteId(clienteId);
        pedidoService.guardar(pedido);

        return "redirect:/pedidos/listarPedidos";
    }

    // Editar pedido
    @GetMapping("/editarPedido/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Pedido pedido = pedidoService.buscarPorId(id);
        if (pedido == null) {
            redirect.addFlashAttribute("error", "❌ Pedido no encontrado");
            return "redirect:/pedidos/listarPedidos";
        } else {
            model.addAttribute("pedido", pedido);
            return "pedidos/formularioPedidos";
        }
    }

    // Mostrar detalle de un pedido específico
    @GetMapping("/detalle/{id}")
    public String verDetalle(@PathVariable Long id, Model model) {

        Pedido pedido = pedidoService.buscarPorId(id);

        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", detallePedidoService.listarPorPedido(id));
        model.addAttribute("productos", productoService.listar());

        return "pedidos/detallePedido";
    }
    
}
