package com.reuse.reuse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.reuse.reuse.Entity.Producto;
import com.reuse.reuse.Service.ProductoService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    //Listar productos
    @GetMapping({"/listarProductos", ""})
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.listar());
        return "productos/listarProductos";
    }

    // Mostrar formulario para nuevo producto
    @GetMapping("/formularioProductos")
    public String mostrarFormulario(Model model) {
        model.addAttribute("producto", new Producto());
        return "productos/formularioProductos";
    }

    // Agregar nuevo producto
    @PostMapping("/formularioProducto")
    public String aggProducto(
        @RequestParam String nombre,
        @RequestParam String descripcion,
        @RequestParam double precio,
        @RequestParam int stock,
        @RequestParam(defaultValue = "true") boolean activo,
        @RequestParam Long categoriaId
    ) {

        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setDescripcion(descripcion);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setActivo(activo);
        producto.setCategoriaId(categoriaId);

        productoService.guardar(producto);

        return "redirect:/productos/listarProductos";
    }

    // Editar producto
    @GetMapping("editarProducto/{id}")
    public String editar(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Producto producto = productoService.buscarPorId(id);
        if (producto == null) {
            redirect.addFlashAttribute("error", "❌ Producto no encontrado");
            return "redirect:/productos/listarProductos";
        }
        model.addAttribute("producto", producto);
        return "productos/formularioProductos";
    }

}
