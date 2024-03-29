##  Task Management System 
### Система управлениями задачами ( Task Management System ) сервис позволяет пользователю создавать, редактировать и удалять задачи. Пользователь может посмотреть любую задачу, получить список задач определенного пользователя, список задач определенного исполнителя или получить список задач по фильтру, но редактировать только свои. Исполнители также могут просматривать любые задачи, но менять статус только тех задач где они являются исполнителями. К каждой задаче пользователи могут создавать, редактировать и удалять комментарии. Редактировать и удалять комментарии может только автор комментария.
Запускаем через docker compose, миграция Liquibase, Swagger UI.


## Эндпоинты

### Контроллер /auth

### Регистрация пользователя:

Сервис получает данные, проверяет, и после этого отвечает, что все данные валидны и отправляет токен.

#### Запрос 
POST /auth/reg - создание пользователя

##### Специальные заголовки:

Content-Type : application/json

##### Тело

```
{
  "name": "Иван",
  "surname": "Иванов",
  "email": "Ivan@mail.com",
  "password": "wH&bkYvkV(aD"
}
```

#### Ответ
HTTPStatus.CREATED	

##### Специальные заголовки: нет

##### Тело
```
{
  "id": 19,
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.ehwIjoxNjk4NTcy2OTg1Z9.jas6nGzHcURmxWMkoWQzwYs7jinwaq6Lpy-i6QXBbn4"
}
```


##### Возможные коды ответа:

- 201 - пользователь успешно создан
- 400 - в теле запроса ошибка
- 409 - конфликт (например, пользователь с таким email уже существует в БД)
- 500 - неизвестная ошибка сервера

Ответы в случае ошибок:

| Ошибка | Дополнительная информация                           | Ответ                                                                                                                                                               |
|--------|-----------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 400    | Ошибка в JSON                                       | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Неверная структура объекта: проверьте скобки, запятые и названия полей",  <br/>"date": "2023-09-11 19:06:04"<br/>} |
| 400    | Не передано поле Имя/пустое                         | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Имя обязательно для заполнения; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                       |
| 400    | Не передано поле Фамилия/пустое                     | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Фамилия не должен быть пустым; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                        |
| 400    | Не передано поле Password/пустое                    | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Пароль не должен быть пустым",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                           |
| 400    | Не передано поле Email/пустое                       | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Поле Email обязательно для заполнения; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                |
| 409    | Пользователь с указанным  Email <br/>уже есть в БД  | {<br/>"status": "CONFLICT",  <br/>"message": "Эта электронная почта уже существует в базе данных",  <br/>"date": "2023-09-11 19:06:04"<br/>}                        |
| 500    | нет                                                 | {<br/>"status": "INTERNAL_SERVER_ERROR",  <br/>"message": "Неизвестная ошибка сервера",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                   |


## Ограничения:

| Поле      | Ограничение                                                                          | Код ответа | Ответ                                                                                             |
|-----------|--------------------------------------------------------------------------------------|------------|----------------------------------------------------------------------------------------------------------------------------------------------|
| name      | Обязательное, не пустое, не более 50 символов                                        | 400        | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Имя должно быть не больше 50 знаков; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}           |
| surname   | Не обязательное, не более 50 символов                                                | 400        | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Фамилия должна быть не больше 50 знаков; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}       |                                      
| password  | Обязательное, не пустое, не более 50 символов                                        | 400        | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Пароль должен быть не больше 50 знаков; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}        |
| email     | Обязательное, не пустое, не более 70 символов, должно соответствовать формату Email  | 400        | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Email должен быть не больше 70 знаков; ",  <br/>"date": "2023-09-11 19:06:04"<br/>}         |

### Вход в систему ( Login ):

Сервис получает данные ( username/password ), проверяет все данные и, если пользователь с такими данными существует, отдаёт в теле ответа его id, accessToken.

#### Запрос 
POST /auth/login - логин

##### Специальные заголовки:

Content-Type : application/json

##### Тело

```
{
  "username": "Ivan@mail.com",
  "password": "wH&bkYvkV(aD"
}
```
#### Ответ
HTTPStatus.OK


##### Специальные заголовки: нет

##### Тело

```
{
  "id": 19,
  "accessToken": "eyJhbGciOiJIUzI1NiJ9.jk4NTcyNDM2XQiOjE2OTg1NzIzNzZ9.jas6nGzHcURmxWMkoWQzwYs7jinwaq6Lpy-i6QXBbn4"
}
```
##### Возможные коды ответа:

- 200 - операция прошла успешно
- 400 - в теле запроса ошибка
- 404 - пользователь с таким email не найден
- 500 - неизвестная ошибка сервера

Ответы в случае ошибок:

| Ошибка | Дополнительная информация                           | Ответ                                                                                                                                                               |
|--------|-----------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 400    | Ошибка в JSON                                       | {<br/>"status": "BAD_REQUEST",  <br/>"message": "Неверная структура объекта: проверьте скобки, запятые и названия полей",  <br/>"date": "2023-09-11 19:06:04"<br/>} |
| 404    | Пользователь с таким email не найден                | {<br/>"status": "NOT_FOUND",  <br/>"message": "Пользователь с таким email не найден",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                     |
| 500    | Нет                                                 | {<br/>"status": "INTERNAL_SERVER_ERROR",  <br/>"message": "Неизвестная ошибка сервера",  <br/>"date": "2023-09-11 19:06:04"<br/>}                                   |



      
