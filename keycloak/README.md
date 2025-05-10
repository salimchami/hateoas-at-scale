# Keycloak 

## Export realm

```shell
docker exec -it hateoasatscale-keycloak sh -c \
  "cp -rp /opt/keycloak/data/h2 /tmp; \
  /opt/keycloak/bin/kc.sh export --log-level info --file /opt/keycloak/data/import/keycloak-configuration.json --realm hateoasatscale --users same_file --db dev-file --db-url 'jdbc:h2:file:/tmp/h2/keycloakdb;NON_KEYWORDS=VALUE' --http-management-port 9001"
```

```shell
  docker cp hateoasatscale-keycloak:/opt/keycloak/data/import/keycloak-configuration.json .
```
