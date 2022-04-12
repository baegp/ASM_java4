
document.getElementById("categoryForm1").addEventListener('submit', (event) => {
    event.preventDefault();
    const txtName = document.querySelector('#txtName').value
    const txtDescription = document.querySelector('#txtDescription').value
    const txtImage = document.querySelector("#image").value || ''
    const txtId = document.querySelector("#txtId").value || null
    const errorName = document.querySelector('#error-name')
    const errorDes = document.querySelector('#error-des')

    if (txtId) {
        //case update
        const jsonObj = {
            id: Number(txtId),
            name: txtName,
            description: txtDescription,
            image: txtImage
        }
        console.log('jsonObj', jsonObj)
                console.log('hehehe')

        fetch('/ASM_java4/AdminCategoryController', {
            method: 'put',
            headers: {
                'Accept': 'application/json, text/plain, */*',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(jsonObj)
        })
                .then(function (response) {
                    console.log('response', response)
                    return response.json();
                })
    } else {
        //case post
        if (txtName === '') {
            errorName.innerHTML = "name required"
        }
        if (txtDescription === '') {
            errorDes.innerHTML = "name required"
        } else {
            const jsonObj = {
                name: txtName,
                description: txtDescription,
                image: txtImage
            }

            fetch('/ASM_java4/AdminCategoryController', {
                method: 'post',
                headers: {
                    'Accept': 'application/json, text/plain, */*',
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(jsonObj)
            })
                    .then(function (response) {
                        console.log('response', response);
                        return response.json();
                    });
        }
    }
}
)

function DeleteCategory(id) {
    console.log("Id delete ", id);
    if (!Number.isNaN(id)) {
        const obj = {
            "id": id
        }
        fetch('/ASM_java4/AdminCategoryController', {
            method: 'delete',
            headers: {
                'Accept': 'application/json, text/plain, /',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(obj)
        })
                .then(function (response) {
                    console.log('response', response)
                    return response.json();
                })
                .then(function (result) {
                    $.toast({
                        heading: 'Success',
                        text: result.message,
                        position: 'bottom-right',
                        showHideTransition: 'slide',
                        icon: 'success'
                    })
                    setTimeout(() => {
                        location.reload();
                    }, 2000)

                })
                .catch(function (error) {
                    //failed
                    console.log('Request failed', error);
                })
    }
}

function resetForm() {
    document.getElementById('categoryForm1').reset();
}
function EditCategory(id, item) {
   
    document.querySelector('#txtName').value = item.name || '';
    document.querySelector('#txtDescription').value = item.description || '';
    document.querySelector("#image").value = item.image || '';
    document.querySelector("#txtId").value = id || 0;
}

//function clickCloseAdd() {
//    document.getElementById('categoryForm1').style.display = 'none';
//}