# RestSpringBootHexagonal
]
# 🧩 Projeto: API com Arquitetura Hexagonal (Spring Boot)

## 📌 Objetivo

Este projeto foi desenvolvido como um laboratório prático para estudar e aplicar **arquitetura hexagonal (Ports & Adapters)** em um cenário real com:

* Spring Boot
* JPA / Hibernate
* DTOs
* Paginação desacoplada
* Filtros dinâmicos
* Tratamento global de exceções

A proposta não é apenas funcionalidade, mas **evolução arquitetural progressiva**.

---

# 🏗️ Arquitetura

O projeto segue o padrão **Hexagonal Architecture**, separando claramente:

```plaintext
Controller → UseCase → Domain → Port → Adapter → Database
```

## 📦 Camadas

### 🔹 Domain

Contém a lógica de negócio pura, sem dependência de frameworks.

* Entidades: `Book`, `Person`, `Gender`
* Regras de negócio (validações, métodos de alteração)
* Interfaces (Ports)
* Paginação própria
* Filtros

✔ Sem Spring
✔ Sem JPA

---

### 🔹 Application

Responsável por adaptação entre API e domínio.

* DTOs (entrada/saída)
* Mappers (DTO ↔ Domain)

---

### 🔹 Adapters

#### 📥 Entrada (Web)

* Controllers REST
* Validação (`@Valid`)
* Exception Handler global

#### 📤 Saída (Persistência)

* JPA Entities
* Repositories (Spring Data)
* Adapters (implementação dos Ports)
* Specification para filtros

---

# 📁 Estrutura do Projeto

```plaintext
com.decodex.br
│
├── domain
│   ├── model
│   ├── filter
│   ├── pagination
│   ├── port (in / out)
│   ├── service
│   └── exception
│
├── application
│   ├── dto (book / person)
│   └── mapper
│
├── adapters
│   ├── in (web + exception)
│   └── out (persistence)
│
└── config (opcional)
```

---

# 🔄 Evolução do Projeto (Passo a Passo)

## 1. Estrutura inicial (Layered)

* Entidades JPA simples (`Book`, `Person`)

---

## 2. Separação de domínio

* Remoção de anotações JPA
* Adição de validações e regras de negócio

---

## 3. Ports (Interfaces)

* `UseCase` (entrada)
* `RepositoryPort` (saída)

---

## 4. Services (Casos de uso)

* Implementação das regras usando apenas interfaces

---

## 5. Adapters

* JPA isolado em `adapters.out`
* Mappers entre Entity ↔ Domain

---

## 6. Controllers

* Primeira versão usando domínio direto

---

## 7. DTOs

* Separação entre API e domínio
* Create / Update / Response

---

## 8. Exception Handling

* `@RestControllerAdvice`
* Padronização de erros

---

## 9. Paginação desacoplada

### Problema

O domínio dependia de `Pageable` (Spring)

### Solução

Criação de:

```java
PageRequest
PageResult
```

### Benefício

✔ Domínio independente de framework

---

## 10. Filtros dinâmicos

### Estratégia

* Filtro no domínio (`PersonFilter`)
* Conversão para `Specification` no adapter

### Benefício

✔ Mantém domínio limpo
✔ Permite queries dinâmicas

---

## 11. Correção de domínio financeiro

### Mudança

```java
Double → BigDecimal
```

### Motivo

Evitar problemas de precisão com valores monetários

---

# 🔍 Funcionalidades

* CRUD de Book e Person
* Paginação desacoplada
* Filtros dinâmicos:

  * firstName
  * lastName
  * address
  * gender
* Validação com Bean Validation
* Tratamento global de exceções

---

# ⚙️ Tecnologias

* Java 21
* Spring Boot 3
* Spring Data JPA
* Hibernate
* PostgreSQL (ou outro)
* Jakarta Validation

---

# 🧠 Decisões Arquiteturais

## ❓ Por que não usar `Pageable` no domínio?

Para evitar acoplamento com Spring e permitir troca de framework.

---

## ❓ Por que usar DTO?

Para desacoplar:

* API
* domínio
* persistência

---

## ❓ Por que Specification só no adapter?

Porque é tecnologia específica (Spring Data), e não regra de negócio.

---

# 📈 Objetivo de Aprendizado

Este projeto serve como:

✔ laboratório de arquitetura
✔ material de estudo
✔ base para projetos maiores
✔ documentação de evolução técnica

---

# 💬 Conclusão

O projeto evolui de um CRUD simples para uma arquitetura desacoplada, demonstrando na prática:

* separação de responsabilidades
* independência de frameworks
* design orientado a domínio

---
