package gt.edu.umg.p2c1;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import gt.edu.umg.p2c1.db.DbContactos;
import gt.edu.umg.p2c1.entidades.Contactos;

public class VerActivity extends AppCompatActivity {

    EditText txtNombre, txtTelefono, txtCorreo;
    Button btnGuardar;
    Contactos contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtCorreo = findViewById(R.id.txtCorreoElectronico);
        btnGuardar = findViewById(R.id.btnGuardar);

        if (getIntent() != null && getIntent().hasExtra("ID")) {
            id = getIntent().getIntExtra("ID", 0);
        }

        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if (contacto != null) {
            txtNombre.setText(contacto.getNombre());
            txtTelefono.setText(contacto.getTelefono());
            txtCorreo.setText(contacto.getCorreo_electronico());
            btnGuardar.setVisibility(View.INVISIBLE);
            txtNombre.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtCorreo.setInputType(InputType.TYPE_NULL);
        }
    }
}
