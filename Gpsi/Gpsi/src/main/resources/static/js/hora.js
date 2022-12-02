function startTime() {
    const timeR = document.getElementById('hora');
    const dateR = document.getElementById('date');

    const monthNames = ["Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
    ];


    const interval = setInterval(() => {

        const local = new Date();

        let day = local.getDate(),
            month = local.getMonth(),
            year = local.getFullYear();

        timeR.innerHTML = local.toLocaleTimeString();
        dateR.innerHTML = `${day} ${monthNames[month]} ${year}`;

    }, 1000);
}