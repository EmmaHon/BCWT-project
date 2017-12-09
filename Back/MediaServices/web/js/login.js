
const settings = {

  method:'POST',
  headers: 'Content-Type', 'MediaType.APPLICATION_JSON',
  data: new FormData(document.querySelector('#loginform'))
};

const trylogin = (evt) => {

  evt.preventDefault();

  fetch("webresources/login/trylogin", settings)
  .then((Response) => {
      return response.toString()
      
  })
}

document.querySelector('#loginform').addEventListener('submit', trylogin);
