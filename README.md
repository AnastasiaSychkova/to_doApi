# to_doApi
## Тестирование проекта
1. Для регистрации юзера необходимо сделать запрос через postman POST /user/registration c body:
{
    "mail": "mail",
    "password": "password",
    "role": "USER"
}
2. Добавление и изменение задач происходит через форму в браузере (для всех запросов, кроме POST /user/registration, необходима аутентификация)
