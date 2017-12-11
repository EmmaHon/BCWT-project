
const settings = {

  method:'POST',
  headers: 'Content-Type', 'MediaType.APPLICATION_JSON; charset=utf-8',
  data: new FormData(document.querySelector('#loginform'))
};

const trylogin = (evt) => {

  evt.preventDefault();

  fetch("webresources/login/trylogin", settings)
  .then((Response) => {
      console.log(Response)
      console.log(Response.json())
      return Response.json()
      
  })
}

document.querySelector('#loginform').addEventListener('submit', trylogin);
