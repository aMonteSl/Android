package com.example.practica3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.Serializable


class MainActivity : AppCompatActivity() {
    private lateinit var btnEntrantes: ImageButton
    private lateinit var btnPrincipales: ImageButton
    private lateinit var btnBebidas: ImageButton
    private lateinit var btnPedidos: Button
    private val carrito = mutableListOf<Producto>()
    // Launcher para obtener el resultado de SubMenusActivity
    private val submenuLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            manejarResultadoSubmenu(result.resultCode, result.data)
        }

    // Launcher para iniciar la actividad de submenú y obtener el resultado
//    private val submenuLauncher: ActivityResultLauncher<Intent> =
//        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//            if (result.resultCode == Activity.RESULT_OK) {
//                result.data?.let { intent ->
//                    // Recuperamos la lista de productos seleccionados (que implementan Serializable)
//                    val productos = intent.getSerializableExtra("selectedProducts") as? ArrayList<Producto>
//                    productos?.let { listaProductos ->
//                        // Por ejemplo, mostramos un Toast con el número de productos seleccionados
//                        Toast.makeText(this, "Productos seleccionados: ${listaProductos.size}", Toast.LENGTH_SHORT).show()
//                        // Y mostramos en Log el nombre y cantidad de cada producto
//                        listaProductos.forEach { producto ->
//                            Log.d("MainActivity", "Producto: ${producto.nombre} - Cantidad: ${producto.cantidad}")
//                        }
//                        // Agregamos los productos seleccionados al carrito
//                        agregarProductosACarrito(listaProductos)
//                        // Opcional: mostramos un mensaje indicando el número total de ítems en el carrito
//                        Toast.makeText(this, "Total de productos en el carrito: ${carrito.size}", Toast.LENGTH_SHORT).show()
//                        for (producto in carrito){
//                            Log.d("MainActivity", "Producto En carrito: ${producto.nombre} - Cantidad: ${producto.cantidad}")
//                        }
//                    }
//                }
//            } else {
//                Toast.makeText(this, "Operación cancelada", Toast.LENGTH_SHORT).show()
//            }
//        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        aplicarAjustesDePantalla()
        inicializarUI()
        configurarBotones()



//        btnEntrantes = findViewById(R.id.btn_entrantes)
//        btnPrincipales = findViewById(R.id.btn_principales)
//        btnBebidas = findViewById(R.id.btn_bebidas)
//        btnPedidos = findViewById(R.id.btnPedido)
//
//        btnEntrantes.setOnClickListener { launchPlatoSubmenu("Entrantes") }
//        btnPrincipales.setOnClickListener { launchPlatoSubmenu("Principales") }
//        btnBebidas.setOnClickListener { launchPlatoSubmenu("Bebidas") }
//
//        btnPedidos = findViewById(R.id.btnPedido)
//        btnPedidos.setOnClickListener {
//            val intent = Intent(this, Pedidos::class.java)
//            intent.putExtra("carrito", carrito as Serializable)
//            startActivity(intent)
//        }

    }

    /** Configura el padding de la pantalla para ajustarse a los system bars **/
    private fun aplicarAjustesDePantalla() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    /** Inicializa las referencias a los botones **/
    private fun inicializarUI() {
        btnEntrantes = findViewById(R.id.btn_entrantes)
        btnPrincipales = findViewById(R.id.btn_principales)
        btnBebidas = findViewById(R.id.btn_bebidas)
        btnPedidos = findViewById(R.id.btnPedido)
    }

    /** Configura los eventos de los botones **/
    private fun configurarBotones() {
        btnEntrantes.setOnClickListener { launchPlatoSubmenu("Entrantes") }
        btnPrincipales.setOnClickListener { launchPlatoSubmenu("Principales") }
        btnBebidas.setOnClickListener { launchPlatoSubmenu("Bebidas") }

        btnPedidos.setOnClickListener {
            abrirPedidosActivity()
        }
    }

    /** Lanza la actividad de SubMenus y espera un resultado **/
    private fun launchPlatoSubmenu(submenu: String) {
        val intent = Intent(this, SubMenus::class.java)
        intent.putExtra("submenu", submenu)
        submenuLauncher.launch(intent)
    }

    /** Maneja el resultado de la actividad de submenús **/
    private fun manejarResultadoSubmenu(resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            data?.getSerializableExtra("selectedProducts")?.let { productos ->
                val listaProductos = productos as? ArrayList<Producto>
                listaProductos?.let {
                    agregarProductosACarrito(it)
                    mostrarToast("Productos en el carrito: ${carrito.size}")
                    logProductosEnCarrito()
                }
            }
        } else {
            mostrarToast("Operación cancelada")
        }
    }

    /** Agrega los productos seleccionados al carrito, fusionando cantidades **/
    private fun agregarProductosACarrito(productos: List<Producto>) {
        for (producto in productos) {
            val productoExistente = carrito.find { it.nombre == producto.nombre }
            if (productoExistente != null) {
                productoExistente.cantidad += producto.cantidad
            } else {
                carrito.add(producto)
            }
        }
    }

    /** Abre la actividad de pedidos y envía el carrito **/
    private fun abrirPedidosActivity() {
        val intent = Intent(this, Pedidos::class.java)
        intent.putExtra("carrito", carrito as Serializable)
        startActivity(intent)
    }

    /** Muestra un mensaje en pantalla **/
    private fun mostrarToast(mensaje: String) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }

    /** Muestra en Log los productos del carrito **/
    private fun logProductosEnCarrito() {
        for (producto in carrito) {
            Log.d("MainActivity", "Producto en carrito: ${producto.nombre} - Cantidad: ${producto.cantidad}")
        }
    }

//    private fun launchPlatoSubmenu(submenu: String) {
//        val indent = Intent(this, SubMenus::class.java)
//        indent.putExtra("submenu", submenu)
//        submenuLauncher.launch(indent)
//    }
//
//    // Función que añade productos al carrito, actualizando la cantidad si ya existe
//    private fun agregarProductosACarrito(productos: List<Producto>) {
//        for (producto in productos) {
//            // Se busca un producto con el mismo nombre en el carrito
//            val productoExistente = carrito.find { it.nombre == producto.nombre }
//            if (productoExistente != null) {
//                // Si ya existe, se suma la cantidad nueva a la existente
//                productoExistente.cantidad += producto.cantidad
//            } else {
//                // Si no existe, se añade el producto al carrito
//                carrito.add(producto)
//            }
//        }
//    }
}