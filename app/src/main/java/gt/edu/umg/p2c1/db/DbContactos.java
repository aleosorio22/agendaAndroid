package gt.edu.umg.p2c1.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import gt.edu.umg.p2c1.entidades.Contactos;

public class DbContactos extends DbHelper {
    Context context;

    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String correo_electronico) {
        long id = 0;
        try {
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);

            id = db.insert(TABLE_CONTACTOS, null, values);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return id;
    }

    public ArrayList<Contactos> mostrarContactos() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Contactos> listaContactos = new ArrayList<>();
        Contactos contacto;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);

        if (cursorContactos.moveToFirst()) {
            do {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setNombre(cursorContactos.getString(1));
                contacto.setTelefono(cursorContactos.getString(2));
                contacto.setCorreo_electronico(cursorContactos.getString(3));

                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
        }
        cursorContactos.close();
        return listaContactos;
    }

    public Contactos verContacto(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst()) {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setNombre(cursorContactos.getString(1));
            contacto.setTelefono(cursorContactos.getString(2));
            contacto.setCorreo_electronico(cursorContactos.getString(3));
        }
        cursorContactos.close();
        return contacto;
    }
}
