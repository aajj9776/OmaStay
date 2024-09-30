// <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.css" />
/*<script type="text/javascript" src="https://cdn.jsdelivr.net/momentjs/latest/moment.min.js"></script>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/daterangepicker/daterangepicker.min.js"></script>*/
//위에 있는거 같이 첨부해야됨
$(document).ready(function () {
    var start = $('#semiStart').val() ? moment($('#semiStart').val(), 'YYYY-MM-DDTHH:mm:ss') : moment();
    var end = $('#semiEnd').val() ? moment($('#semiEnd').val(), 'YYYY-MM-DDTHH:mm:ss') : moment();
    var peak_start = $('#peakStart').val() ? moment($('#peakStart').val(), 'YYYY-MM-DDTHH:mm:ss') : moment();
    var peak_end = $('#peakEnd').val() ? moment($('#peakEnd').val(), 'YYYY-MM-DDTHH:mm:ss') : moment();

    function setDateRangePicker(start, end) {
        var displayText = start.format('YYYY-MM-DD') + " ~ " + end.format('YYYY-MM-DD');
        $('.date-range-picker-input-semi').val(displayText);
        $('#semiStart').val(start.format('YYYY-MM-DDTHH:mm:ss'));
        $('#semiEnd').val(end.format('YYYY-MM-DDTHH:mm:ss'));
    }

    function setDateRangePicker2(peak_start, peak_end) {
        var displayText = peak_start.format('YYYY-MM-DD') + " ~ " + peak_end.format('YYYY-MM-DD');
        $('.date-range-picker-input-peak').val(displayText);
        $('#peakStart').val(peak_start.format('YYYY-MM-DDTHH:mm:ss'));
        $('#peakEnd').val(peak_end.format('YYYY-MM-DDTHH:mm:ss'));
    }

    $('.date-range-picker-semi').daterangepicker({
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
        timePicker: false,
        singleDatePicker: false,
        startDate: start,
        endDate: end
    }, function (start, end) {
        setDateRangePicker(start, end);
    });

    $('.date-range-picker-peak').daterangepicker({
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
        timePicker: false,
        singleDatePicker: false,
        startDate: peak_start,
        endDate: peak_end
    }, function (peak_start, peak_end) {
        setDateRangePicker2(peak_start, peak_end);
    });

});
