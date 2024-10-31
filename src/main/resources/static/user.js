$(document).ready(function () {
    loadTableUser();
    function loadTableUser() {
        const table = $("#user_table tbody");

        $.get("api/auth", function (userDetails) {
            table.empty();

            const rolesList = userDetails.roles
                .map((role) => `<p>${role.name}</p> `)
                .join("");


            const row = `<tr>
<td>${userDetails.id}</td>
<td>${userDetails.firstName}</td>
<td>${userDetails.lastName}</td>
<td>${userDetails.age}</td>
<td>${userDetails.userEmail}</td>
<td>
   ${rolesList}
</td>
</tr>`;
            table.append(row);
        });



    }
$("#list-profile-list").click(function () {
    loadTableUser()
})

})



