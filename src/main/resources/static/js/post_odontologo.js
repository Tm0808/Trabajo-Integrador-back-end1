//window.addEventListener('load', function () {
//
//    const formulario = document.querySelector('#add_new_dentist');
//
//    formulario.addEventListener('submit', function (event) {
//
//        const formData = {
//            nombre: document.querySelector('#nombre').value,
//            apellido: document.querySelector('#apellido').value,
//            matricula: document.querySelector('#matricula').value,
//
//        };
//        const url = '/odontologos';
//        const settings = {
//            method: 'POST',
//            headers: {
//                'Content-Type': 'application/json',
//            },
//            body: JSON.stringify(formData)
//        }
//        fetch(url, settings)
//                    .then(response => response.json())
//                    .then(data => {
//                         let successAlert = '<div class="alert alert-success alert-dismissible">' +
//                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                             '<strong></strong> Odontólogo agregado </div>'
//
//                         document.querySelector('#response').innerHTML = successAlert;
//                         document.querySelector('#response').style.display = "block";
//                         resetUploadForm();
//
//                    })
//                    .catch(error => {
//                            let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
//                                             '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
//                                             '<strong> Error intente nuevamente</strong> </div>'
//
//                              document.querySelector('#response').innerHTML = errorAlert;
//                              document.querySelector('#response').style.display = "block";
//                             resetUploadForm();})
//            });
//        function resetUploadForm(){
//                document.querySelector('#nombre').value = "";
//                document.querySelector('#apellido').value = "";
//                document.querySelector('#matricula').value = "";
//
//            }
//
//            (function(){
//                let pathname = window.location.pathname;
//                if(pathname === "/"){
//                    document.querySelector(".nav .nav-item a:first").addClass("active");
//                } else if (pathname == "/dentistList.html") {
//                    document.querySelector(".nav .nav-item a:last").addClass("active");
//                }
//            });
//        });
window.addEventListener('load', function () {

    const formulario = document.querySelector('#add_new_dentist');

    formulario.addEventListener('submit', function (event) {

        const formData = {
            nombre: document.querySelector('#add_nombre').value,
            apellido: document.querySelector('#add_apellido').value,
            matricula: document.querySelector('#add_matricula').value
        };

        const url = '/odontologos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        };

        fetch(url, settings)
        .then(response => {
            if (!response.ok) {
                throw new Error(`Error: Hubo un problema en el servidor. Código de estado: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            let successAlert = '<div class="alert alert-success alert-dismissible">' +
                               '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                               '<strong>Odontólogo agregado:</strong> El odontólogo ha sido agregado correctamente.</div>';

            document.querySelector('#add_response').innerHTML = successAlert;
            document.querySelector('#add_response').style.display = "block";
            resetUploadForm();
        })
        .catch(error => {
            let errorAlert;
            if (error.response && error.response.status === 400) {
                console.log(error.response)
                errorAlert = `<div class="alert alert-danger alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <strong>Error:</strong> ${error.response.data.message}
                              </div>`;
            } else {
                console.log(error.response)
                errorAlert = `<div class="alert alert-danger alert-dismissible">
                                <button type="button" class="close" data-dismiss="alert">&times;</button>
                                <strong>Error:</strong> ${error.message}</div>`;
            }

            document.querySelector('#add_response').innerHTML = errorAlert;
            document.querySelector('#add_response').style.display = "block";
            resetUploadForm();
            getDentists();
        });

        event.preventDefault();

    });


    function resetUploadForm(){
        document.querySelector('#add_nombre').value = "";
        document.querySelector('#add_apellido').value = "";
        document.querySelector('#add_matricula').value = "";

    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            document.querySelector(".nav .nav-item a:first").classList.add("active");
        } else if (pathname == "/odontologos.html") {
            document.querySelector(".nav .nav-item a:last").classList.add("active");
        }
    })();
});