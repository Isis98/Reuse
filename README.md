# ♻️ Proyecto Reuse – Sistema de Gestión Comercial

**Proyecto Reuse** es una aplicación web desarrollada en **Java con Spring Boot**, orientada a la gestión comercial de clientes, proveedores, productos, inventario y pedidos.  
El proyecto sigue el **patrón MVC** y utiliza **JDBC** para el acceso a datos, priorizando el control manual de consultas SQL y una comprensión profunda del flujo de información
(con opción de implementar JPA en un futuro).

Este sistema está pensado como un proyecto académico y práctico, con una arquitectura clara, modular y preparada para crecer.

---

## 🚀 Tecnologías Utilizadas

- **Java 17**
- **Spring Boot**
  - Spring MVC
  - JDBC (JdbcTemplate)
- **Thymeleaf**
- **HTML5 / CSS3**
- **MySQL**
- **Maven**
- **Git / GitHub**
- (Tambien se tendrá en cuenta usar Spring Security y/o OAuth2)

---

## 🧱 Arquitectura del Proyecto

El proyecto sigue estrictamente el **patrón Modelo–Vista–Controlador (MVC)**:

Controller → Service → ServiceImpl → Repository (JDBC) → RepositoryImpl → Base de Datos
↓
Vista (Thymeleaf)

---

### Capas del sistema

- **Controller**  
  Maneja las solicitudes HTTP y la comunicación con las vistas.

- **Service / ServiceImpl**  
  Contiene la lógica de negocio y validaciones.

- **Repository / RepositoryImpl**  
  Acceso a datos mediante **JDBC (JdbcTemplate)** y consultas SQL.

- **Entity (Modelo)**  
  Representa las entidades del sistema.

- **View**  
  Vistas HTML con Thymeleaf y fragmentos reutilizables.

---

## 📂 Entidades del Sistema

### Entidades ya implementadas y entidades propuestas.
✅ **Cliente**
- **Proveedor**
✅ **Producto**
✅ **Usuario**
- **Categoria**


### Entidades en desarrollo /️
✅ **Pedido**
- **DetallePedido**
- **Factura**

---

## 🖥️ Interfaz de Usuario

- Vistas desarrolladas con **Thymeleaf**
- Uso de fragmentos reutilizables
- Formularios dinámicos
- Manejo correcto de fechas (`datetime-local`)
- Persistencia de filtros en URLs
- Diseño enfocado en funcionalidad y claridad

