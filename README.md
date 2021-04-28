## Запуск авто-тестов
### Шаги по воспроизведению :
1. Запустить в Docker контейнеры СУБД MySQl, PostgerSQL и Node.js
1. Запустить контейнеры в терминале
``` 
docker-compose up
```
3. Запустить SUT командой
``` 
java -jar artifacts/aqa-shop.jar 
```
4. Запустить авто-тесты командой
```
gradlew clean test
```
5.Сгенерировать отчеты

``` 
gradlew allureReport
gradlew allureServe
``` 

4. Закрыть все контейнеры командой
``` 
docker-compose down 
``` 
