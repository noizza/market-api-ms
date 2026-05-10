## 📐 Arquitectura del Sistema

El siguiente diagrama describe la interacción entre los microservicios, el servidor de descubrimiento y la infraestructura de mensajería:

```mermaid
graph TD
    subgraph Client_Layer [Capa de Cliente]
        Postman[Postman / Frontend]
    end

    subgraph Gateway_Layer [Puerta de Enlace]
        Gateway[Spring Cloud Gateway]
    end

    subgraph Discovery_Layer [Localización]
        Eureka[Eureka Server]
    end

    subgraph Microservices [Ecosistema de Microservicios]
        MS_Clientes[MS Clientes]
        MS_Productos[MS Productos]
        MS_Ventas[MS Ventas]
    end

    subgraph Messaging [Eventos Asíncronos]
        Kafka((Apache Kafka))
    end

    subgraph Persistence [Capa de Datos - SQL Server]
        DB_C[(db_clientes)]
        DB_P[(db_productos)]
        DB_V[(db_ventas)]
    end

    %% Relaciones
    Postman -->|Peticiones HTTP| Gateway
    Gateway -->|Enrutamiento| MS_Ventas
    Gateway -->|Enrutamiento| MS_Clientes
    Gateway -->|Enrutamiento| MS_Productos

    MS_Clientes -.->|Registro| Eureka
    MS_Productos -.->|Registro| Eureka
    MS_Ventas -.->|Registro| Eureka
    Gateway -.->|Descubre| Eureka

    %% Comunicación entre servicios
    MS_Ventas -->|OpenFeign Síncrono| MS_Clientes
    MS_Ventas -->|OpenFeign Síncrono| MS_Productos
    
    MS_Ventas -.->|Publica Evento| Kafka
    Kafka -.->|Actualiza Stock| MS_Productos

    %% Bases de Datos
    MS_Clientes ---> DB_C
    MS_Productos ---> DB_P
    MS_Ventas ---> DB_V

    %% Estilos
    style Eureka fill:#f9f,stroke:#333,stroke-width:2px
    style Kafka fill:#ff9,stroke:#333,stroke-width:2px
    style Gateway fill:#bbf,stroke:#333,stroke-width:2px
