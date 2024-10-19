package gt.edu.umg.p2c1;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

import gt.edu.umg.p2c1.adaptadores.ListaContactosAdapter;
import gt.edu.umg.p2c1.db.DbContactos;
import gt.edu.umg.p2c1.db.DbHelper;
import gt.edu.umg.p2c1.entidades.Contactos;

public class MainActivity extends AppCompatActivity {

    Button btnCrear;
    RecyclerView listaContactos;
    ArrayList<Contactos> listaArrayContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura el Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnCrear = findViewById(R.id.btnCrear);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbHelper dbHelper = new DbHelper(MainActivity.this);
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                if (db != null) {
                    Toast.makeText(MainActivity.this, "BASE DE DATOS CREADA", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Error al crear la base de datos", Toast.LENGTH_LONG).show();
                }
            }
        });

        listaContactos = findViewById(R.id.listaContactos);
        listaContactos.setLayoutManager(new LinearLayoutManager(this));

        DbContactos dbContactos = new DbContactos(MainActivity.this);
        listaArrayContactos = dbContactos.mostrarContactos();

        ListaContactosAdapter adapter = new ListaContactosAdapter(listaArrayContactos);
        listaContactos.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuNuevo) {
            nuevoRegistro();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void nuevoRegistro() {
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}
