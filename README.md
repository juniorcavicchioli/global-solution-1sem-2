# IFEED

Ifeed é ~~será~~ um aplicativo para direcionar doações. Utilizando ~~um dia~~ de aprendizado de máquina para, conforme recebermos dados atualizados do site [SISVAN](http://sisaps.saude.gov.br/sisvan/relatoriopublico/index), definirmos qual estado está atualmente em uma situação mais crítica de saúde nutricional e recomendar doações para uma instiuição que trabalhe nesse estado.


Para agilizar a navegação pelos arquivos, decidi mapear onde se encontram os pontos importantes pedidos nas entregas de cada um.

- [Enterprise Application Development](#enterprise-application-development)
- [Digital Business Enablement](#digital-business-enablement)
- [DevOps Tools e Cloud Computing](#devops-tools-e-cloud-computing)


## Enterprise Application Development

- [Mapeamento Objeto Relacional JPA e Hibernate](core/src/main/java/br/com/fiap/global/core/models);
- [Diagrama de Classes](diagramas/diagrama-de-classes.jpg) - também se encontra no [README](core) da API;
- Métodos utilizando JPQL:
  - [findByUsuario e findByInstituicao](core/src/main/java/br/com/fiap/global/core/repository/ArrecadacaoRepository.java) para arrecadações;
  - [findByEmail](core/src/main/java/br/com/fiap/global/core/repository/UsuarioRepository.java) para usuários;
  - [findByName](core/src/main/java/br/com/fiap/global/core/repository/InstituicaoRepository.java) para instituições;
- [Link](https://www.youtube.com/watch?v=Gng1gJKfpm0) para o tutorial de instalação e testes.
- [README](core) com a documentação da API.

## Digital Business Enablement

A API está na pasta [`core`](core).
- [Link](https://www.youtube.com/watch?v=Gng1gJKfpm0) para o tutorial de instalação e testes.

## DevOps Tools e Cloud Computing

- [docker-compose.yml](docker-compose.yml)
- [dockerfile](core/Dockerfile) da API

