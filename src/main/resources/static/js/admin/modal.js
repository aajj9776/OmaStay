$(document).ready(function () {
    //모달 닫기
    $('#closeModal').click(function () {
        $('#modalOverlay').css('display', 'none');
    });

    // 모달 외부 클릭 방지
    $('#modalOverlay').click(function (event) {
        if (event.target === this) {
            $(this).css('display', 'none');
        }
    });
});