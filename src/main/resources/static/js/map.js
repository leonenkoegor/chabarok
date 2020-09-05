function initMap() {
    let myLatlng = new google.maps.LatLng(52.345241, 31.029524);
    let mapOptions = {
        zoom: 16,
        center: myLatlng
    }

    let map = new google.maps.Map(document.getElementById("map"), mapOptions);
    let info = new google.maps.InfoWindow({
        content: '<h3 style="color:black" id="mapToastHeader">Ресторан чабарок</h3><p style="color:black" id="mapToastDesc">Мы ждём вас!</p>'
    });

    let marker = new google.maps.Marker({
        position: myLatlng,
        title: "Добро пожаловать в Чабарок!",
    });
    marker.addListener('click', function () {
        info.open(map, marker);
    })
    marker.setMap(map);
}