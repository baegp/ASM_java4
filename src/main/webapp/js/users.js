
document.getElementById("UsersForm").addEventListener('submit', (event) => {
    event.preventDefault();
    const txtUserName = document.querySelector('#txtUserName').value
    const txtPassWord = document.querySelector('#txtPassWord').value
    const txtEmail = document.querySelector("#txtEmail").value || ''
    const txtRole = document.querySelector('#txtRole').value || ''

    const txtId = document.querySelector("#txtId").value || null

    const errorName = document.querySelector('#error-name')
    const errorDes = document.querySelector('#error-des')

    if (txtId) {
        //case update
        const jsonObj = {
            id: Number(txtId),
            username: txtUserName,
            password: txtPassWord,
            email: txtEmail,
            role: txtRole
        }
        console.log('jsonObj', jsonObj)
        console.log('hehehe')


        fetch('/ASM_java4/AdminUsersController', {
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
            const jsonObj = {
                id: Number(txtId),
                username: txtUserName,
                password: txtPassWord,
                email: txtEmail,
                role: txtRole
            }

            fetch('/ASM_java4/AdminUsersController', {
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
)

function DeleteUser(id) {
    console.log("Id delete ", id);
    if (!Number.isNaN(id)) {
        const obj = {
            "id": id
        }
        fetch('/ASM_java4/AdminUsersController', {
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
    document.getElementById('ProductForm').reset();
}

function EditUser(id, item) {

    document.querySelector('#txtUserName').value = item.username || '';
    document.querySelector('#txtPassWord').value = item.password || '';
    document.querySelector("#txtEmail").value = item.email || '';
    document.querySelector("#txtRole").value = item.role || 0

    document.querySelector("#txtId").value = id || 0;

}

