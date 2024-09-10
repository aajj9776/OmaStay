let count = 1;
// 이거 시작날짜랑 끝나는 날짜 연도로 해서 세션으로 같이 넘겨주세요
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

    let s_count = sessionStorage.getItem('people');
    if (s_count) {
        count = parseInt(s_count);
    }

    //세션스토리지에 저자된 값이 있으면 불러오기
    let s_keyword = sessionStorage.getItem('keyword');
    if (s_keyword) {
        $("#search-accommodation").val(s_keyword);
    }

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

const keywordInput = document.getElementById('search-accommodation');
const dateInput = document.getElementById('date-range-picker-input');
const peopleInput = document.getElementById('person_count2');
const searchButton = document.getElementById('keyword_searchBtn');

searchButton.addEventListener('click', function(){
    // 검색어, 날짜, 시작 날짜, 끝 날짜, 인원을 세션스토리지에 저장
    const dateRange = dateInput.value.split(' ~ ');
    const startDate = moment(dateRange[0], 'MM/DD');
    const endDate = moment(dateRange[1].split(' ')[0], 'MM/DD');

    //localdatetime로 변환
    const formattedStartDate = startDate.toISOString();
    const formattedEndDate = endDate.toISOString();

    sessionStorage.setItem('keyword', keywordInput.value);
    sessionStorage.setItem('date', dateInput.value);
    //예약 시작 일과 끝일 저장
    sessionStorage.setItem(('startDate'), formattedStartDate);
    sessionStorage.setItem(('endDate'), formattedEndDate);
    sessionStorage.setItem('people', peopleInput.innerText);
    console.log(sessionStorage.getItem('keyword'), sessionStorage.getItem('date'), sessionStorage.getItem('people'), sessionStorage.getItem('startDate'), sessionStorage.getItem('endDate'));


    recSearch();
});

let recSearches = [];

document.addEventListener('click', (event) => {
    const deleteBtn = event.target.closest('.delete-btn');
    if (deleteBtn && deleteBtn.closest('#rec_search_content')) {
        const indexRemove = parseInt(deleteBtn.dataset.index, 10); 

        recSearches.splice(indexRemove, 1);
        sessionStorage.setItem('recSearches', JSON.stringify(recSearches));
        
        const listItemToRemove = deleteBtn.closest('#rec_search_table');
            if (listItemToRemove) { 
                listItemToRemove.remove(); 
                if (listItemToRemove.nextElementSibling) { // nextElementSibling null 체크
                    listItemToRemove.nextElementSibling.remove(); // <hr> 요소도 함께 제거
                }
            }
        }
    });

    document.addEventListener('click',(event) => {
        const allDelBtn = event.target.closest('#allDelBtn');
        if (allDelBtn) {
            const chk = confirm('정말 전부 삭제하시겠습니까?');
            if (chk) {
                recSearches = [];
                sessionStorage.setItem('recSearches', JSON.stringify(recSearches));
                const recSearchContent = document.getElementById('rec_search_content');
                recSearchContent.innerHTML = '';
            }
        }
    });
    
    window.addEventListener('load', () => {
        const keyword = sessionStorage.getItem('keyword');
    
        if (keyword && keyword.trim().length > 0) {
            recSearch();
        }
    });
    

function recSearch(){
    const keyword = sessionStorage.getItem('keyword');
    const date = sessionStorage.getItem('date');
    const people = sessionStorage.getItem('people'); 
    
    if (!keyword || keyword.trim().length === 0) {
        alert('검색어를 입력해주세요.');
        return;
    }

    const keywords = (keyword && date && people) ? `${keyword} ${date} ${people}` : null;
    console.log(keywords);

    let storedSearch = sessionStorage.getItem('recSearches');
    recSearches = storedSearch ? JSON.parse(storedSearch) : [];
   
    const extractKeyword = (searchItem) => {
        const parts = searchItem.split(/ (\d{2}\/\d{2} ~ \d{2}\/\d{2} \(\d박 \d일\)) /); 
        return parts[0];
    };

    const existingKeywords = recSearches.map(extractKeyword);

        if (keywords && existingKeywords.includes(keyword)) { 
        const indexToRemove = existingKeywords.indexOf(keyword);
        recSearches.splice(indexToRemove, 1);
        }

        if (keywords) { 
            recSearches.unshift(keywords); 
        }

    recSearches = recSearches.slice(0, 5); 
    sessionStorage.setItem('recSearches', JSON.stringify(recSearches)); 
    
    const recSearchContent = document.getElementById('rec_search_content');
    recSearchContent.innerHTML = ''; 

        for (let i = 0; i < recSearches.length; i++) {
            const parts = recSearches[i].split(/ (\d{2}\/\d{2} ~ \d{2}\/\d{2} \(\d박 \d일\)) /);
            const keyword = parts[0];
            const date = parts[1];
            const people = parts[2];
                if(keyword != null && date != null && people != null){
                recSearchContent.innerHTML += `
                <div id="rec_search_table">
                    <li class="dropdown-item flex flex-row justify-between items-center"> 
                        <div>
                            <p class="text-black font-semibold overflow-hidden max-w-[260px]">${keyword}</p> 
                            <p class="text-gray-600 text-sm">${date} 인원${people}</p>
                        </div>
                        <button class="delete-btn text-gray-500 hover:text-gray-700">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="w-6 h-6">
                                <path stroke-linecap="round" stroke-linejoin="round" d="M6 18L18 6M6 6l12 12" />
                            </svg>
                        </button>
                    </li>
                    <hr class="dropdown-divider my-2" /> 
                    </div>
                `;
            }

        }
    }