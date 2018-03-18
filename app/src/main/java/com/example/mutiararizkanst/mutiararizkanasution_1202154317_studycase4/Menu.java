package com.example.mutiararizkanst.mutiararizkanasution_1202154317_studycase4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private Button daftar, cari;//deklarasi variabel

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        daftar = (Button)findViewById(R.id.list);
        cari = (Button)findViewById(R.id.search);


        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //membuat tindakan setelah tombol di klik
                //menampilkan pesan ketika button diklik
                Toast.makeText(Menu.this,"Anda memilih menu List Nama Mahasiswa",Toast.LENGTH_SHORT).show();
                //Intent ke aktivitas selanjutnya ketika button diklik
                Intent menu = new Intent(Menu.this,ListMahasiswa.class);
                //memulai aktifitas
                startActivity(menu);
            }
        });


        cari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//membuat tindakan setelah tombol di klik
                //menampilkan pesan ketika button diklik
                Toast.makeText(Menu.this,"Anda memilih menu Cari Gambar",Toast.LENGTH_SHORT).show();
                //Intent ke aktivitas selanjutnya setelah button diklik
                Intent menu2 = new Intent(Menu.this,CariGambar.class);
                //memulai aktifitas
                startActivity(menu2);
            }
        });
    }
}