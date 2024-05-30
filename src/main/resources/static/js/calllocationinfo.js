
let map;

async function initMap() {
    console.log("initMap 함수가 호출되었습니다.");
    const { Map } = await google.maps.importLibrary("maps");

    const mapElement = document.getElementById("map");
    const mapX = parseFloat(mapElement.getAttribute("data-mapx"));
    const mapY = parseFloat(mapElement.getAttribute("data-mapy"));

    map = new Map(document.getElementById("map"), {
        center : {lat : mapX, lng: mapY },
        zoom : 8,
        mapId: "DEMO_MAP_ID",
    });
}

initMap();

