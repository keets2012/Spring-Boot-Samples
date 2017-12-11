# Spring-Boot-Samples(Spring Cloud)

## api-gateway

### 先看一下笔者已经提供的两个端点测试

#### 登录

```
method: post 
url: http://localhost:10101/login/oauth/token?grant_type=password
header:
{
	Authorization: Basic ZnJvbnRlbmQ6ZnJvbnRlbmQ=,
	Content-Type: application/x-www-form-urlencoded
}
body:
{
	username: keets,
	password: ***,
	client:frontend
}
```
与之前讲到的auth那边基本相同，只是加了网关的路由。另外，笔者额外加了client属性，作为token中的额外信息。返回如下：

```
{
    "access_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTMwMDAzNDgsIlgtQU9ITy1Vc2VySWQiOiJlNWNhZDQ1Ni04OWE1LTQ4OWUtYjMwMS03YWQ3MmM0NDUxODkiLCJ1c2VyX25hbWUiOiJld3IiLCJqdGkiOiI4MjlkYmU0MC00MTM3LTQzYjgtODhmNS0xNDg5Y2YzNDM5MDMiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbImFsbCJdfQ.-sEyK-cUxMGlPqn0SSvlkgwJv-fu-sOFkcZwilviWYI",
    "token_type": "bearer",
    "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJld3IiLCJzY29wZSI6WyJhbGwiXSwiYXRpIjoiODI5ZGJlNDAtNDEzNy00M2I4LTg4ZjUtMTQ4OWNmMzQzOTAzIiwiZXhwIjoxNTE1NTQ5MTQ4LCJYLUFPSE8tVXNlcklkIjoiZTVjYWQ0NTYtODlhNS00ODllLWIzMDEtN2FkNzJjNDQ1MTg5IiwianRpIjoiZDNlOWJhYjEtZjQ1YS00M2I2LTk3ZDEtMjBjNGIwYjExYTliIiwiY2xpZW50X2lkIjoiZnJvbnRlbmQifQ.iQnTajwngqa31bA-W9KgBqOs7iqEMN9Ruq-Y2vBldmI",
    "expires_in": 43199,
    "scope": "all",
    "X-AOHO-UserId": "e5cad456-89a5-489e-b301-7ad72c445189",
    "jti": "829dbe40-4137-43b8-88f5-1489cf343903",
    "X-AOHO-ClientId": "frontend"
}
```
#### backend demo提供了一个测试端点

```
method: get 
url: http://localhost:10101/demo/test
header:
{
	Authorization: bearereyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MTMwMDAzNDgsIlgtQU9ITy1Vc2VySWQiOiJlNWNhZDQ1Ni04OWE1LTQ4OWUtYjMwMS03YWQ3MmM0NDUxODkiLCJ1c2VyX25hbWUiOiJld3IiLCJqdGkiOiI4MjlkYmU0MC00MTM3LTQzYjgtODhmNS0xNDg5Y2YzNDM5MDMiLCJjbGllbnRfaWQiOiJmcm9udGVuZCIsInNjb3BlIjpbImFsbCJdfQ.-sEyK-cUxMGlPqn0SSvlkgwJv-fu-sOFkcZwilviWYI,
}
```

由于现在auth调用userId是hard-code，随机的userId，当请求转发到backend时，会被拒绝，你可以改写一下auth中的用户信息实现部分，以符合真实的环境。


