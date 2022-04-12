// --------------- hien thi menu 2 cap ---------------------
function showMenu2() {
    let node = document.getElementById("menu2-dssp");
    if (node.style.display == "none") {
        node.style.display = "block";
    } else {
        node.style.display = "none";
    }
}



// ----------------- show search------------------
let modalBtn = document.getElementById("popup-btn");
let modal = document.querySelector(".popup");
let closeBtn = document.querySelector(".close-btn");
modalBtn.onclick = function () {
    modal.style.display = "block"
}
closeBtn.onclick = function () {
    modal.style.display = "none"
}
window.onclick = function (e) {}


// ---------------- tim kiem ---------------
/**
 * 
 * @param {[]} arr 
 * @param {string} ten 
 * @returns 
 */

function clickTimKiem() {
    let nodeSearch = document.getElementById('search')
    let arrKq = timKiem(searchSp, nodeSearch.value)
    modal.style.display = "none";
    xuatGiaoDien(arrKq, "xuatSanPham");
}


// ------------------- xuat giao dien -------------------
/**
 * 
 * @param {[]} arr 
 * @param {String} idnode 
 */



