const trylogin = (evt) => {

  evt.preventDefault();

  const muhForm = new FormData();
  const mail = document.querySelector('input[type="email"]');
  const passwd = document.querySelector('input[type="password"]');
  muhForm.append("email", mail.value);
  muhForm.append("password", passwd.value);

  const settings = {

    method:'POST',
   headers: {
    'Accept': 'application/json, application/xml, text/plain, text/html, *.*',
    'Content-Type': 'multipart/form-data'
  },
    body: muhForm
  };

  console.log("trying");

  fetch("http://10.114.32.38:8080/MediaServices/login", settings)
  .then((value) => {
  console.log(value.text());
  })
  .then((value) => {
    console.log(document.cookie);
  })

};

document.querySelector('form').addEventListener('submit', trylogin);
