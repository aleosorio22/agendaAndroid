package gt.edu.umg.p2c1.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import gt.edu.umg.p2c1.R;
import gt.edu.umg.p2c1.VerActivity;
import gt.edu.umg.p2c1.entidades.Contactos;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {

    ArrayList<Contactos> listaContactos;

    public ListaContactosAdapter(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        Contactos contacto = listaContactos.get(position);
        holder.viewNombre.setText(contacto.getNombre());
        holder.viewTelefono.setText(contacto.getTelefono());
        holder.viewCorreo.setText(contacto.getCorreo_electronico());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, VerActivity.class);
                intent.putExtra("ID", contacto.getId());  // Pasar el ID del contacto
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {

        TextView viewNombre, viewTelefono, viewCorreo;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewTelefono = itemView.findViewById(R.id.viewTelefono);
            viewCorreo = itemView.findViewById(R.id.viewCorreo);
        }
    }
}
