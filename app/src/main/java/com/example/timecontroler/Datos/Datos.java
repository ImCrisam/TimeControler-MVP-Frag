package com.example.timecontroler.Datos;

import android.content.Context;
import android.widget.Toast;

import com.example.timecontroler.Modelos.Registro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Datos {
    private Context ctx;

    private static final String separador = "---";
    public static final String nombreArchivo = "RegistroTimeControl.txt";

    public Datos(Context ctx) {
        this.ctx = ctx;

    }

    public List<Registro> leer() {
        String[] list = ctx.fileList();
        List<Registro> listR = new ArrayList<>();
        if (existe(list, nombreArchivo)) {
            try {
                InputStreamReader archivo = new InputStreamReader(
                        ctx.openFileInput(nombreArchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                Registro r = new Registro();
                boolean index = false;
                while (linea != null) {
                    r.setNombre(linea);
                    linea = br.readLine();
                    r.setTotal(linea);
                    linea = br.readLine();
                    index = false;
                    while (!index && linea != null) {
                        if (linea.equals(separador)) {
                            listR.add(r);
                            r = new Registro();
                            index = true;
                        } else {
                            r.addTiempo(linea);
                        }
                        linea = br.readLine();
                    }
                }
                br.close();
                archivo.close();
            } catch (IOException e) {
                return null;
            }
        } else {
            return null;
        }
        return listR;
    }

    public void grabar(List<Registro> registros) {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(ctx.openFileOutput(
                    nombreArchivo, ctx.MODE_PRIVATE));
            for (Registro regis : registros) {
                archivo.write(regis.getNombre());
                archivo.write("\n");
                archivo.write(regis.getTotal());
                archivo.write("\n");

                for (int i = 0; i < regis.getSizeTiempos(); i++) {
                    archivo.write(regis.getItem(i));
                    archivo.write("\n");
                }
            }
            archivo.write(separador);
            archivo.write("\n");

            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(ctx, "Los datos fueron grabados", Toast.LENGTH_SHORT);
        t.show();
    }


    public void clear() {
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(ctx.openFileOutput(
                    nombreArchivo, ctx.MODE_PRIVATE));
                archivo.write("");

            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }
        Toast t = Toast.makeText(ctx, "clear", Toast.LENGTH_SHORT);
        t.show();
    }

    public void agregar(Registro registros) {
        String[] list = ctx.fileList();
        if (existe(list, nombreArchivo)) {
            try {
                OutputStreamWriter archivo = new OutputStreamWriter(ctx.openFileOutput(
                        nombreArchivo, ctx.MODE_APPEND));
                archivo.append(registros.getNombre());
                archivo.append("\n");

                archivo.append(registros.getTotal());
                archivo.append("\n");

                for (int i = 0; i < registros.getSizeTiempos(); i++) {
                    archivo.append(registros.getItem(i));
                    archivo.append("\n");
                }

                archivo.append(separador);
                archivo.append("\n");
                archivo.flush();
                archivo.close();
            } catch (IOException e) {
                Toast t = Toast.makeText(ctx, "Error", Toast.LENGTH_SHORT);
            }
            Toast t = Toast.makeText(ctx, "Los datos fueron grabados", Toast.LENGTH_SHORT);
            t.show();
        } else {
            grabar((List<Registro>) registros);
        }

    }

    private boolean existe(String[] archivos, String archbusca) {
        for (int f = 0; f < archivos.length; f++)
            if (archbusca.equals(archivos[f]))
                return true;
        return false;
    }

}
