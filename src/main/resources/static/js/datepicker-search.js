// <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
/*<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>*/
//위에 있는거 같이 첨부해야됨
$(document).ready(function () {
    var start = moment();
    var end = moment();

    function setDateRangePicker(start, end) {
        var displayText = start.format('YYYY-MM-DD') + " ~ " + end.format('YYYY-MM-DD');
        $('#date-range-picker-input').val(displayText);
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
        linkedCalendars: false,
        minDate: moment().add(-36, 'months'),
        maxDate: moment().endOf('year'),
        timePicker: false,
        singleDatePicker: false,
        startDate: start,
        endDate: end
    }, function (start, end) {
        setDateRangePicker(start, end);
    });

});
