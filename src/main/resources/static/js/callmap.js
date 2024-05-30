
let map;

async function initMap() {
    console.log("initMap 함수가 호출되었습니다.");
    const { Map } = await google.maps.importLibrary("maps");

    const mapElement = document.getElementById("map");
    const mapX = parseFloat(mapElement.getAttribute("data-mapx"));
    const mapY = parseFloat(mapElement.getAttribute("data-mapy"));

    map = new Map(mapElement, {
        center : {lat : 126.9765267272, lng: 37.5675596477 },
        zoom : 8,
    });
}

window.initMap = initMap;

