# Actividad 3 - Trabajo Practico 7 - Grupo 6

## 3.- Responda las siguientes preguntas:

### a) ¿Por qué no se dibuja la relación de agregación entre la clase CollectionProducto y Producto?
No se dibuja la relación de agregación entre CollectionProducto y Producto porque el vínculo ya se entiende a partir de un atributo en CollectionProducto, ademas su función es guardar y administrar una lista de objetos Producto, eso no es una relación conceptual entre objetos del sistema, sino una estructura de almacenamiento.

### b) ¿Cuántos atributos tiene la clase Factura? ¿Cuáles son los nombres de esos atributos?
La clase Factura tiene cuatro atributos:
- `nroFactura: long`
- `fecha: LocalDate`
- `clientes: Cliente`
- `detalles: list`

### c) ¿Cómo se llama la relación que se establece entre Factura y Detalle?
Se llama relación de `composición`, por lo que los objetos detalle dependen completamente de la factura a la que pertenecen. Si la factura se elimina, sus detalles también se eliminan.

### d) ¿Cómo se llama la relación entre las clases Factura y Cliente?
En el diagrama UML inicial no estaba especificada ninguna relación, pero nosotros establecimos una relación de `asociación`, con la siguiente multiplicidad (cada factura está asociada a un único cliente, pero un cliente puede tener muchas facturas a su nombre).

### e) ¿Por qué los atributos de las clases Collections son públicos?
Son públicas para poder acceder directamente desde otras clases, el objetivo de las clases collections no es proteger datos, sino permitir acceso global y rápido durante la ejecución del programa.

### f) Describa las características de todos los métodos de la clase CollectionClientes.

#### 1.	agregarCliente: Permite agregar un nuevo cliente a la colección, siempre que no exista previamente uno con el mismo DNI. Caracteristicas:

- **Es público.**
- **Es estático.**
- **Es procedimiento.**
- **Recibe como parámetro cliente -> objeto de tipo Cliente que se desea agregar.**
- **Incluye validaciones.**

#### 2.	buscarCliente: Recorre la colección buscando un cliente cuyo DNI coincida con el valor recibido como parámetro. Se utiliza en distintos puntos del sistema, por ejemplo, al realizar una venta o un crédito. Caracteristicas:

- **Es público.**
- **Es estático.**
- **Es una función.**
- **Devuelve un objeto Cliente si se encuentra, sino retorna null.**
- **Recibe como parámetro el dni del cliente a buscar.**

#### 3.	precargarClientes: Carga en la colección un conjunto de clientes predefinidos, permitiendo iniciar y probar el programa. Caracteristicas:

- **Es público.**
- **Es estático.**
- **Es procedimiento, por lo que no retorna nada**
- **No recibe parámetros.**