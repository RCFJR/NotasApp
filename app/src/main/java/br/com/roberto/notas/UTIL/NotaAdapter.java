package br.com.roberto.notas.UTIL;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import br.com.roberto.notas.DAO.NotasDAO;
import br.com.roberto.notas.MODEL.NotaAdapterList;
import br.com.roberto.notas.MainActivity;
import br.com.roberto.notas.NotaActivity;
import br.com.roberto.notas.R;

import static android.support.v4.content.ContextCompat.startActivity;

public class NotaAdapter extends ArrayAdapter<NotaAdapterList> {
    private final Context context;
    private final ArrayList<NotaAdapterList> elementos;

    public NotaAdapter(Context context, ArrayList<NotaAdapterList> elementos)
    {
        super(context, R.layout.linhanota , elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linhanota, parent, false);

        final TextView nomeNota = (TextView) rowView.findViewById(R.id.texNomeNota);

        nomeNota.setText(elementos.get(position).TITULO.replace("*","'"));

        nomeNota.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), NotaActivity.class);
                i.putExtra("item_selected", nomeNota.getText().toString());
                startActivity(getContext(), i, null);
            }
        });

        return rowView;
    }


    public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l)
    {
        Intent i = new Intent(getContext(), NotaActivity.class);
        i.putExtra("item_selected", adapterView.getItemIdAtPosition(pos));
        startActivity(getContext(), i, null);
    }
}
