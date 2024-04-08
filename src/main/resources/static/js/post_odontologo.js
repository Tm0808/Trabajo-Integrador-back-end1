window.addEventListener('load', function () {

    function getDentists() {
        fetch('/odontologos')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Error: Hubo un problema en el servidor. Código de estado: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                const tableBody = document.querySelector('#dentistaTableBody');
                tableBody.innerHTML = '';

                data.forEach(dentist => {
                    const row = `
                        <tr>
                            <td>${dentist.id}</td>
                            <td>${dentist.nombre}</td>
                            <td>${dentist.apellido}</td>
                            <td>${dentist.matricula}</td>
                        </tr>
                    `;
                    tableBody.innerHTML += row;
                });
            })
            .catch(error => {
                console.error('Error al obtener la lista de odontólogos:', error);
            });
    }

    getDentists();

});


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

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/listarOdontologos.html"){
            document.querySelector(".nav .nav-item a[href='listarOdontologos.html']").classList.add("active");
        } else if (pathname === "/agregarOdontologos.html") {
            document.querySelector(".nav .nav-item a[href='agregarOdontologos.html']").classList.add("active");
        } else if (pathname === "/turnos.html") {
            document.querySelector(".nav .nav-item a[href='turnos.html']").classList.add("active");
        }
    })();
});