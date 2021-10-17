package com.Matheus.app_trabalho;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView lvclientes;
    private ArrayAdapter adapter;
    private List<Clientes> ListaDeClientes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvclientes = findViewById(R.id.lvclientes);
        carregarProdutos();
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("acao", "inserir");

                startActivity(intent);
            }
        });

        lvclientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int idclientes = ListaDeClientes.get(position).getId();
                Intent intent = new Intent(MainActivity.this, FormActivity.class);
                intent.putExtra("acao", "editar");
                intent.putExtra("idclientes", idclientes);
                startActivity(intent);
            }
        });

        lvclientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir(position);
                return true;
            }
        });

    }

    private void excluir(int posicao){
        Clientes clientes = ListaDeClientes.get(posicao);
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Remover Registro...");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Você deseja remover o cliente"+clientes.getNome()+"?");
        alerta.setNeutralButton("Cancelar", null);

        alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ClienteDAO.excluir(MainActivity.this, clientes.getId());
                carregarProdutos();
            }
        });
        alerta.show();
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        carregarProdutos();
    }

    private void carregarProdutos(){

        ListaDeClientes = ClienteDAO.getClientes(this);
        if (ListaDeClientes.size() == 0) {
            Clientes fake = new Clientes("Nenhum Registro...", "", "");
            ListaDeClientes.add(fake);
            lvclientes.setEnabled(false);
        }else {
            lvclientes.setEnabled(true);
        }

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ListaDeClientes);
        lvclientes.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}