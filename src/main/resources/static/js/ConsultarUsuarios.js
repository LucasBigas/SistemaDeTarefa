$(document).ready(function() {
    $('.js-view').on('click', function() {
        var userId = $(this).data('id');
        // Fetch user details using the userId (e.g., via AJAX or directly from the server)
        $.ajax({
            url: '/usuario/' + userId,
            method: 'GET',
            success: function(data) {
                $('#viewNomeCompleto').text(data.nomeCompleto);
                $('#viewSexo').text(data.sexo);
                $('#viewResidencia').text(data.residencia);
                $('#viewTelefone').text(data.telefone);
                $('#viewCpf').text(data.cpf);
                $('#viewRg').text(data.rg);
                $('#viewEmail').text(data.email);
                $('#viewDataNascimento').text(data.dataNascimento);
            }
        });
    });
});