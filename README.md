# MedStock 
O MedStock é uma aplicação de software robusta e escalável, desenvolvida para atender às necessidades específicas do setor farmacêutico, com foco na gestão de estoque e vendas.

* Gestão de Estoque: Cadastre e rastreie produtos farmacêuticos de forma detalhada, incluindo informações sobre fornecedores, datas de validade e números de lote. Mantenha o controle em tempo real das quantidades disponíveis e receba alertas proativos quando o estoque estiver baixo.

* Registro de Vendas: Registre vendas com facilidade, fornecendo informações completas sobre produtos vendidos, clientes e detalhes financeiros. A integração perfeita com sistemas de prescrição médica permite a venda de medicamentos sujeitos a receita.

* Gestão Financeira: Acompanhe suas receitas e despesas, e obtenha insights financeiros valiosos por meio de relatórios detalhados de lucros, perdas e fluxo de caixa.

* Controle de Acesso Seguro: Defina níveis de permissão de usuário para garantir que apenas pessoal autorizado acesse funções específicas da aplicação. Proteja dados sensíveis de clientes e informações confidenciais do negócio.

## Autores

- [@GustavoTBett](https://github.com/GustavoTBett)

# Front-end
O front-end do MedStock é desenvolvido utilizando a biblioteca React, interface do usuário foi projetada com uma abordagem centrada no usuário, fornecendo uma experiência agradável e eficiente. 

# Back-end
O back-end do MedStock é construído utilizando o ecossistema do Spring, uma estrutura de desenvolvimento Java que oferece uma abordagem abrangente para a criação de aplicativos corporativos.

* Spring Boot: A utilização do Spring Boot acelera o processo de desenvolvimento, facilitando a configuração e fornecendo um ambiente de execução autônomo.

* Spring MVC: A arquitetura Model-View-Controller (MVC) do Spring permite uma separação clara das camadas de aplicação, promovendo a organização do código.

* Spring Data JPA: O Spring Data JPA simplifica a interação com o banco de dados, oferecendo recursos como mapeamento objeto-relacional e consultas dinâmicas.

* Segurança com Spring Security: A integração do Spring Security garante a proteção das rotas e recursos, garantindo um ambiente seguro para os usuários.

# Banco de dados
O PostgreSQL é um sistema de gerenciamento de banco de dados relacional de código aberto, altamente respeitado por sua robustez, flexibilidade e recursos avançados. É amplamente conhecido por sua capacidade de gerenciar grandes volumes de dados, proporcionando um ambiente seguro, confiável e eficiente para aplicações empresariais.

# Integração

* API RESTful: A comunicação entre o front-end e o back-end é baseada em princípios REST, permitindo uma troca eficiente de dados.

* Banco de Dados Relacional: O sistema utiliza um banco de dados relacional para armazenar informações de produtos, vendas, clientes e outros dados relevantes.

* Integração de Terceiros: O sistema pode ser integrado com sistemas de prescrição médica, sistemas de pagamento e outros sistemas externos por meio de APIs.

# Requisitos

## Gestão de Estoque:
* Cadastro de Produtos: A aplicação deve permitir o cadastro detalhado de produtos farmacêuticos, incluindo informações como nome, descrição, código, categoria, fabricante e informações relacionadas à saúde (se necessário).
* Rastreamento de Produtos: Deve ser possível rastrear produtos por meio de números de lote, datas de validade e fornecedores.
* Controle de Quantidade: O sistema deve fornecer informações em tempo real sobre as quantidades disponíveis de cada produto em estoque.
* Alertas de Estoque Baixo: A aplicação deve enviar alertas proativos quando os níveis de estoque estiverem baixos, com base em critérios predefinidos.
* Histórico de Movimentação: Deve ser possível rastrear o histórico de movimentação de produtos, incluindo entradas, saídas e transferências.

## Registro de Vendas:
* Registro de Vendas: A aplicação deve permitir o registro detalhado das vendas, incluindo informações sobre os produtos vendidos, clientes, data e detalhes financeiros.
* Integração com Sistemas de Prescrição: Deve ser possível integrar a aplicação com sistemas de prescrição médica para verificar a venda de medicamentos sujeitos a receita e garantir conformidade legal.

## Gestão Financeira:
* Acompanhamento Financeiro: A aplicação deve permitir o registro de receitas e despesas associadas às vendas e operações do negócio.
* Relatórios Financeiros: Deve ser possível gerar relatórios detalhados de lucros, perdas e fluxo de caixa, oferecendo insights financeiros valiosos para a tomada de decisões.
