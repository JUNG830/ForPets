<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>로그인</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
    <link href="https://getbootstrap.com/docs/4.0/examples/signin/signin.css" rel="stylesheet" crossorigin="anonymous">
</head>

<body>
<div class="container">
    <form class="form-signin" id="signUp_form">
        <p class="text-center">
            <img src="/images/Main_1.jpg" class="img-thumbnail" style="width: 200px;" alt="이 글이 보인다면 시큐리티 설정 잘못한거임!">
        </p>
        <h2 class="form-signin-heading text-center mb-5">forPets.com</h2>

        <p>
            <label for="userId" class="sr-only">아이디</label>
            <input type="text" id="userId" name="id" class="form-control" placeholder="아이디" required="" autofocus="">
        </p>
        <p>
            <label for="password" class="sr-only">비밀번호</label>
            <input type="password" id="password" name="password" class="form-control" placeholder="비밀번호" required="">
        </p>
        <button class="btn btn-lg btn-primary btn-block" type="submit">로그인</button>
    </form>

    <form class="form-signin" method="get" action="/signup">
        <button class="btn btn-lg btn-warning btn-block" type="submit">회원가입하기</button>
    </form>

    <script>
        const form = document.getElementById('signUp_form');

        form.addEventListener('submit', e => {
            e.preventDefault();

            const data = new FormData(form);
            const param = JSON.stringify(Object.fromEntries(data));

            fetch('/login-process', {
                method: 'POST',
                body: param,
                headers: {
                    'Content-Type': 'application/json'
                }
            })
            .then(response => {
                if (response.status == 200) {
                    console.log(response.status)
                    console.log(response.data)
                    alert("로그인 성공")
                    window.location.href = '/main';
                } else {
                    console.log(response.status)
                    console.log(response.data)
                    alert("로그인 실패")
                }
            })
            .catch(error => console.log(error))
        });
    </script>
</div>
</body>
</html>