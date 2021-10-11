const out = function (str){console.log(str)};

const regionUrl = "https://api.dataforsyningen.dk/regioner";

function getAllRegionerAPI()
{
  out("Fetch region started");
  const regionAPI = regionUrl;
  return fetch(regionAPI).then(response => response.json());
}

let body;
let postRegionRequest = {
  method: "POST",
  headers: {
    "content-type": "application/json"
  },
  body: body
}

let regionJson = {
  "regionKode": "",
  "region": "",
  "regionHref": ""
}

function postRegionJson(btn) {
  const prom = getAllRegionerAPI();
  prom.then(postRegion);
}

const postRegionUrl = "http://localhost:8080/region";

function postRegion(data) {
  data.forEach(obj => {
    regionJson.regionKode = obj.kode;
    regionJson.regionNavn = obj.navn;
    regionJson.regionHref = obj.href;

    body = JSON.stringify(regionJson);
    postRegionRequest.body = body;
    fetch(postRegionUrl, postRegionRequest).catch((error) => out(error));
  })
}

const pbGetRegion = document.querySelector(".pbGet");
pbGetRegion.addEventListener("click", postRegionJson);
