package com.example.practica4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ContactAdapter.OnContactClickListener {

    private lateinit var toolbar: MaterialToolbar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ContactAdapter
    private val contacts = mutableListOf<Contact>()
    private lateinit var toggle: ActionBarDrawerToggle

    // Registro para recibir el nuevo contacto desde CreateContactActivity
    private val createContactLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val newContact = data?.getSerializableExtra("new_contact") as? Contact
            if (newContact != null) {
                contacts.add(newContact)
                adapter.notifyItemInserted(contacts.size - 1)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configurar Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Configurar DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        navView.setNavigationItemSelectedListener { menuItem ->
            drawerLayout.closeDrawers()
            when(menuItem.itemId) {
                R.id.nav_item_one -> Log.d("Drawer", "Se pulsó Item 1")
                R.id.nav_item_two -> Log.d("Drawer", "Se pulsó Item 2")
                R.id.nav_item_three -> Log.d("Drawer", "Se pulsó Item 3")
                R.id.nav_item_four -> Log.d("Drawer", "Se pulsó Item 4")
                R.id.nav_item_five -> Log.d("Drawer", "Se pulsó Item 5")
                else -> Log.d("Drawer", "Otro item")
            }
            true
        }

        // Configurar RecyclerView
        recyclerView = findViewById(R.id.recyclerViewContacts)
        adapter = ContactAdapter(contacts, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Configurar FAB para lanzar CreateContactActivity
        val fab = findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, CreateContactActivity::class.java)
            createContactLauncher.launch(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_clear -> {
                contacts.clear()
                adapter.notifyDataSetChanged()
                Toast.makeText(this, "Lista de contactos vaciada", Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.action_info -> {
                Toast.makeText(this, "Autor: Adrián Montes Linares", Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onContactClick(contact: Contact) {
        val intent = Intent(this, DetallesContactoActivity::class.java)
        intent.putExtra("contact", contact)
        startActivity(intent)
    }

}