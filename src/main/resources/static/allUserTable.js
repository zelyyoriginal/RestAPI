$(document).ready(function () {



   window.loadAllUser =  function () {

       const editTemplate = (userId) => `
        <button id="btn_edit_${userId}" type="button" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#edit_user_modal" data-user-id="${userId}">Edit</button>`;

       const deleteTemplate = (userId) => `
        <button id="btn_delete_${userId}" type="button" class="btn btn-danger btn-sm" data-bs-toggle="modal" data-bs-target="#delete_user_modal" data-user-id="${userId}">Delete</button>`;


       const tBody = $("#table_all_users tbody")
        tBody.empty();
        $.getJSON("admin/api/users", function (data) {

            data.forEach(function (user) {
            const roles=   user.roles
                    .map((role) => `<p>${role.name}</p>`)
                    .join("");


                const row = `<tr> 
                    <td>${user.id}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.userEmail}</td>
                    <td>${roles}</td>
                    <td>${editTemplate(user.id)}</td>
                    <td>${deleteTemplate(user.id)}</td>
                </tr>`;
                tBody.append(row);
            });


        })
    };
    window.loadAllUser();

})