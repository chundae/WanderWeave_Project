document.addEventListener("DOMContentLoaded", function() {
    const text = "Trip";
    const textElement = document.getElementById("animated-text");
    let index = 0;
    const typingSpeed = 150; // 타이핑 속도 (밀리초)
    const pauseDuration = 30000; // 30초 간격 (밀리초)

    function typeText() {
        if (index < text.length) {
            textElement.innerHTML += text.charAt(index);
            index++;
            setTimeout(typeText, typingSpeed);
        } else {
            setTimeout(() => {
                textElement.innerHTML = ''; // 텍스트 초기화
                index = 0; // 인덱스 초기화
                typeText(); // 타이핑 다시 시작
            }, pauseDuration);
        }
    }

    typeText(); // 첫 타이핑 시작
});

//액티비티 추가 페이지 이동
function addActivity(TripId) {
    window.location.href = '/activity/' + TripId + '/add';
}

//액티비티 디테일페이지 이동
function viewActivity(ActivityId){
    window.location.href = '/activity/' + ActivityId + '/detail';
}

//여행 수정 페이지 이동
function editTrip(TripId){
    window.location.href = '/trip/' + TripId + '/edit'
}

function delTrip(TripId){
    window.location.href = '/delete/' + TripId + '/trip';
}

function LikeActivity(ActivityId){
    window.location.href = '/activity/' + ActivityId + '/Like';
}

//구글 맵 스크립

window.onload = function(){
    //활동항목 로딩
    document.querySelectorAll('.activity-list li').forEach(function(activityItem){
        //활동항목에서 좌표 가져오기
        var latitude = parseFloat(activityItem.querySelector('#latitude').value);
        var longitude = parseFloat(activityItem.querySelector('#longitude').value);

        var mapElement = activityItem.querySelector('.map');

        //지도 위치 설정
        var map = new google.maps.Map(mapElement,{
            center : {lat : latitude, lng : longitude},
            zoom : 12
        });

        //마커 생성
        var marker = new google.maps.Marker({
            position : {lat:latitude, lng:longitude},
            map : map
        });
    });
};