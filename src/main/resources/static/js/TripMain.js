document.addEventListener("DOMContentLoaded", function() {
    const text = "WanderWeave";
    const textElement = document.getElementById("animated-text");
    let index = 0;
    const typingSpeed = 150; // 타이핑 속도 (밀리초)
    const pauseDuration = 15000; // 30초 간격 (밀리초)

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


function addTrip(){
    window.location.href = "/add_trip"
}

function viewtrip(TripId){
    window.location.href = '/trip/' + TripId + '/details';
}