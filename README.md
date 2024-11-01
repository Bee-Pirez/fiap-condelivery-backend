# :package:Condelivery 

<img
  src="./assets/logoIcon.svg"
  style="height: 100px; margin: 10px 0"
/>

<p>Backend do Condelivery, usando arquitetura de microsservicos com SpringBoot.</p>
<p>O Condelivery é um projeto acadêmico de um sistema de entregas exclusivo para condomínios. A solução visa facilitar o recebimento e a gestão de encomendas, oferecendo uma plataforma web e móvel para os moradores.</p>

* Pitch: [YouTube](https://www.youtube.com/watch?v=QaElNAv6Ne0)
* [Protótipo](https://www.figma.com/design/JKM6bqd7yQhenKmrgP8qcI/Condelivery?node-id=0-1&node-type=canvas&t=FIJrbOOBgEBK51w3-0)
* [Front-end Mobile](https://github.com/andrade-tiago/condelivery.app)
* [Front-end Web](https://github.com/josiastavares/Condelivery-front-web)


## :wrench: Serviços implementados:
- <p><b>ConfigServer</b> </p> 
- <p><b>Discovery</b> </p>
- <p><b>Gateway</b> (com Spring Cloud) </p>
- <p>Micro Serviço de <b>produto </b>:</p>
<img
  src="./assets/ProdutosRelacionamento.png"
/>
- <p>Micro Serviço de <b>autenticação</b> </p>
- <p>Micro Serviço de <b>Usuário</b> </p>
<img
  src="./assets/UsuariosRelacionamento.png"
/>
- <p>Micro Serviço de <b>pedido</b></p>

<img
  src="./assets/ordersRelacionmento.png"
/>

- <p>Micro Serviço de <b>Entrega</b> </p>
- <p>Micro Serviço de <b>Avaliação</b> </p>

## :construction: Serviços futuros:
- <p>Micro Serviço de <b>Chat com entregador</b> (Websocket)</p>
- <p>Micro Serviço de <b>pagamento</b>(api mercado pago e cartão stripe) </p>

## :triangular_ruler: Design Patterns usados:
<p>Data Transfer Objects</p>
<p>Repository Pattern</p>
<p>Service Layer Pattern</p>
<p>Controller</p>


## :gear: Tecnologias:
- **Java 21**: Linguagem de programação principal.
- **Spring Boot**: Framework para simplificação do desenvolvimento de aplicações Java.
- **Spring Security**: Oferece autenticação e autorização em aplicações Java. Ele protege recursos sensíveis, garantindo que apenas usuários autenticados tenham acesso. Utilização de JWT (JSON Web Tokens) para autenticação.
- **Spring Data JPA**: Abstração da camada de persistência que simplifica o uso de JPA para interagir com bancos de dados.
- **Spring Web**: Facilita a construção de aplicações web.
- **Spring WebSockets**: Facilita a construção de aplicações web em tempo real, permitindo comunicação bidirecional entre o cliente e o servidor..
- **Spring Boot DevTools**: Conjunto de ferramentas que melhora a experiência de desenvolvimento com aplicações Spring Boot.
- **Spring Cloud Netflix** (Eureka Server, Discovery...): Ferramentas para integração de microsserviços e registro de serviços, incluindo o Eureka para service discovery (descoberta de serviços)..
- **Spring Cloud Open Feign**: Cliente HTTP declarativo que simplifica a comunicação entre microserviços.
- **Spring Cloud Gateway**:  Framework para roteamento de API e gateway de microsserviços.
- **Maven**: Ferramenta de automação de build e gerenciamento de dependências.
- **Flyway**: Biblioteca para controle de versão do banco de dados (migrations).
- **Lombok**: Biblioteca para geração de código repetitivo (boilerplate).

## 🚀 Instalação e Execução

### Pré-requisitos

- JDK 21 instalado
- Maven instalado

### Passos para rodar o projeto

1. Clone o repositório:

    ```sh
    git clone https://github.com/Bee-Pirez/fiap-condelivery-ms.git
    cd fiap-condelivery-ms
    ```






