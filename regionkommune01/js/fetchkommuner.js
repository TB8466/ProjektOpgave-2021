const kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

function getAllkommuneerAPI()
{
  out("Fetch kommune started");
  const kommuneAPI = kommuneUrl;
  return fetch(kommuneAPI).then(response => response.json());
}

const postkommuneRequest = {
  method: "POST",
  headers: {
    "content-type": "application/json"
  },
  body: body
}

const kommuneJson = {
  "kommuneKode": "",
  "kommuneNavn": "",
  "region":{
    "regionKode": ""
  },
  "kommuneHref": ""
}

function postkommuneJson(btn) {
  const prom = getAllkommuneerAPI();
  prom.then(postkommune);
}

const postkommuneUrl = "http://localhost:8080/kommune";

function postkommune(data) {
  data.forEach(obj => {
    kommuneJson.kommuneKode = obj.kode;
    kommuneJson.kommuneNavn = obj.navn;
    kommuneJson.region.regionKode = obj.region.kode;
    kommuneJson.kommuneHref = obj.href;

    body = JSON.stringify(kommuneJson);
    postkommuneRequest.body = body;
    fetch(postkommuneUrl, postkommuneRequest).catch((error) => out(error));
  })
}

const pbGetkommune = document.querySelector(".pbGetKommuner");
pbGetkommune.addEventListener("click", postkommuneJson);
