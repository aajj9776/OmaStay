
// 오늘 날짜를 기준으로 연도와 월을 구함
const currentDate = new Date();
const currentYear = currentDate.getFullYear();
const currentMonth = currentDate.getMonth() + 1; // 0부터 시작하므로 1을 더함

// select 요소 가져오기
const selectElement = document.getElementById('dateSelect');



// 5개월 전까지의 옵션 추가
for (let i = 0; i < 6; i++) {
    let year = currentYear;
    let month = currentMonth - i;

    if (month <= 0) {
        year--;
        month += 12; // 12월로 넘어감
    }

    // 월을 두 자리로 표시 (예: 01, 02, ..., 12)
    const monthString = month < 10 ? '0' + month : month;

    const option = document.createElement('option');
    option.value = `${year}-${monthString}`;
    option.textContent = `${year}-${monthString}`;
    selectElement.appendChild(option);
}