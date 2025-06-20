# Proyecto E-commerce: CRUD de Usuarios con Spring Boot

---

## Descripción del Proyecto

Este proyecto es una aplicación web simple desarrollada con **Spring Boot** que implementa un sistema de **CRUD (Create, Read, Update, Delete)** para la gestión de usuarios, productos, órdenes, etc. Fue diseñado como un ejercicio práctico para reforzar conceptos de desarrollo backend, manejo de bases de datos y la arquitectura de aplicaciones web con Spring.

---

## Características Principales

* **User**
* **Crear Usuarios:** Permite registrar nuevos usuarios en el sistema.
* **Listar Usuarios:** Muestra todos los usuarios existentes en una tabla paginada.
* **Ver Detalles del Usuario:** Permite visualizar la información completa de un usuario específico.
* **Actualizar Usuarios:** Permite modificar la información de un usuario existente.
* **Eliminar Usuarios:** Permite remover usuarios del sistema.
* **Validación de Datos:** Implementación de validaciones básicas en los formularios (ej. campos obligatorios, formato de email).
* **Manejo de Excepciones:** Gestión de errores para una experiencia de usuario más robusta.

---

## Tecnologías Utilizadas

* **Backend:**
    * **Java 17+:** Lenguaje de programación.
    * **Spring Boot 3.x:** Framework principal para el desarrollo de la aplicación.
    * **Spring Data JPA:** Para la interacción con la base de datos de manera simplificada.
    * **Spring Web:** Para la creación de APIs REST y controladores.
    * **Maven:** Herramienta de gestión de dependencias y construcción del proyecto.
* **Base de Datos:**
    * **PostgreSQL:**
* **Frontend (si aplica):**
    * **React:**
---

## Metodologías y Patrones de Diseño Aplicados

Durante el desarrollo de este proyecto, se aplicaron las siguientes metodologías y patrones:

* **Metodología Ágil (Scrum/Kanban):** Aunque es un proyecto pequeño y personal, se adoptó un enfoque iterativo y de mejora continua, similar a los principios ágiles, planificando funcionalidades en pequeñas fases.
* **Patrón Arquitectónico en Capas (Layered Architecture):**
    * **Capa de Presentación (Controllers):** Manejo de peticiones HTTP y redirección.
    * **Capa de Lógica de Negocio (Services):** Contiene la lógica principal de la aplicación.
    * **Capa de Persistencia (Repositories):** Interacción directa con la base de datos.
* **Patrones de Diseño de Software:**
    * **Repository Pattern:** Abstracción del acceso a datos.
    * **Service Layer Pattern:** Separación de la lógica de negocio.
    * **DTOs (Data Transfer Objects):** Para transferir datos entre capas de manera optimizada y evitar exponer entidades directamente.
* **Control de Versiones con Git:** Uso de un sistema de control de versiones distribuido para la gestión eficiente de cambios en el código y el historial del proyecto.

---

## Estructura del Proyecto

src/
├── main/
│   ├── java/proyectoEcommerce/
│   │   ├── auth/               (Clases para la autenticación de usuarios)
│   |   ├── config/             (Clases de configuración de Spring)
│   │   ├── controller/         (Controladores REST/MVC)
│   │   ├── model/              (Entidades de base de datos)
│   │   ├── repository/         (Interfaces para acceso a datos)
│   │   ├── service/            (Clases de lógica de negocio)
│   │   └── ProjectApplication.java (Clase principal de Spring Boot)
│   └── resources/
│       ├── static/             (Archivos CSS, JS, imágenes)
│       ├── templates/          (Archivos HTML)
│       └── application.properties (Configuración de la aplicación y BD)
└── test/
├── java/proyectoEcommerce/

---

## Cómo Ejecutar el Proyecto

Para levantar la aplicación en tu entorno local, sigue estos pasos:

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/DiegoSalamancaG/BackendSpringBoot[https://github.com/tu-usuario/nombre-del-repositorio.git](https://github.com/tu-usuario/nombre-del-repositorio.git)
    cd proyectoEcommerce
    ```
2.  **Configurar la base de datos (PostgreSQL):**
    * Crea una base de datos llamada `[ecommerce]`
    * Actualiza el archivo `src/main/resources/application.properties` con tus credenciales:
        ```properties
        spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
        spring.datasource.username=tu_usuario_db
        spring.datasource.password=tu_password_db
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.format_sql=true
        spring.jpa.open-in-view=false
        #desactivar seguridad(Solo para etapas tempranas de desarrollo)
        spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
        ```
3.  **Compilar y Ejecutar con Maven:**
    * Abre una terminal en la raíz del proyecto y ejecuta:
        ```bash
        ./mvnw spring-boot:run
        ```
    * Alternativamente, puedes abrir el proyecto en **IntelliJ IDEA**, hacer clic derecho en `ProjectApplication.java` y seleccionar "Run 'ProjectApplication'".

4.  **Acceder a la Aplicación:**
    * Una vez que la aplicación esté corriendo, ábrela en tu navegador web en la siguiente dirección:
      `http://localhost:8080` (o el puerto que hayas configurado).

---

## Contribuciones (Opcional)

Si este fuera un proyecto colaborativo, aquí podrías añadir cómo otros pueden contribuir:
1.  Haz un "fork" del repositorio.
2.  Crea una nueva rama (`git checkout -b feature/nueva-caracteristica`).
3.  Realiza tus cambios y haz commits descriptivos.
4.  Empuja tu rama (`git push origin feature/nueva-caracteristica`).
5.  Abre un "Pull Request".

---


## Contacto

* **Diego Salamanca:** [LinkedIn](https://www.linkedin.com/in/diego-salamanca-1615b91b0/) | [Correo Electrónico](mailto:diegosalamanca.guajardo@gmail.com)
* **GitHub:** [https://github.com/DiegoSalamancaG](https://github.com/DiegoSalamancaG)

---