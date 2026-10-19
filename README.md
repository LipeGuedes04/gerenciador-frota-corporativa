# 🚘 Central de Operações de Frota Comercial - Fleet Manager

## 🎯 Escopo do Projeto

O sistema é uma plataforma de gerenciamento de frotas e auditoria de contratos de locação automatizada. Diferente de abordagens estáticas, esta aplicação implementa o controle dinâmico de múltiplos ativos em memória. O operador consegue **cadastrar uma frota ilimitada de veículos**, inspecionar o status operacional de toda a linha, **rastrear e isolar automóveis específicos através da busca por placa corporativa**, efetuar cálculos preditivos de custos por período de uso e gerenciar travas lógicas de ocupação em tempo real.

### 🔄 Dinâmica de Estados do Sistema
```text
[ Entrada de Dados ] ➔ [ Injeção na Lista (Frota) ] ➔ [ Filtro por Predicado (Placa) ] ➔ [ Validação de Ativo ] ➔ [ Despacho de Contrato ]
```

---

## 🎨 Arquitetura de Software (Relacionamento entre Componentes)

O ecossistema foi projetado utilizando o modelo de separação de responsabilidades do paradigma orientado a objetos. A classe de controle desacopla a interface do usuário da persistência de dados, que é delegada de forma segura para uma coleção indexada de objetos do domínio.

```text
       ┌────────────────────────┐
       │       Main.java        │ (Orquestrador de Fluxo e Menu Dinâmico)
       └───────────┬────────────┘
                   │
                   ▼ (Gerencia via ArrayList)
       ┌────────────────────────┐
       │   List<Carro> frota    │ (Coleção elástica de instâncias ativas)
       └───────────┬────────────┘
                   │
         ┌─────────┴─────────┬─────────────────┬─────────────────┐
         ▼                   ▼                 ▼                 ▼
  [Caso 1: .add()]     [Caso 2: Loop]    [Caso 3: Busca]   [Caso 4: State]
  Aloca novo ativo    Vanneia a frota   Simula custos por  Altera flag de
  na memória RAM      e exibe estados   ponteiro de placa  ocupação de item
```

### 📋 Mapeamento de Escopo e Componentes

| Estrutura / Recurso | Classificação | Responsabilidade Técnica no Ecossistema |
| :--- | :--- | :--- |
| 🚀 `Main.java` | Controlador | Intermedeia o console com o motor lógico, mantendo o laço dinâmico e o roteamento via chaves numéricas. |
| 🚘 `Carro.java` | Entidade | Encapsula as regras fiscais do ativo, blindando as propriedades do veículo contra manipulações externas. |
| 📦 `List<Carro>` | Coleção | Atua como banco de dados em memória temporária, agrupando e indexando os objetos gerados. |
| 🔍 `for-each` | Iterador | Varre a estrutura elástica inspecionando predicados textuais com a diretiva `.equalsIgnoreCase()`. |

---

## 🧠 Conceitos de Engenharia Praticados

* **Coleções Genéricas Homogêneas:** Substituição de variáveis isoladas ou arrays rígidos pela interface `List` parametrizada, permitindo a expansão sob demanda da infraestrutura de dados.
* **Busca e Isolamento por Referência (Ponteiros):** Implementação de varreduras lineares para capturar o endereço de memória de um objeto específico dentro de uma coleção e executar métodos diretamente nele.
* **Programação Defensiva com Guards:** Uso de cláusulas de barreira (`frota.isEmpty()`) para interceptar requisições em bases de dados vazias antes que loops desnecessários sejam processados pelo hardware.

---

## 💻 Demonstração Operacional (Console)

Abaixo, veja o comportamento do terminal realizando um fluxo completo de cadastro, listagem, simulação e barreira de locação:

```text
========== LOCADORA ===========

=== MENU PRINCIPAL ==
1 - Cadastrar Novo Carro
2 - Listar Frota Completa
3 - Simular Aluguel por Placa
4 - Confirmar Locação por Placa
5 - Sair do programa
Escolha uma opção: 1

--- CADASTRO DE VEÍCULO ---
Qual o modelo do carro: SUV Premium
Qual a placa do carro: FLT-1010
Qual o preço da diária: 250.00
✅ Veículo cadastrado com sucesso!

=== MENU PRINCIPAL ===
1 - Cadastrar Novo Carro
2 - Listar Frota Completa
3 - Simular Aluguel por Placa
4 - Confirmar Locação por Placa
5 - Sair do programa
Escolha uma opção: 3

--- SIMULAR ALUGUEL POR PLACA ---
Digite a placa do veículo que deseja simular: flt-1010
Quantos dias a pessoa quer alugar? 4
Valor total do aluguel simulado: R\$ 1000.00

=== MENU PRINCIPAL ===
1 - Cadastrar Novo Carro
2 - Listar Frota Completa
3 - Simular Aluguel por Placa
4 - Confirmar Locação por Placa
5 - Sair do programa
Escolha uma opção: 4

--- CONFIRMAR LOCAÇÃO POR PLACA ---
Digite a placa do veículo que deseja ALUGAR: flt-1010
✅ Carro alugado com sucesso!

=== MENU PRINCIPAL ===
1 - Cadastrar Novo Carro
2 - Listar Frota Completa
3 - Simular Aluguel por Placa
4 - Confirmar Locação por Placa
5 - Sair do programa
Escolha uma opção: 5
Saindo do sistema... Obrigado!
```
