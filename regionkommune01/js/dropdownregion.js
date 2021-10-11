const h1field = document.querySelector(".h1field");
const pbTest = document.querySelector(".pbTest");
const ddRegion = document.querySelector(".ddRegion");

function getRegioner (){
  return fetch("http://localhost:8080/regioner").then(response => response.json());
}

function postRegionDD(data){
  data.forEach(obj => {
    const el = document.createElement("option");
    el.textContent = obj.regionNavn;
    el.value = obj.regionKode;
    ddRegion.appendChild(el);
  })
}

function updateDropDown(btn){
  ddRegion.innerHTML = "";
 getRegioner().then(postRegionDD);

}

function getKommuner (){
  return fetch("http://localhost:8080/kommuner").then(response => response.json());
}

const kommuneJson2 = {
  "kommuneKode": "",
  "kommuneNavn": "",
  "region":{
    "regionKode": ""
  }
}

function postKommunerH2(data, kode){
  h1field.innerHTML = "";
    data.forEach(obj => {
      if(obj.region.regionKode == ddRegion.value) {
        kommuneJson2.kommuneNavn = obj.kommuneNavn;
        const el = document.createElement("h2");
        el.textContent = kommuneJson2.kommuneNavn;
        h1field.appendChild(el);
      }
    })
}

function updateH2(){
  getKommuner().then(postKommunerH2);
  out(getKommuner());
  out(ddRegion.value);
}

pbTest.addEventListener("click",updateDropDown);
ddRegion.addEventListener("change",updateH2);
