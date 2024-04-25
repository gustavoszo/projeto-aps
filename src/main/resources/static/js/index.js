// Precisa estar dentro do ready do JQuery, pois vai ser executado apenas quando um alert aparecer na tela
$(document).ready(function () {
  
    setTimeout(function() {
      $('.alert').fadeOut("slow", function() {
        $(this).alert('close')
      });
    }, 3000);

    // var url = '';

    // $('button[id*="btn_"]').click(function() {
    //   url = "http://localhost:8080/" + $(this).attr('id').split('_')[1];
    // })

    // $('#confirmarDeleteUsuario').click(function() {
    //   document.location.href = url;
    // })

})
