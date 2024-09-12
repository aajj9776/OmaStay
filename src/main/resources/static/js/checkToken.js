window.onload = function () {
  //쿠키
  let token = Cookies.get("accessToken");
  console.log("Access Token:", token);
  let login = false;
  if (token != null) {
    console.log("토큰있음");
    login = true;
  } else {
    console.log("토큰없음");
    login = false;
  }
  localStorage.setItem("isLogin", login.toString());

  let userInfo = jwt_decode(token); // JWT 디코딩
  console.log(userInfo);
  if (userInfo.exp < Date.now() / 1000) {
    alert("토큰이 만료되었습니다. 다시 로그인해주세요.");
    location.href = "/login";
  }
};
