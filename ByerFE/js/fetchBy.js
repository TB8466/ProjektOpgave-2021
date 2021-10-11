const out = str => {console.log(str)};
const tableContent = document.getElementById("tableContent");

function getCityAPI(){
  return fetch("https://api.dataforsyningen.dk/bebyggelser").then(response => response.json());
}

function postCity(data){
  data.forEach(obj => {
    if(obj.type === "by") {
    const tr = tableContent.insertRow();
    const td = tr.insertCell();
    td.textContent = obj.kode;
    const td2 = tr.insertCell();
    td2.textContent = obj.navn;
    }
  })
}

function updateCities(){
  //out(getCityAPI())
  getCityAPI().then(postCity);
}
updateCities();
