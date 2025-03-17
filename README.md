# hubspot-integrator

Para subir a aplicação:
- Configure as variáveis de ambiente:
  HUBSPOT_CLIENT_ID="sua_client_id"
  HUBSPOT_CLIENT_SECRET="sua_client_secret"
  HUBSPOT_REDIRECT_URI="https://host/autenticacao/retorno"
- Rode os seguintes comandos:
  - mvn clean install -DskipTests
  - mvn spring-boot:run