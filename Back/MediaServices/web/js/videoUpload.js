const uploadMedia = (evt) => {

  evt.preventDefault();

// select input element where type is file
    const input = document.querySelector('input[type="file"]');
    const info = document.querySelectorAll('input[type="text"]');
    const data = new FormData();

    data.append('video', input.files[0]);
    data.append('videotitle', info[0].value);
    data.append('videodescr', info[1].value);
    // make an object for settings
    const settings = {
            method: 'POST',
            body: data
        };
    // initiate fetch. In this example the server responds with json.
    // Response could also be text. Then you would use response.text()
    fetch('http://10.114.32.38:8080/MediaServices/upload', settings).then((response) => {
        return response.json();
    }).then((json) => {
        console.log(json.test)
    });
}

document.querySelector("form").addEventListener('submit', uploadMedia);
