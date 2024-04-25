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
    console.log('Id(s) inexistente(s)');
}

// Datatable
try {
  let table = new DataTable('#myTable');
  let secondTable = new DataTable('#mySecondTable');
} catch {
  console.log('Element not found')
}


