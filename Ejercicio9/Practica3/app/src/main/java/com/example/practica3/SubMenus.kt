package com.example.practica3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class SubMenus : AppCompatActivity() {
    private lateinit var tituloSubMenu: TextView
    private lateinit var llProductos: LinearLayout
    private lateinit var productos: ArrayList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sub_menus)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        aplicarAjustesDePantalla()
        inicializarUI()
        cargarProductos()
        mostrarProductos()
        configurarBotonAtras()

//        tituloSubMenu = findViewById(R.id.tituloSubMenu)
//
//
//        val submenu = intent.getStringExtra("submenu")
//        tituloSubMenu.text = submenu
//
//        productos = when (submenu) {
//            "Entrantes" -> arrayListOf(
//                Producto("Nachos", "$5.99"),
//                Producto("Croquetas", "$6.99"),
//                Producto("Pan con tomate", "$4.99"),
//                Producto("Ensalada", "$3.99"),
//                Producto("Jamón", "$11.99"),
//                Producto("Calamares", "$12.99"),
//                Producto("Oreja", "7.99"),
//                Producto("Morcilla", "$8.99"),
//                Producto("Nachos con queso", "$5.99"),
//                Producto("Bruschetta", "$4.50"),
//                Producto("Gyozas", "$6.25"),
//                Producto("Palitos de mozzarella", "$5.50"),
//                Producto("Patatas bravas", "$5.25"),
//
//                )
//
//            "Principales" -> arrayListOf(
//                Producto("Sandwich", "$4.99"),
//                Producto("Pizza", "$7.99"),
//                Producto("Hamburguesa", "$8.99"),
//                Producto("Lasagna", "$9.50"),
//                Producto("Pollo asado", "$11.99"),
//                Producto("Pasta Carbonara", "$7.99"),
//                Producto("Churrasco", "$13.50"),
//                Producto("Paella", "$12.99"),
//                Producto("Tacos de carne", "$6.99"),
//                Producto("Bistec con patatas", "$10.50"),
//                Producto("Ravioles de queso", "$8.50"),
//                Producto("Salmón a la parrilla", "$14.99")
//            )
//
//            "Bebidas" -> arrayListOf(
//                Producto("Agua", "$1.50"),
//                Producto("Refresco", "2.99"),
//                Producto("Jugo de naranja", "$2.50"),
//                Producto("Café", "$1.99"),
//                Producto("Té helado", "$2.25"),
//                Producto("Batido de fresa", "$3.50"),
//                Producto("Limonada", "$2.75"),
//                Producto("Cerveza", "$4.00"),
//                Producto("Vino tinto", "$5.99"),
//                Producto("Mojito", "$6.50")
//            )
//
//            else -> arrayListOf()
//        }
//
//        val llProductos = findViewById<LinearLayout>(R.id.llProductos)
//
//        for (producto in productos) {
//            // Inflar el layout del producto
//            val itemView = layoutInflater.inflate(R.layout.item_producto, llProductos, false)
//            // Referencias a las vistas dentro del layout inflado
//            val tvNombre = itemView.findViewById<TextView>(R.id.tvNombreProducto)
//            val tvPrecio = itemView.findViewById<TextView>(R.id.tvPrecio)
//            val btnMas = itemView.findViewById<ImageButton>(R.id.btnMas)
//            val btnMenos = itemView.findViewById<ImageButton>(R.id.btnMenos)
//            val tvCantidad = itemView.findViewById<TextView>(R.id.tvCantidad)
//
//            // Asignamos los valores del producto
//            tvNombre.text = producto.nombre
//            tvPrecio.text = producto.precio
//            tvCantidad.text = producto.cantidad.toString()
//
//            // Configurar el botón de "más"
//            btnMas.setOnClickListener {
//                producto.cantidad++
//                tvCantidad.text = producto.cantidad.toString()
//            }
//
//            // Configurar el botón de "menos"
//            btnMenos.setOnClickListener {
//                if (producto.cantidad > 0) {
//                    producto.cantidad--
//                    tvCantidad.text = producto.cantidad.toString()
//                }
//            }
//
//            // Agregar la vista del producto al LinearLayout contenedor
//            llProductos.addView(itemView)
//
//        }
    }

    /** Ajusta los márgenes de la pantalla */
    private fun aplicarAjustesDePantalla() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /** Inicializa las vistas del layout */
    private fun inicializarUI() {
        tituloSubMenu = findViewById(R.id.tituloSubMenu)
        llProductos = findViewById(R.id.llProductos)
    }

    /** Carga la lista de productos según el submenú seleccionado */
    private fun cargarProductos() {
        val submenu = intent.getStringExtra("submenu")
        tituloSubMenu.text = submenu

        productos = when (submenu) {
            "Entrantes" -> arrayListOf(
                Producto("Nachos", "$5.99"),
                Producto("Croquetas", "$6.99"),
                Producto("Pan con tomate", "$4.99"),
                Producto("Ensalada", "$3.99"),
                Producto("Jamón", "$11.99"),
                Producto("Calamares", "$12.99"),
                Producto("Oreja", "$7.99"),
                Producto("Morcilla", "$8.99"),
                Producto("Nachos con queso", "$5.99"),
                Producto("Bruschetta", "$4.50"),
                Producto("Gyozas", "$6.25"),
                Producto("Palitos de mozzarella", "$5.50"),
                Producto("Patatas bravas", "$5.25")
            )
            "Principales" -> arrayListOf(
                Producto("Sandwich", "$4.99"),
                Producto("Pizza", "$7.99"),
                Producto("Hamburguesa", "$8.99"),
                Producto("Lasagna", "$9.50"),
                Producto("Pollo asado", "$11.99"),
                Producto("Pasta Carbonara", "$7.99"),
                Producto("Churrasco", "$13.50"),
                Producto("Paella", "$12.99"),
                Producto("Tacos de carne", "$6.99"),
                Producto("Bistec con patatas", "$10.50"),
                Producto("Ravioles de queso", "$8.50"),
                Producto("Salmón a la parrilla", "$14.99")
            )
            "Bebidas" -> arrayListOf(
                Producto("Agua", "$1.50"),
                Producto("Refresco", "$2.99"),
                Producto("Jugo de naranja", "$2.50"),
                Producto("Café", "$1.99"),
                Producto("Té helado", "$2.25"),
                Producto("Batido de fresa", "$3.50"),
                Producto("Limonada", "$2.75"),
                Producto("Cerveza", "$4.00"),
                Producto("Vino tinto", "$5.99"),
                Producto("Mojito", "$6.50")
            )
            else -> arrayListOf()
        }
    }

    /** Muestra los productos en el layout */
    private fun mostrarProductos() {
        for (producto in productos) {
            val itemView = layoutInflater.inflate(R.layout.item_producto, llProductos, false)

            val tvNombre = itemView.findViewById<TextView>(R.id.tvNombreProducto)
            val tvPrecio = itemView.findViewById<TextView>(R.id.tvPrecio)
            val btnMas = itemView.findViewById<ImageButton>(R.id.btnMas)
            val btnMenos = itemView.findViewById<ImageButton>(R.id.btnMenos)
            val tvCantidad = itemView.findViewById<TextView>(R.id.tvCantidad)

            tvNombre.text = producto.nombre
            tvPrecio.text = producto.precio
            tvCantidad.text = String.format(Locale.getDefault(), "%d", producto.cantidad)



            btnMas.setOnClickListener {
                producto.cantidad++
                tvCantidad.text = String.format(Locale.getDefault(), "%d", producto.cantidad)
            }

            btnMenos.setOnClickListener {
                if (producto.cantidad > 0) {
                    producto.cantidad--
                    tvCantidad.text = String.format(Locale.getDefault(), "%d", producto.cantidad)

                }
            }

            llProductos.addView(itemView)
        }
    }

    /** Configuración del nuevo comportamiento al presionar atrás */
    private fun configurarBotonAtras() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                enviarProductosSeleccionados()
                finish() // Cierra la actividad y vuelve a la anterior
            }
        })
    }

    /** Enviar los productos seleccionados a la actividad principal */
    private fun enviarProductosSeleccionados() {
        val productosSeleccionados = productos.filter { it.cantidad > 0 }
        val resultIntent = Intent().apply {
            putExtra("selectedProducts", ArrayList(productosSeleccionados))
        }
        setResult(Activity.RESULT_OK, resultIntent)
    }

    // Al pulsar el botón atrás se envían los productos seleccionados a la actividad principal
//    override fun onBackPressed() {
//        val productosSeleccionados = productos.filter { it.cantidad > 0 }
//        val resultIntent = Intent()
//        resultIntent.putExtra("selectedProducts", ArrayList(productosSeleccionados))
//        setResult(Activity.RESULT_OK, resultIntent)
//        super.onBackPressed()
//    }
}