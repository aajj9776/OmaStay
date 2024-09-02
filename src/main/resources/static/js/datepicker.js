/*<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>*/
//위에 있는거 같이 첨부해야됨
$(document).ready(function () {
    var start = moment();
    var end = moment().add(1, 'days');

    function setDateRangePicker(start, end) {
        var nights = end.diff(start, 'days');
        var days = nights + 1;
        var displayText = start.format('MM/DD') + " ~ " + end.format('MM/DD') + " (" + nights + "박 " + days + "일)";
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
        minDate: moment(),
        maxDate: moment().add(6, 'months'),
        timePicker: false,
        singleDatePicker: false,
        startDate: start,
        endDate: end
    }, function (start, end) {
        setDateRangePicker(start, end);
    });

    setDateRangePicker(start, end);
});
$(document).ready(function() {
    var start = moment();
    var end = moment().add(1, 'days');

    function setDateRangePicker(start, end) {
        var nights = end.diff(start, 'days');
        var days = nights + 1;
        var displayText = start.format('MM/DD') + " ~ " + end.format('MM/DD') + " (" + nights + "박 " + days + "일)";
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
        minDate: moment(),
        maxDate: moment().add(6, 'months'),
        timePicker: false,
        singleDatePicker: false,
        startDate: start,
        endDate: end
    }, function(start, end) {
        setDateRangePicker(start, end);
    });

    setDateRangePicker(start, end);
});