
# 🌤 Sistema de Previsão do Tempo (gRPC + Spring Boot + REST)

Este projeto implementa um sistema de previsão do tempo utilizando **gRPC como camada de comunicação interna** e uma **API REST como interface externa**.

A aplicação permite:

* Cadastro de cidades
* Consulta de temperatura atual
* Previsão de 5 dias
* Estatísticas climáticas (média, mínima e máxima)

---

# 🚀 Como rodar o projeto

## 📌 Pré-requisitos

* Java 17+
* Maven
* IntelliJ ou outra IDE
* Postman (para testes REST)

---

## ▶️ Executando o projeto

Clone o repositório:

```bash
git clone https://github.com/seu-usuario/sistema-previsao-tempo.git
```

Entre na pasta:

```bash
cd sistema-previsao-tempo
```

Execute:

```bash
mvn clean install
mvn spring-boot:run
```

A aplicação estará disponível em:

```
http://localhost:8080
```

---

# 🌐 Endpoints REST (Postman)

## 📍 Cadastrar cidade

```
POST /clima/cidade?nome=Goiania
```

## 📍 Listar cidades

```
GET /clima/cidades
```

## 📍 Temperatura atual

```
GET /clima/temperatura?cidade=Goiania
```

## 📍 Previsão 5 dias

```
GET /clima/previsao?cidade=Goiania
```

## 📍 Estatísticas

```
GET /clima/estatisticas?cidade=Goiania
```

---

# 📄 Explicação do arquivo `.proto`

O arquivo `.proto` define toda a comunicação gRPC do sistema.

## 📌 Service (serviço gRPC)

O serviço define os métodos disponíveis:

```proto
service ClimaService {
    rpc cadastrarCidade (CidadeRequest) returns (MensagemResponse);
    rpc listarCidades (Empty) returns (ListaCidadesResponse);
    rpc obterTemperaturaAtual (CidadeRequest) returns (TemperaturaResponse);
    rpc previsaoCincoDias (CidadeRequest) returns (PrevisaoResponse);
    rpc estatisticasClimaticas (CidadeRequest) returns (EstatisticaResponse);
}
```

### ✔ Explicação:

* Define os métodos remotos disponíveis
* Cada método é uma chamada gRPC
* Funciona como uma “interface” do sistema

---

## 📌 Messages (estruturas de dados)

Exemplo:

```proto
message CidadeRequest {
    string nome = 1;
}
```

### ✔ Explicação:

* Define os dados enviados entre cliente e servidor
* Campos possuem números únicos (1,2,3...)
* São serializados pelo Protobuf

Outros exemplos:

* `TemperaturaResponse`
* `PrevisaoDia`
* `EstatisticaResponse`

---

## 📌 RPCs implementados

### ✔ cadastrarCidade

Cadastra uma cidade no sistema.

### ✔ listarCidades

Retorna todas as cidades cadastradas.

### ✔ obterTemperaturaAtual

Retorna a temperatura atual simulada.

### ✔ previsaoCincoDias

Retorna previsão dos próximos 5 dias.

### ✔ estatisticasClimaticas

Calcula:

* média
* mínima
* máxima

---

## ⚙️ Como o `.proto` gera código (Stubs)

Quando o Maven compila o projeto:

```bash
mvn compile
```

O plugin do protobuf gera automaticamente:

* Classes Java das mensagens (`CidadeRequest`, etc.)
* Classe do serviço (`ClimaServiceGrpc`)
* Stubs para cliente e servidor

📌 Exemplo gerado:

```java
ClimaServiceGrpc.ClimaServiceBlockingStub
ClimaServiceGrpc.ClimaServiceImplBase
```

---

# 🔄 Fluxo da aplicação (REST → gRPC)

## 📌 Arquitetura:

```
Postman (HTTP REST)
        ↓
ClimaController (Spring REST)
        ↓
gRPC Stub (BlockingStub)
        ↓
ClimaGrpcService (Servidor gRPC)
        ↓
ClimaService (lógica de negócio)
```

---

## 📌 Explicação do fluxo

1. O usuário faz uma requisição HTTP (Postman)
2. O Controller REST recebe a requisição
3. O Controller chama um **gRPC Stub**
4. O Stub envia a requisição via gRPC
5. O servidor gRPC executa o método real
6. O service retorna os dados
7. A resposta volta até o Postman

---

# 📸 Prints do sistema

### ✔ Cadastro de cidade

<img width="1339" height="487" alt="image" src="https://github.com/user-attachments/assets/790965d5-1be1-446b-aca3-7faaca6418f5" />

<img width="1343" height="504" alt="image" src="https://github.com/user-attachments/assets/a67b645e-4767-4347-a95d-710a7f9bdef0" />

### ✔ Previsão do tempo

<img width="1339" height="522" alt="image" src="https://github.com/user-attachments/assets/96985c2e-c944-44f8-b32a-bc34f0fcc809" />

<img width="1329" height="845" alt="image" src="https://github.com/user-attachments/assets/7b6a56dc-bf65-4c51-a48d-81d69022dd78" />

### ✔ Estatísticas

<img width="1340" height="578" alt="image" src="https://github.com/user-attachments/assets/1f9c1587-82fb-48fd-81a4-8f1afe299ef3" />

---

# 🧠 Tecnologias utilizadas

* Java 17
* Spring Boot
* gRPC
* Protocol Buffers (Protobuf)
* Maven
* Postman

---

# 📌 Autor

Projeto acadêmico – Sistema de Previsão do Tempo com gRPC
José Victor Araújo Rojas

