document.addEventListener("DOMContentLoaded", function() {
    // 로컬 스토리지에서 'user' 가져오기
    let user = JSON.parse(localStorage.getItem('user'));
    console.log("로컬 스토리지에서 가져온 'user' 값:", user);
    
    // 쿠키에서 accessToken 확인
    function getCookie(name) {
        let cookieArr = document.cookie.split(";");
        for (let i = 0; i < cookieArr.length; i++) {
            let cookiePair = cookieArr[i].split("=");
            if (name === cookiePair[0].trim()) {
                return decodeURIComponent(cookiePair[1]);
            }
        }
        return null;
    }
    
    // 로그아웃 처리
    function handleLogout() {
        // 쿠키 및 로컬 스토리지에서 정보 삭제
        document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
        localStorage.removeItem("user");
        localStorage.removeItem("islogin");
        location.href = "/login/logout";  // 로그아웃 후 이동
    }

    const accessToken = getCookie("accessToken");
    console.log("쿠키에서 'accessToken' 값:", accessToken);
    
    const loginButton = document.querySelector(".header_login_button a");
    const domesticLink = document.querySelector("a[href='/?activeSearch=domestic']");
    const loginMenuItem = document.querySelector("a[onclick='changPage(1)']");
    const guestReservationLink = document.querySelector("a[onclick='changPage(3)']");
    const ulDropdownMenu = document.querySelector(".dropdown-menu");  

    const memIdx = user ? user.id : null; // 로컬스토리지에 id 값이 있으면 가져옴
   
    if (accessToken) {
        // 로그인 상태일 경우
        loginButton.innerText = "로그아웃";
        loginButton.setAttribute("href", "#"); // 로그아웃 경로 설정
        loginButton.addEventListener("click", function(e) {
            e.preventDefault();  // 기본 링크 동작 방지
            handleLogout();  // 로그아웃 함수 호출
        });

        loginMenuItem.innerText = "로그아웃";
        loginMenuItem.onclick = function(e) {
            e.preventDefault();
            handleLogout();  // 로그아웃 함수 호출
        };

        if (guestReservationLink) {
            console.log("비회원 예약 링크 찾음, 텍스트 및 href 변경.");
            guestReservationLink.innerText = "회원 예약조회";
            guestReservationLink.onclick = function(e) {
                e.preventDefault();
                changPage(6);  // "회원 예약조회"로 이동
            };
        }

        if (domesticLink) {
            domesticLink.innerText = "멤버십";
            domesticLink.setAttribute("href", "/mypage/member-ship");
            domesticLink.onclick = function() {
                window.location.href = "/mypage/member-ship";
            };
        }

        function isMyInfoItemExist() {
            return !!ulDropdownMenu.querySelector("a[href='/mypage/info']");
        }

        if (!isMyInfoItemExist()) {
            const myInfoItem = document.createElement("li");
            const myInfoLink = document.createElement("a");
            myInfoLink.classList.add("dropdown-item");
            myInfoLink.innerText = "내 정보";
            myInfoItem.appendChild(myInfoLink);
            myInfoLink.setAttribute("href", "/mypage/info");
            myInfoLink.addEventListener("click", function(e) {
                e.preventDefault();
                window.location.href = "/mypage/info"; // 페이지 이동
            });
            ulDropdownMenu.insertBefore(myInfoItem, ulDropdownMenu.firstChild);
        };
    } else {
        // 비로그인 상태일 경우
        loginButton.innerText = "로그인/회원가입";
        loginButton.setAttribute("href", "/login");

        if (guestReservationLink) {
            guestReservationLink.innerText = "비회원 예약조회";
            guestReservationLink.setAttribute("href", "#");
        }
    }

    if (memIdx) {
        const script = document.createElement("script");
        script.src = `/js/userlogin.js?id=${memIdx}`;
        document.body.appendChild(script);
    }
});
