# Selenium Sandbox

## Developer environment

```
docker compose up
docker exec -it [container name] bash
cd tests/selenium-sandbox
gradle test
```

## Environment variables

- `NINEANIME_EMAIL`
- `NINEANIME_PASS`
- `CREDENTIALS_FILE`

## Credentials file

```yaml
email: EMAIL
pass: PASS
```

## Authentication

For running the tests it is required to have an account on the [](https://9anime.pe/) website.
The credentials for the registered account can be stored in a YAML file with `CREDENTIALS_FILE`
env variable set to the absolute path of the file.
It is also allowed to set the `NINEANIME_EMAIL` and `NINEANIME_PASS` env variables instead,
however, be aware that when a YAML file is given, it will overwrite the credentials.
