# CloudyShop - Java
**Cloudy Shop e-commerce system in Java**

## Sistema de Gestión de Comercio Electrónico

Este proyecto es un sistema de gestión para un comercio electrónico desarrollado en Java como una aplicación de consola para la tienda **CloudyShop**. Permite a diferentes tipos de usuarios (administradores, empleados y clientes) interactuar con el sistema según sus roles. Los **administradores** pueden gestionar etiquetas, empleados y puntos de entrega; los **empleados** pueden gestionar productos y pedidos; y los **clientes** pueden registrarse, gestionar su cuenta, ver productos, crear pedidos y más.

## Descripción

El sistema simula las operaciones básicas de un comercio electrónico, incluyendo:

- **Gestión de usuarios**: Registro e inicio de sesión para administradores, empleados y clientes.
- **Gestión de productos**: Creación, edición y eliminación de productos y etiquetas.
- **Gestión de pedidos**: Creación, actualización y cancelación de pedidos, con manejo de direcciones y pagos.
- **Interfaz de consola**: Interacción a través de menús organizados por roles.

## Instalación

Para instalar y ejecutar este proyecto, sigue estos pasos:

1. **Clona el repositorio**:  
   También puedes descargarlo y trabajar desde una carpeta local.  
   ```bash
   git clone https://github.com/ChrisM2309/cloudyShopJava.git
   ```

2. **Abre el proyecto en IntelliJ**:  
   - Abre IntelliJ IDEA.  
   - Selecciona **"Open"** y navega a la carpeta `cloudyShopJava`.  
   - IntelliJ detectará automáticamente la configuración de Maven.

3. **Instala la librería JUnit**:  
   - Asegúrate de que JUnit 5.10 o una versión más reciente esté configurada en el proyecto.  
   - Si es necesario, agrega la dependencia en el archivo `pom.xml`:  
     ```xml
     <dependency>
         <groupId>org.junit.jupiter</groupId>
         <artifactId>junit-jupiter</artifactId>
         <version>5.10.0</version>
         <scope>test</scope>
     </dependency>
     ```

4. **Compila el proyecto**:  
   - IntelliJ compilará automáticamente el proyecto gracias a Maven.  

5. **Ejecuta el programa**:  
   - Navega a la carpeta `src/main/java/sistema`.  
   - Haz clic derecho en `Main.java` y selecciona **"Run 'Main'"**.
 
### Requisitos
- **Java Development Kit (JDK)** 8 o superior.
- **IntelliJ IDEA** (recomendado).
- **Maven** (integrado en IntelliJ).
- **Git** (para clonar el repositorio).

### Credenciales de prueba para usuario administrador

- **Usuario:** admin1
- **Contraseña:** admin123

## Ejecución de Pruebas Unitarias

Para ejecutar las pruebas unitarias del proyecto:

1. **Navega a la carpeta de pruebas**:  
   - En IntelliJ, abre la carpeta `src/test/java/usuarioTest`.  

2. **Ejecuta las clases de prueba**:  
   - Dentro de `usuarioTest`, encontrarás tres clases: `ClienteTest`, `EmpleadoTest` y `AdminTest`.  
   - Haz clic derecho en cada clase y selecciona **"Run 'NombreDeLaClase'"**.  
   - Alternativamente, selecciona la carpeta `usuarioTest` y elige **"Run 'All Tests'"** para ejecutar todas las pruebas a la vez.  

Gracias a la configuración de Maven, las pruebas funcionarán correctamente siempre que las dependencias estén bien definidas.

## Generación de Documentación JavaDoc

El proyecto incluye documentación JavaDoc para todas sus clases y métodos. Para generar esta documentación en IntelliJ IDEA, sigue estos pasos:

1. **Abre IntelliJ IDEA**:  
   Asegúrate de que el proyecto `cloudyShopJava` esté cargado.

2. **Accede a la herramienta JavaDoc**:  
   - En la barra de menú superior, ve a **Tools** > **Generate JavaDoc**.

