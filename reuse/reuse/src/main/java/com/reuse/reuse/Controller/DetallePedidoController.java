package com.reuse.reuse.Controller;

import org.springframework.stereotype.Controller;



import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.reuse.reuse.Entity.DetallePedido;

import com.reuse.reuse.Entity.Producto;
import com.reuse.reuse.Service.DetallePedidoService;
import com.reuse.reuse.Service.PedidoService;
import com.reuse.reuse.Service.ProductoService;

@Controller
@RequestMapping("/detallePedidos")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;
    private final ProductoService productoService;
    private final PedidoService pedidoService;

    public DetallePedidoController(
            DetallePedidoService detallePedidoService,
            ProductoService productoService,
            PedidoService pedidoService) {
        this.detallePedidoService = detallePedidoService;
        this.productoService = productoService;
        this.pedidoService = pedidoService;
    }

    // Agregar detalle a un pedido
    @PostMapping("/agregarDetalle")
    public String agregarDetalle(
            @RequestParam Long pedidoId,
            @RequestParam Long productoId,
            @RequestParam int cantidad) {

        Producto producto = productoService.buscarPorId(productoId);

        if (producto == null) {
            return "redirect:/pedidos/detalle/" + pedidoId;
        }

        DetallePedido d = new DetallePedido();
        d.setPedidoId(pedidoId);
        d.setProductoId(productoId);
        d.setCantidad(cantidad);
        d.setPrecio(producto.getPrecio());

        detallePedidoService.agregarDetalle(d);

        // recalcula total automáticamente
        pedidoService.calcularTotal(pedidoId);

        return "redirect:/pedidos/detalle/" + pedidoId;
    }
}
