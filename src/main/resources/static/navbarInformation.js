$(document).ready(function () {
    var email = $('#navbar_email');
    var roles = $('#navbar_roles');

    $.get("api/auth", function (data) {

        email.empty().text(data.userEmail);
        roles.empty().text(data.roles
            .map((role) => `${role.name}`)
            .join(", "))
    });


})