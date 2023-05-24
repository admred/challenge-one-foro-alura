# Challenge ONE | Back End | Foro Alura 

Este proyecto consiste en una **API backend** para un foro basico. 
Cada entidad tiene todas las Operaciones de un CRUD (Created, Remove, Update, Delete)


### Teconoligoas
- java 17
- Spring Boot 3
- Spring boot REST
- Mysql
- Eclipse IDE
- curl (linux)

### Estructura del proyecto
- com.alura.foro
    - ForoInicialApplication.java
- com.alura.foro.controller
    - TopicoController.java
    - UsuarioController.java
    - CursoController.java
    - RespuestaController.java
- com.alura.foro.modelo
    - Topico.java
    - Usuario.java
    - Curso.java
    - Respuesta.java
- com.alura.foro.repository
    - TopicoRepository.java
    - ControllerRepository.java
    - CursoRepository.java
    - RespuestaRepository.java
- com.alura.foro.dto.topico
    - DatosActualizarTopico.java
    - DatosDetalleTopico.java
    - DatosListadoTopico.java
    - DatosRegistroTopico.java
    - DatosRespuestaTopico.java
- com.alura.foro.dto.usuario
    - DatosActualizarUsuario.java
    - DatosRegistroUsuario.java
    - DatosRespuestaUsuario.java
- com.alura.foro.dto.curso
    - DatosActualizarCurso.java
    - DatosRegistroCurso.java
    - DatosRespuestaCurso.java
- com.alura.foro.dto.respuesta
    - DatosActualizarRespuesta.java
    - DatosRegistroRespuesta.java
    - DatosRespuestaRespuesta.java

### Como crear un topico
```
curl -v -X POST 'http://localhost:8080/topicos' -H 'content-type: application/json' -d '{"titulo":"Como crear un README","curso_id":2,"autor_id":3,"mensaje":"Lorem ipsum ..."}'| jq
Note: Unnecessary use of -X or --request, POST is already inferred.
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> POST /topicos HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> content-type: application/json
> Content-Length: 113
> 
} [113 bytes data]
* upload completely sent off: 113 out of 113 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 201 
< Location: http://localhost:8080/topicos/7
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Wed, 24 May 2023 18:47:04 GMT
< 
{ [46 bytes data]
100   153    0    40  100   113   1379   3896 --:--:-- --:--:-- --:--:--  5275
* Connection #0 to host localhost left intact
{
  "id": 7,
  "titulo": "Como crear un README"
}

```

### Como actualizar un topico
```
$ curl -v -X PUT 'http://localhost:8080/topicos' -H 'content-type: application/json' -d '{"id":7,"titulo":"Como crear un documento Markdown"}'| jq
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> PUT /topicos HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> content-type: application/json
> Content-Length: 52
> 
} [52 bytes data]
* upload completely sent off: 52 out of 52 bytes
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Wed, 24 May 2023 18:52:42 GMT
< 
{ [58 bytes data]
100   104    0    52  100    52   1130   1130 --:--:-- --:--:-- --:--:--  2260
* Connection #0 to host localhost left intact
{
  "id": 7,
  "titulo": "Como crear un documento Markdown"
}

```
#### Como obtener una lista de topicos
```
$ curl -v -X GET 'http://localhost:8080/topicos' | jq
Note: Unnecessary use of -X or --request, GET is already inferred.
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
  0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0*   Trying ::1:8080...
* Connected to localhost (::1) port 8080 (#0)
> GET /topicos HTTP/1.1
> Host: localhost:8080
> User-Agent: curl/7.74.0
> Accept: */*
> 
* Mark bundle as not supporting multiuse
< HTTP/1.1 200 
< Content-Type: application/json
< Transfer-Encoding: chunked
< Date: Wed, 24 May 2023 18:53:57 GMT
< 
{ [1107 bytes data]
100  1100    0  1100    0     0  22448      0 --:--:-- --:--:-- --:--:-- 22916
* Connection #0 to host localhost left intact
{
  "content": [
    {
      "id": 2,
      "titulo": "Como centrar un DIV",
      "fechaCreacion": "2023-05-24T13:48:50",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 7,
      "titulo": "Como crear un documento Markdown",
      "fechaCreacion": "2023-05-24T14:47:04",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 6,
      "titulo": "Lorem ipsum dolor",
      "fechaCreacion": "2023-05-24T13:48:51",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 4,
      "titulo": "Por que no se ejecuta mi Hola Mundo?",
      "fechaCreacion": "2023-05-24T13:48:51",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 5,
      "titulo": "Por que se borra todo cuando elimino un elemento?",
      "fechaCreacion": "2023-05-24T13:48:51",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 3,
      "titulo": "Que es un error 404?",
      "fechaCreacion": "2023-05-24T13:48:51",
      "status": "NO_RESPONDIDO"
    },
    {
      "id": 1,
      "titulo": "Que son las variables?",
      "fechaCreacion": "2023-05-24T13:48:50",
      "status": "NO_RESPONDIDO"
    }
  ],
  "pageable": {
    "sort": {
      "sorted": true,
      "unsorted": false,
      "empty": false
    },
    "pageNumber": 0,
    "pageSize": 10,
    "offset": 0,
    "paged": true,
    "unpaged": false
  },
  "totalPages": 1,
  "totalElements": 7,
  "last": true,
  "sort": {
    "sorted": true,
    "unsorted": false,
    "empty": false
  },
  "first": true,
  "size": 10,
  "number": 0,
  "numberOfElements": 7,
  "empty": false
}
```

### Cosas que me quedaron pendientes
- Manejo de errors
- Seguridad
