<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
    <title>SignUp</title>
</head>

<script type="text/javascript">
    function signUp() {
      var f = document.FormSignUp;
      f.submit();
    }
</script>
<body>
  <h1>회원가입!!!</h1>
  <!-- <form action="SignUp" method="post" name="FormSignUp">
    <div class="Container">
        <label for="id" >
            아이디
            <input id="id" name="id" type="text">
        </label>
        <label for="pssword">
            비밀번호
            <input id="password" name="password" type="password">
        </label>

        <input type="button" value=" 등록하기 " class="submitBtn" onclick="signUp();"/>
    </div>
  </form> -->

  <form action="SignUp" method="post">
		아이디 : <input type="text" name="id">
		비밀번호 : <input type="password" name="password">
		<button>가입하기</button>
	</form>
</body>
</html>