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
