# Запуск тестов

### Запуск тестов в IDEA
Для запуска с jvm параметром надо указать в
Run - Edit Configuration для конктретного запуска

в VM options :
-ea -DTAGS=@testtag -Dautotest_target_url=http://testurl/app

### Запуск тестов через MAVEN

mvn clean install -DTAGS=@smoke -Dautotest_target_url=http://testurl/app

## Параметры запуска
* -DTAGS - указание тега теста
* -Dautotest_target_url - тестовый url