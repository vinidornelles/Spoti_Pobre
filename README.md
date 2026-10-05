# SpotiPobre

Trabalho 1: sistema web de músicas feito com Java, Javalin, Mustache e PostgreSQL (JDBC).

## Funcionalidades

- Criar playlist (pública ou privada) e definir o usuário dono: `/playlists/nova`
- Dashboard com os álbuns e suas músicas: `/dashboard`
- Registrar a reprodução de uma música pelo botão "Ouvir": `/reproduzir`
- Cadastro de usuários, artistas, gêneros e álbuns

## Como rodar

1. Criar o banco de dados:

   ```
   psql -U postgres -f spoti_pobre.sql
   ```

2. Conferir o usuário e a senha do banco em `spoti_pobre/src/main/java/persistencia/ConexaoPostgreSQL.java`.

3. Rodar o projeto:

   ```
   cd spoti_pobre
   mvn compile exec:java "-Dexec.mainClass=apresentacao.Main"
   ```

4. Abrir http://localhost:7070 no navegador.

## Arquivos

- `spoti_pobre/`: projeto Maven com o código
- `spoti_pobre.sql`: script que cria o banco e insere os dados
- `spotify_pobre.dia`: modelagem do banco
