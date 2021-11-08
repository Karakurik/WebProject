
document.addEventListener("DOMContentLoaded", confirm_delete)

function confirm_delete() {
    var elems = document.getElementsByClassName('confirmation');
    var confirmIt = function (e) {
        if (!confirm('Вы точно хотите удалить?')) e.preventDefault();
    };
    for (var i = 0, l = elems.length; i < l; i++) {
        elems[i].addEventListener('click', confirmIt, false);
    }
}
