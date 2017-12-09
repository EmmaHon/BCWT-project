
const settings = {

  method:'POST',
  data: new FormData(document.querySelector('#loginform'))
};

const trylogin = (evt) => {

  evt.preventDefault();

  console.log("trying");

  fetch("webresources/login", settings)
  .then((response) => {
  return response.json()
  })
  .then((response) => {
      console.log(response)
  })

}

document.querySelector('#loginform').addEventListener('submit', trylogin);
