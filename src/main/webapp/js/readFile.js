function test() {
    document.writeln("erftvg")
    alert("adsfdg")
}

function readFiles(blob)
{
    let reader = new FileReader();

    reader.readAsText(blob);
    reader.onload = function () {
        document.writeln(reader.result)
    }
    reader.onerror = function () {
        document.writeln("error")
    }

    /*e = new File(blob, "name");
    file = e.target.files[0];
    alert(file.size)

    // если есть нужные объекты - то чтение файлов возможно
    if (window.FileList && window.File) {

        const file = e.target.files[0];

        const name = file.name ? file.name : 'NOT SUPPORTED';
        const type = file.type ? file.type : 'NOT SUPPORTED';
        const size = file.size ? file.size : 'NOT SUPPORTED';

        // let r =  { name, type, size };

        // console.log(r)


        // объект класса читающего файл
        const reader = new FileReader();

        // содержимое файла
        let content = file.target.result;

        let rows = content.split('\r\n');

        // выводим содержимое в консоль построчно
        document.writeln(rows)
        console.log(rows);

        // обработчик, который срабатывает при загрузке файла
        /!*reader.addEventListener('load', event => {

            // содержимое файла
            let content = event.target.result;

            let rows = content.split('\r\n');

            // выводим содержимое в консоль построчно
            console.log(rows);
        });*!/


        // читаем текстовый файл
        reader.readAsText(file);
    }*/
}