3. **Configura las opciones**:  
   - En la ventana que aparece, selecciona la opción **Whole project** para incluir todas las clases del proyecto.  
   - En el campo **Output directory**, especifica la carpeta donde deseas guardar la documentación (por ejemplo, `docs` en el directorio raíz del proyecto).  
   - En **Visibility level**, elige **Private** para asegurarte de que se incluyan todas las clases, métodos y atributos, incluso los de visibilidad privada.  
   - Opcionalmente, puedes marcar otras casillas como "Include @author tags" o "Link to JDK documentation" si lo deseas, pero no son esenciales.

4. **Genera la documentación**:  
   - Haz clic en **OK** para iniciar el proceso de generación.  
   - IntelliJ creará una carpeta (por ejemplo, `docs`) con los archivos HTML de la documentación.

5. **Visualiza la documentación**:  
   - Navega a la carpeta de salida (`docs`) y abre el archivo `index.html` en un navegador web para explorar la documentación completa.

### Nota Importante
Seleccionar **Whole project** y el nivel de visibilidad **Private** asegura que se documenten todas las clases y métodos del proyecto, incluyendo los detalles internos. Esto es especialmente útil para desarrolladores que necesitan una visión completa del código, como las clases en los paquetes `modelo` y `sistema`, así como las clases de prueba en `test`.

## Estructura del Proyecto

El proyecto está organizado en dos carpetas principales dentro de `src`: **`main`** y **`test`**.

- **`main`**: Contiene el código fuente de la aplicación.  
  - **Paquetes**:  
    - **`modelo`**: Incluye las clases de entidades del sistema.  
      - **`usuario`**: Clases de usuarios (`Admin`, `Empleado`, `Cliente`).  
      - **`producto`**: Clases relacionadas con productos (`Producto`, `Etiqueta`).  
      - **`pedido`**: Clases para gestionar pedidos (`Pedido`, `Direccion`, `Pago`).  
    - **`sistema`**: Contiene la clase `Main` y otras clases de sistemas por rol (`SistemaCliente`, `SistemaEmpleado`, `SistemaAdmin`).

- **`test`**: Contiene el código de las pruebas unitarias.  
  - **Paquetes**:  
    - **`usuarioTest`**: Clases de prueba para los usuarios (`ClienteTest`, `EmpleadoTest`, `AdminTest`).

## Funcionalidades Nuevas y Mejoras

Desde la entrega anterior, se han incorporado las siguientes funcionalidades y mejoras:

- **Testeos Unitarios**:  
  - Se agregaron pruebas unitarias para las clases de usuarios (`Cliente`, `Empleado`, `Admin`) usando JUnit 5.  
  - Estas pruebas verifican las funcionalidades principales y aseguran un manejo adecuado de errores.

- **Mejoras en el Manejo de Errores**:  
  - Las clases de usuarios (`Cliente`, `Empleado`, `Admin`) ahora gestionan mejor los errores y casos de mal uso.  
  - Los métodos imprimen mensajes de error claros y retornan de forma controlada al menú anterior, evitando que el programa se detenga abruptamente.  

Estas mejoras representan un avance significativo en la robustez del sistema. La elección de testear las clases de usuarios se debe a que son las más importantes, ya que contienen las funcionalidades principales del sistema.

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

## Consideraciones Especiales sobre la Implementación

La implementación actual tiene ciertas características y limitaciones importantes:

- **Datos Precargados**:  
  El sistema inicializa datos de prueba al arrancar (administradores, empleados, clientes, productos, etiquetas) para facilitar las pruebas.

- **Persistencia de Datos**:  
  No se ha implementado persistencia en archivos ni bases de datos.

- **Herencia en Usuarios**:  
  La clase `Admin` hereda de `Empleado`, compartiendo funcionalidades comunes pero añadiendo permisos adicionales para tareas administrativas.

- **Uso de `ArrayList`**:  
  Las colecciones de datos (empleados, clientes, productos) se gestionan con `ArrayList`, lo que es práctico pero puede no ser eficiente para grandes volúmenes de datos.

### Mejoras a Futuro
- Persistencia de datos (archivos o base de datos).  
- Mejoras en la interfaz de consola (mensajes más detallados o formato visual).
