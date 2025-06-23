
# Proyecto E-commerce: CRUD de Usuarios con Spring Boot

---

## 🧾 Descripción del Proyecto

Este proyecto es una aplicación web simple desarrollada con **Spring Boot** que implementa un sistema de **CRUD (Create, Read, Update, Delete)** para la gestión de usuarios, productos, órdenes, etc. Fue diseñado como un ejercicio práctico para reforzar conceptos de desarrollo backend, manejo de bases de datos y arquitectura de aplicaciones web con Spring.

---

## ✨ Características Principales

**Gestión de Usuarios:**

- **Crear Usuarios:** Permite registrar nuevos usuarios en el sistema.
- **Listar Usuarios:** Muestra todos los usuarios existentes en una tabla paginada.
- **Ver Detalles del Usuario:** Visualiza la información completa de un usuario específico.
- **Actualizar Usuarios:** Permite modificar la información de un usuario existente.
- **Eliminar Usuarios:** Elimina usuarios del sistema.
- **Validación de Datos:** Validaciones básicas en formularios (campos obligatorios, formato de email, etc.).
- **Manejo de Excepciones:** Gestión de errores para una experiencia de usuario más robusta.

---

## 🛠️ Tecnologías Utilizadas

**Backend:**
- **Java 17+**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Spring Web**
- **Maven**

**Base de Datos:**
- **PostgreSQL**

**Frontend (si aplica):**
- **React**

---

## 🧩 Metodologías y Patrones de Diseño

- **Metodología Ágil (Scrum/Kanban):** Enfoque iterativo y de mejora continua.
- **Arquitectura en Capas (Layered Architecture):**
  - **Controller:** Manejo de peticiones HTTP.
  - **Service:** Lógica de negocio.
  - **Repository:** Interacción con la base de datos.
- **Patrones de Diseño:**
  - **Repository Pattern**
  - **Service Layer Pattern**
  - **DTOs (Data Transfer Objects)**
- **Control de Versiones:** Uso de Git para gestión del código fuente.

---

## 🗂️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/proyectoEcommerce/
│   │   ├── auth/                  # Autenticación de usuarios
│   │   ├── config/                # Configuración de Spring
│   │   ├── controller/            # Controladores REST
│   │   ├── model/                 # Entidades (JPA)
│   │   ├── repository/            # Repositorios (interfaces)
│   │   ├── service/               # Servicios (lógica de negocio)
│   │   └── ProjectApplication.java # Clase principal
│   └── resources/
│       ├── static/                # Archivos estáticos (CSS, JS, imágenes)
│       ├── templates/             # Vistas (HTML, Thymeleaf)
│       └── application.properties # Configuración general
└── test/
    └── java/proyectoEcommerce/   # Pruebas
```

---

## 🚀 Cómo Ejecutar el Proyecto

1. **Clonar el repositorio:**

```bash
git clone https://github.com/DiegoSalamancaG/BackendSpringBoot.git
cd BackendSpringBoot
```

2. **Configurar la base de datos (PostgreSQL):**

- Crea una base de datos llamada `ecommerce`
- Edita el archivo `src/main/resources/application.properties` con tus credenciales:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce
spring.datasource.username=tu_usuario_db
spring.datasource.password=tu_password_db
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.open-in-view=false
# Desactivar seguridad (solo para desarrollo)
spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
```

3. **Compilar y ejecutar con Maven:**

```bash
./mvnw spring-boot:run
```

> O bien, abre el proyecto en **IntelliJ IDEA**, haz clic derecho en `ProjectApplication.java` y selecciona “Run”.

4. **Acceder a la aplicación:**

Abre tu navegador y ve a `http://localhost:8080`

---

## 🤝 Contribuciones

¿Quieres contribuir? ¡Genial! Sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama: `git checkout -b feature/nueva-caracteristica`
3. Realiza tus cambios y haz commits claros.
4. Empuja tu rama: `git push origin feature/nueva-caracteristica`
5. Abre un Pull Request.

---

## 📬 Contacto

- **Diego Salamanca**  
  [LinkedIn](https://www.linkedin.com/in/diego-salamanca-1615b91b0/) | [Correo electrónico](mailto:diegosalamanca.guajardo@gmail.com)  
- **GitHub:** [https://github.com/DiegoSalamancaG](https://github.com/DiegoSalamancaG)

---
