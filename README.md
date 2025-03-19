# CloudyShop - Java
Cloudy Shop e-commerce system in Java

## Sistema de Gestión de Comercio Electrónico

Este proyecto es un sistema de gestión para un comercio electrónico desarrollado en Java como una aplicación de consola para la tienda CloudyShop. 
Permite a diferentes tipos de usuarios (administradores, empleados y clientes) interactuar con el sistema según sus roles. Los administradores pueden gestionar etiquetas, empleados y puntos de entrega; los empleados pueden gestionar productos y pedidos; y los clientes pueden registrarse, gestionar su cuenta, ver productos, crear pedidos y más.

## Descripción

El sistema simula las operaciones básicas de un comercio electrónico, incluyendo:

- **Gestión de usuarios**: Registro e inicio de sesión para administradores, empleados y clientes.
- **Gestión de productos**: Creación, edición y eliminación de productos y etiquetas.
- **Gestión de pedidos**: Creación, actualización y cancelación de pedidos, con manejo de direcciones y pagos.
- **Interfaz de consola**: Interacción a través de menús organizados por roles.

## Instalación

Para instalar y ejecutar este proyecto, sigue estos pasos:

1. **Clona el repositorio**: 
Tambien puedes descargarlo y reproducirlo desde una carpeta. 
   ```bash
   git clone https://github.com/ChrisM2309/cloudyShopJava.git
   ```

2. **Navega al directorio del proyecto**:
Usa la carpeta correcta, cloudyShopJava
   ```bash
   cd cloudyShopJava
   ```

3. **Compila el código**:
   ```bash
   javac Main.java
   ```

4. **Ejecuta el programa**:
   ```bash
   java Main.java
   ```

### Requisitos
- Java Development Kit (JDK) 8 o superior.
- Git (para clonar el repositorio).

## Uso

Al iniciar el programa, se presenta un menú principal con las siguientes opciones:

1. Iniciar sesión como administrador
2. Iniciar sesión como empleado
3. Iniciar sesión como cliente
4. Registrarse como cliente
5. Salir

Dependiendo de la opción seleccionada, el usuario será dirigido a un menú específico para su rol. El sistema incluye datos iniciales precargados (administradores, empleados, clientes, productos, etc.) para facilitar las pruebas.

### Menú de Administrador
- **Gestión de Etiquetas**: Crear y eliminar etiquetas.
- **Gestión de Empleados**: Registrar, eliminar y editar información de empleados.
- **Gestión de Inventario**: Consultar el inventario completo.
- **Gestión de Pedidos**: Consultar cantidad de pedidos y pagos realizados.
- **Gestión de Puntos de Entrega**: Agregar, editar y eliminar puntos de entrega.
- **Cerrar sesión**.

### Menú de Empleado
- **Gestión de Productos**: Consultar, agregar, eliminar y editar productos; agregar y eliminar etiquetas de productos.
- **Gestión de Inventario**: Consultar inventario de un producto, registrar entrada de inventario, recibir alertas de inventario bajo.
- **Gestión de Pedidos**: Actualizar estado de pedido, cancelar pedido, ver pedidos pendientes, verificar estado de pago, consultar dirección de pedido.
- **Cerrar sesión**.

### Menú de Cliente
- **Mi Cuenta**: Editar datos, eliminar cuenta, gestionar métodos de pago y direcciones.
- **Productos**: Ver productos, filtrar por etiqueta, consultar inventario, agregar productos a un pedido.
- **Mis Pedidos**: Crear pedido, consultar estado de pedidos, ver historial de compras, agregar dirección y método de pago a un pedido, cancelar pedido.
- **Cerrar sesión**.

## Estructura del Proyecto

El proyecto está organizado en paquetes que agrupan las clases según su funcionalidad. A continuación, se detalla cada paquete y las clases que contiene:

- **`modelo.usuario`**  
  Este paquete contiene las clases relacionadas con los diferentes tipos de usuarios del sistema:  
  - **`Admin.java`**: Clase que hereda de `Empleado` y añade funcionalidades específicas para administradores, como la gestión de etiquetas, empleados y puntos de entrega.  
  - **`Empleado.java`**: Clase base para los empleados, incluye métodos para gestionar productos, inventario y pedidos.  
  - **`Cliente.java`**: Representa a los clientes, permitiéndoles gestionar su cuenta, consultar productos y realizar pedidos.  

