const boxImgA = document.getElementById('box-a-img');
const boxTempA = document.getElementById('box-a-temp');

fetch("https://api.openweathermap.org/data/2.5/weather?q=Tsarevo&appid=8dd1b8c6c70655b59ef4f75b4d9fb753")
    .then(data => data.json())
    .then(info => {
        boxTempA.innerText = Math.round(info.main.temp - 273.15);
    })
