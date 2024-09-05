let count = 1;

function changCount() {
    if (count < 1) count = 1;
    if (count > 10) count = 10;

    $("#person_count").text(count);
    $("#person_count2").text(count);


    dropdownElement.find("#decreaseBtn").prop('aria-disabled', count <= 1);
    dropdownElement.find("#increaseBtn").prop('aria-disabled', count >= 10);


    if (count <= 1) {
        dropdownElement.find("#decreaseBtn").addClass('opacity-50 bg-gray-800');
        dropdownElement.find("#decreaseBtn")[0].style.cursor = 'not-allowed';
    } else {
        dropdownElement.find("#decreaseBtn").removeClass('opacity-50 bg-gray-800');
        dropdownElement.find("#decreaseBtn")[0].style.cursor = 'pointer';
    }

    if (count >= 10) {
        dropdownElement.find("#increaseBtn").addClass('opacity-50 bg-gray-800');
        dropdownElement.find("#increaseBtn")[0].style.cursor = 'not-allowed';
    } else {
        dropdownElement.find("#increaseBtn").removeClass('opacity-50 bg-gray-800');
        dropdownElement.find("#increaseBtn")[0].style.cursor = 'pointer';
    }
}


$(document).ready(function() {
    dropdownElement = $('#second_people_dropdown');
    const dropdown = new bootstrap.Dropdown(dropdownElement[0]);

    changCount();

    $("#decreaseBtn").click(function(event){
        count--;
        changCount();
        console.log(count);
    });

    $("#increaseBtn").on('click', (event) => {
        count++;
        changCount();
        console.log(count);
    });

    dropdownElement.on('click', (event) => {
        const decreaseBtn = dropdownElement.find(".decreaseBtn");
        const increaseBtn = dropdownElement.find(".increaseBtn");

        if ($(event.target).is(decreaseBtn) || $(event.target).is(increaseBtn)) {
            event.stopPropagation();
            dropdown.hide();
        }
    });
    dropdownElement.on('click', function (event) {
        if ($(!event.target).is('#decreaseBtn') && $(!event.target).is('#increaseBtn')) {
            event.stopPropagation();
            dropdown.hide();
        }
    });

    dropdownElement.on('click', function (event) {
        if ($(event.target).is('#decreaseBtn') || $(event.target).is('#increaseBtn')) {
            event.stopPropagation();
            dropdown.show();
        }
    });

});