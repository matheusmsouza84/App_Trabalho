package com.Matheus.app_trabalho;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    public static void inserir(Context context, Clientes clientes){
        ContentValues values = new ContentValues();
        values.put( "nome" , clientes.getNome() );
        values.put( "marcas" , clientes.getMarcas() );
        values.put( "modelos" , clientes.getModelos() );

        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.insert("clientes", null , values);
    }


    public static void editar(Context context, Clientes cliente){
        ContentValues values = new ContentValues();
        values.put("nome", cliente.getNome());
        values.put("marcas", cliente.getMarcas());
        values.put("modelos", cliente.getModelos());

        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.update("clientes", values, "id =" + cliente.getId(), null);
    }

    public static void excluir(Context context, int idClientes){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getWritableDatabase();

        db.delete("clientes", "id =" + idClientes, null);
    }
    public static List<Clientes> getClientes(Context context){
        List<Clientes> lista = new ArrayList<>();

        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM clientes ORDER BY nome", null);

        if(cursor.getCount() > 0){

            cursor.moveToFirst();

            do{

                Clientes cliente = new Clientes();
                cliente.setId(cursor.getInt(0));
                cliente.setNome(cursor.getString(1));
                cliente.setMarcas(cursor.getString(2));
                cliente.setModelos(cursor.getString(3));
                lista.add(cliente);

            }while(cursor.moveToNext());
        }
        return lista;
    }
    public static Clientes getClienteById(Context context, int idClientes){
        Conexao conn = new Conexao(context);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM clientes WHERE id = " +idClientes, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            Clientes cliente = new Clientes();
            cliente.setId( cursor.getInt(0));
            cliente.setNome(cursor.getString(1));
            cliente.setMarcas(cursor.getString(2));
            cliente.setModelos(cursor.getString(3));
                return cliente;
            }else{
            return null;
        }
    }
}
