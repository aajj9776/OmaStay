$(document).ready(function () {
    var start = moment();
    var end = moment().add(1, 'days');

    let startDate = sessionStorage.getItem('startDate');
    let endDate = sessionStorage.getItem('endDate');


    if (startDate && endDate) {
        start = moment(startDate);
        end = moment(endDate);

    }

    function setDateRangePicker(start, end) {
        var nights = end.diff(start, 'days');
        var days = nights + 1;
        var displayText = start.format('MM/DD') + " ~ " + end.format('MM/DD') + " (" + nights + "박 " + days + "일)";
        let date = sessionStorage.getItem('date');


        $('#date-range-picker-input').val(displayText);

        // 로컬 날짜 형식으로 값을 설정하는 부분
        $('#check-in').val(start.format('YYYY-MM-DD'));
        $('#check-out').val(end.format('YYYY-MM-DD'));
    }

    $('#date-range-picker').daterangepicker({
        locale: {
            "separator": " ~ ",
            "format": 'MM/DD',
            "applyLabel": "확인",
            "cancelLabel": "취소",
            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        },
        showDropdowns: true,
        autoApply: true,
        /* 실제 실행시 이거 각주 제거 */
        /*minDate: moment(),
        maxDate: moment().add(6, 'months'),*/
        timePicker: false,
        singleDatePicker: false,
        startDate: start,
        endDate: end
    }, function (start, end) {
        setDateRangePicker(start, end);
    });

    setDateRangePicker(start, end);

});
