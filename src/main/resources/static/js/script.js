var btnContent = document.getElementById('menu-content')
var menuContent = document.getElementById('menu')

btnContent.addEventListener('click', function() {
  if (menuContent.style.display == 'none') {
    menuContent.style.display = 'block';
  } else {
    menuContent.style.display = 'none';
  }
})

try {
    var btnFiltro = document.getElementById('btnFiltro')
    var filtroForm = document.getElementById('filtro')
    
    btnFiltro.addEventListener('click', function() {
  if (filtroForm.style.display == 'none') {
    filtroForm.style.display = 'block';
  } else {
    filtroForm.style.display = 'none';
  }
})
} catch {
    console.log('Element(s) not found');
}

try {
    var btnLogin = document.querySelector('.btn-login')
    var loadLogin = document.querySelector('.load-login')
    
    btnLogin.addEventListener('click', function() {
      console.log('clicou btn-login')
      btnLogin.style.display = 'none';
      loadLogin.style.display = 'block';
  })
  } catch {
    console.log('Element(s) not found');
}

// Datatable
try {
  let table = new DataTable('#myTable');
  let secondTable = new DataTable('#mySecondTable');
} catch {
  console.log('Element not found')
}