- **`modelo.producto`**  
  Este paquete agrupa las clases relacionadas con los productos y su categorización:  
  - **`Producto.java`**: Define los productos del catálogo con atributos como nombre, descripción, precio, inventario y etiquetas asociadas.  
  - **`Etiqueta.java`**: Permite clasificar los productos en categorías (por ejemplo, "Electrónica", "Hogar").  

- **`modelo.pedido`**  
  Este paquete contiene las clases que gestionan los pedidos y sus componentes:  
  - **`Pedido.java`**: Representa un pedido, incluyendo los productos seleccionados, la dirección de entrega, el método de pago y el estado del pedido.  
  - **`Direccion.java`**: Almacena la información de las direcciones, tanto de los clientes como de los puntos de entrega.  
  - **`Pago.java`**: Gestiona los métodos de pago, incluyendo el tipo (por ejemplo, tarjeta, efectivo), los datos asociados y el estado del pago.  

- **`Main.java`**  
  Esta clase principal coordina la lógica del programa, maneja la interacción con el usuario a través de la consola y gestiona las sesiones según los roles.

---

### Descripción de las Funcionalidades Implementadas

El sistema incluye una serie de funcionalidades clave que permiten a los usuarios interactuar con el comercio electrónico según sus roles. Estas son las principales características implementadas:

- **Autenticación de Usuarios**  
  - Los usuarios pueden iniciar sesión como administradores, empleados o clientes.  
  - Los clientes tienen la opción de registrarse en el sistema.  

- **Gestión de Productos**  
  - Los **administradores** pueden crear y eliminar etiquetas para clasificar productos.  
  - Los **empleados** pueden agregar, eliminar y editar productos en el catálogo, así como asignar o quitar etiquetas a los productos.  

- **Gestión de Inventario**  
  - Los empleados pueden consultar el inventario de productos, registrar nuevas entradas de inventario y recibir alertas cuando el inventario de un producto está bajo.  

- **Gestión de Pedidos**  
  - Los **clientes** pueden crear pedidos, añadir productos, seleccionar direcciones y métodos de pago, y cancelar pedidos si es necesario.  
  - Los **empleados** pueden actualizar el estado de los pedidos, cancelarlos y verificar el estado de los pagos asociados.  

- **Gestión de Usuarios**  
  - Los **administradores** pueden registrar, eliminar y editar la información de los empleados.  
  - Los **clientes** pueden editar sus datos personales, gestionar sus direcciones y métodos de pago.  

- **Puntos de Entrega**  
  - Los **administradores** pueden agregar, editar y eliminar puntos de entrega disponibles para los pedidos.  


---

### Consideraciones Especiales sobre la Implementación

La implementación actual tiene ciertas características y limitaciones que es importante destacar:

- **Datos Precargados**  
  El sistema inicializa datos de prueba al arrancar (administradores, empleados, clientes, productos, etiquetas). Esto permite probar las funcionalidades sin necesidad de ingresar datos manualmente.  

- **Persistencia de Datos**  
No se ha implementado persistencia en archivos ni bases de datos.  

- **Validación de Entradas**  
  La validación de las entradas del usuario es básica. Por ejemplo, no se verifican IDs inexistentes ni se manejan adecuadamente entradas no numéricas cuando se esperan números, lo que puede provocar errores.  

- **Herencia en Usuarios**  
  La clase `Admin` hereda de `Empleado`, compartiendo funcionalidades comunes pero añadiendo permisos adicionales para tareas administrativas. Esto refleja una estructura jerárquica en los roles de usuario.  

- **Uso de `ArrayList`**  
  Las colecciones de datos (como listas de empleados, clientes o productos) se gestionan con `ArrayList`. Esto facilita la manipulación de datos, pero puede no ser eficiente para grandes volúmenes de información.  

### Mejoras a futuro

- Validación más robusta de entradas del usuario.
- Persistencia de datos (almacenamiento en archivos o base de datos).
- Mejoras en la interfaz de consola (mensajes más detallados o formato visual).
