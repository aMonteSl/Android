package com.example.practica3

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class Pedidos : AppCompatActivity() {
    private lateinit var tvTituloPedido: TextView
    private lateinit var emptyStateLayout: LinearLayout
    private lateinit var scrollViewCart: ScrollView
    private lateinit var llCartItems: LinearLayout
    private lateinit var carrito: ArrayList<Producto>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_pedidos)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        aplicarAjustesDePantalla()
        inicializarUI()
        recuperarCarrito()
        actualizarVista()

//        // Referencias a los layouts de estado vacío y de la lista
//        val tvTituloPedido = findViewById<TextView>(R.id.tvTituloPedido)
//        val emptyStateLayout = findViewById<LinearLayout>(R.id.emptyStateLayout)
//        val scrollViewCart = findViewById<ScrollView>(R.id.scrollViewCart)
//        val llCartItems = findViewById<LinearLayout>(R.id.llCartItems)
//
//        // Recuperamos el carrito enviado desde MainActivity
//        val carrito = intent.getSerializableExtra("carrito") as? ArrayList<Producto> ?: arrayListOf()
//
//        // Verificamos si el carrito está vacío o si ningún producto tiene cantidad mayor a 0
//        if (carrito.isEmpty() || carrito.none { it.cantidad > 0 }) {
//            tvTituloPedido.visibility = View.GONE
//            emptyStateLayout.visibility = View.VISIBLE
//            scrollViewCart.visibility = View.GONE
//        } else {
//            tvTituloPedido.visibility = View.VISIBLE
//            emptyStateLayout.visibility = View.GONE
//            scrollViewCart.visibility = View.VISIBLE
//        }
//
//        if (carrito.isEmpty() || carrito.all { it.cantidad == 0 }) {
//            // Si el carrito está vacío, mostramos el estado vacío
//            emptyStateLayout.visibility = View.VISIBLE
//            scrollViewCart.visibility = View.GONE
//        } else {
//            // Si hay productos, mostramos la lista
//            emptyStateLayout.visibility = View.GONE
//            scrollViewCart.visibility = View.VISIBLE
//
//            // Por cada producto del carrito se infla un layout para mostrarlo.
//            // Aquí reutilizamos el layout "item_producto.xml" que ya tienes.
//            for (producto in carrito.filter { it.cantidad > 0}) {
//                val itemView = layoutInflater.inflate(R.layout.pedidos_lista, llCartItems, false)
//                val tvDescripcion = itemView.findViewById<TextView>(R.id.tvPedidoDescripcion)
//                val tvTotal = itemView.findViewById<TextView>(R.id.tvPedidoTotal)
//
//                // Elimina caracteres no numéricos para poder convertir a Double
//                val precioStr = producto.precio.replace("[^\\d.]".toRegex(), "")
//                val precioUnitario = precioStr.toDoubleOrNull() ?: 0.0
//                val total = precioUnitario * producto.cantidad
//
//                tvDescripcion.text = "${producto.nombre} x${producto.cantidad}"
//                tvTotal.text = String.format("%.2f€", total)
//
//                llCartItems.addView(itemView)
//            }
//        }

    }

    /** Ajusta los márgenes de la pantalla para adaptarse a los system bars */
    private fun aplicarAjustesDePantalla() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /** Inicializa las vistas del layout */
    private fun inicializarUI() {
        tvTituloPedido = findViewById(R.id.tvTituloPedido)
        emptyStateLayout = findViewById(R.id.emptyStateLayout)
        scrollViewCart = findViewById(R.id.scrollViewCart)
        llCartItems = findViewById(R.id.llCartItems)
    }

    /** Recupera el carrito enviado desde MainActivity */
    private fun recuperarCarrito() {
        carrito = intent.getSerializableExtra("carrito") as? ArrayList<Producto> ?: arrayListOf()
    }

    /** Actualiza la vista según si hay productos o no */
    private fun actualizarVista() {
        val productosConCantidad = carrito.filter { it.cantidad > 0 }

        if (productosConCantidad.isEmpty()) {
            mostrarVistaVacia()
        } else {
            mostrarListaDePedidos(productosConCantidad)
        }
    }

    /** Muestra la vista de carrito vacío */
    private fun mostrarVistaVacia() {
        tvTituloPedido.visibility = View.GONE
        emptyStateLayout.visibility = View.VISIBLE
        scrollViewCart.visibility = View.GONE
    }

    /** Muestra la lista de pedidos con los productos */
    private fun mostrarListaDePedidos(productos: List<Producto>) {
        tvTituloPedido.visibility = View.VISIBLE
        emptyStateLayout.visibility = View.GONE
        scrollViewCart.visibility = View.VISIBLE

        productos.forEach { producto ->
            val itemView = layoutInflater.inflate(R.layout.pedidos_lista, llCartItems, false)
            val tvDescripcion = itemView.findViewById<TextView>(R.id.tvPedidoDescripcion)
            val tvTotal = itemView.findViewById<TextView>(R.id.tvPedidoTotal)

            val precioUnitario = producto.precio.replace("[^\\d.]".toRegex(), "").toDoubleOrNull() ?: 0.0
            val total = precioUnitario * producto.cantidad

            tvDescripcion.text = "${producto.nombre} x${producto.cantidad}"
            tvTotal.text = String.format(Locale.getDefault(), "%.2f€", total)

            llCartItems.addView(itemView)
        }
    }
}