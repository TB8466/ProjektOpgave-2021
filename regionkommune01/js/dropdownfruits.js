const out = str => console.log(str);

const fruits = ["Apple","Pear","Pineapple","Peach"];

const ddFruits = document.getElementById("ddFruits");
const pbFillFruit = document.getElementById("pbFilDropDown");

function fillDropDown(item, index){
    const el = document.createElement("option");
    el.textContent = item;
    el.value = index;
    ddFruits.appendChild(el);

}

function actionFillFruits(){
  fruits.forEach(fillDropDown)
}

function ddSelected(event){
  out(event);
  const selind = ddFruits.selectedIndex;
  out(selind);
  out(ddFruits.options[selind].textContent);
}

pbFillFruit.addEventListener("click",actionFillFruits);

ddFruits.addEventListener("change",ddSelected);
