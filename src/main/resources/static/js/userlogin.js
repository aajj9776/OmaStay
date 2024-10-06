document.addEventListener("DOMContentLoaded", function() {
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

    function handleLogout() {
        // 쿠키,Storage 삭제 (로그아웃 처리)
        document.cookie = "accessToken=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;"; // 쿠키 삭제

        // 로컬스토리지에서 user와 islogin 제거
        localStorage.removeItem("user");
        localStorage.removeItem("islogin");

        location.href = "/login/logout";  // 로그아웃 경로로 이동
    }

    // 로그인 버튼을 클래스 이름으로 가져옴
    const accessToken = getCookie("accessToken");
    const loginButton = document.querySelector(".header_login_button a");
    const domesticLink = document.querySelector("a[href='/?activeSearch=domestic']");
    const loginMenuItem = document.querySelector("a[onclick='changPage(1)']");
    const guestReservationLink = document.querySelector("a[onclick='changPage(3)']");
    const ulDropdownMenu = document.querySelector(".dropdown-menu");  

    function isMyInfoItemExist() {
        return !!ulDropdownMenu.querySelector("a[href='/mypage/info']");
    }

    if (accessToken) {
        // 로그인 상태라면, 로그아웃 버튼으로 변경
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
            console.log("guestReservationLink found, modifying text and href.");
            guestReservationLink.innerText = "회원 예약조회";
            guestReservationLink.setAttribute("href", "/mypage/reservation");  // "회원 예약조회" 페이지로 이동
            guestReservationLink.removeAttribute("onclick");  // 기존 onclick 제거
        } else {
            console.log("guestReservationLink not found.");
        }


        // "국내 숙소"를 "멤버십"으로 변경하고 링크를 변경
        if (domesticLink) {
            domesticLink.innerText = "멤버십";
            domesticLink.setAttribute("href", "/mypage/member-ship");
            domesticLink.onclick = function() {
                window.location.href = "/mypage/member-ship";
  
                // 직접 경로 설정
            };
            
        }

        // "내 정보" 메뉴 항목 추가
        if (!isMyInfoItemExist()) {
        const myInfoItem = document.createElement("li");
        const myInfoLink = document.createElement("a");
        myInfoLink.classList.add("dropdown-item");
        myInfoLink.innerText = "내 정보";
        myInfoItem.appendChild(myInfoLink);
        myInfoLink.setAttribute("href", "/mypage/info");
        myInfoLink.addEventListener("click", function(e) {
            e.preventDefault();     //이유를 모르지만 이게 없으면 이동아 안됨.....
            window.location.href = "/mypage/info"; // 페이지 이동
        });
        ulDropdownMenu.insertBefore(myInfoItem, ulDropdownMenu.firstChild);
        };
    } else {
        // 비로그인 상태라면, 로그인/회원가입 버튼 유지
        loginButton.innerText = "로그인/회원가입";
        loginButton.setAttribute("href", "/login");  // 로그인 페이지로 이동

        // 비회원 예약조회는 그대로 유지
        if (guestReservationLink) {
            guestReservationLink.innerText = "비회원 예약조회";  // 기본 텍스트 유지
            guestReservationLink.setAttribute("href", "#");  // 기존 클릭 이벤트 유지
        }
        // 로그인/회원가입 버튼 유지
        loginButton.innerText = "로그인/회원가입";
        loginButton.setAttribute("href", "/login");

    }
});
