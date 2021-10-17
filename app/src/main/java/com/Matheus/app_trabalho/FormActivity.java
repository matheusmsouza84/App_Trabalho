package com.Matheus.app_trabalho;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {


    private EditText etNome;
    private Spinner spMarcas;
    private EditText etModelos;
    private Button btnSalvar;
    private Button Voltar;
    private String acao;
    private Clientes clientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

        etNome = findViewById(R.id.etNome);
        spMarcas = findViewById(R.id.spMarcas);
        etModelos = findViewById(R.id.etModelos);
        btnSalvar = findViewById(R.id.btnSalvar);


        acao = getIntent().getStringExtra("acao");
        if (acao.equals("editar")){
           carregarForm();
        }

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { salvar(); }

        });
        Voltar = findViewById(R.id.Voltar);
        Voltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    private void carregarForm(){
        int id = getIntent().getIntExtra("idclientes", 0);
        clientes = ClienteDAO.getClienteById(this,id);
        etNome.setText(clientes.getNome());
        String[] marcas = getResources().getStringArray(R.array.marcas);
        for (int i = 1;i < marcas.length ;i++){
            if (clientes.getMarcas().equals(marcas[i])){
                spMarcas.setSelection(i);
                break;
            }
        }
        etModelos.setText(clientes.getModelos());

    }

    private void salvar(){
        String nome = etNome.getText().toString();
        String modelos = etModelos.getText().toString();

        if (nome.isEmpty() || spMarcas.getSelectedItemPosition() == 0){

            Toast.makeText(this, "Todos os campos são obrigatorios", Toast.LENGTH_LONG).show();

        }else{

            if (acao.equals("inserir")) {
                clientes = new Clientes();
            }

            clientes.setNome(nome);
            clientes.setMarcas(spMarcas.getSelectedItem().toString());
            clientes.setModelos(modelos);

            if (acao.equals("inserir")) {
                ClienteDAO.inserir(this, clientes);
                etNome.setText("");
                spMarcas.setSelection(0, true);
                etModelos.setText("");
            }else{
                ClienteDAO.editar(this, clientes);
                finish();
            }


        }

    }
}