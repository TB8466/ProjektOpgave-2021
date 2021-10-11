console.log("Hej med dig");

const out = function(str){console.log(str);}


out(1+"Strings");

const divtag = document.querySelector(".divtag");
const button = document.querySelector(".butt");

divtag.innerHTML = "Dette er en div";

function createH1() {
    const element = document.createElement("h1");
    element.innerText = "dette er en h1";
    element.setAttribute("class", "h1Klasse");
    divtag.appendChild(element);
}

button.addEventListener("click",createH1);