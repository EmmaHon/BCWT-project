
const settings = {

  method:'PUT',
  body: new FormData(document.querySelector('form'))
};

const trylogin = (evt) => {

  evt.preventDefault();

  console.log("trying");

  fetch("http://10.114.32.38:8080/MediaServices/webresources/login/trylogin", settings)
  .then((value) => {
  console.log(value);
  })
  .then((value) => {})

}

document.querySelector('form').addEventListener('submit', trylogin);
