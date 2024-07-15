document.addEventListener("DOMContentLoaded", function() {
    const text = "Add Activity";
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


function initAutocomplete(){
    //검색값
    const locationInput = document.getElementById('location');
    //검색값 자동완성
    const autocomplete = new google.maps.places.Autocomplete(locationInput);

    //지도 표기(기본 값 서울)
    const map = new google.maps.Map(document.getElementById('map'),{
        center : {lat : -34.397, lng: 150.644},
        zoom : 8
    });

    //마커설정
    const marker = new google.maps.Marker({
        map : map,
        draggable : true
    });

    autocomplete.addListener('place_changed', function(){
        const place = autocomplete.getPlace();
        if (!place.geometry) {
            return;
        }

        map.setCenter(place.geometry.location);
        map.setZoom(15);

        marker.setPosition(place.geometry.location);
        marker.setVisible(true);

        document.getElementById('latitude').value = place.geometry.location.lat();
        document.getElementById('longitude').value = place.geometry.location.lng();
    });

    google.maps.event.addListener(marker,'dragend', function(event){
        document.getElementById('latitude').value = place.geometry.location.lat();
        document.getElementById('longitude').value = place.geometry.location.lng();
    });

    google.maps.event.addListener(map, 'click', function(evnet){
        document.getElementById('latitude').value = place.geometry.location.lat();
        document.getElementById('longitude').value = place.geometry.location.lng();
    });

    google.maps.event.addDomListener(windwo, 'load', initAutocomplete)

}