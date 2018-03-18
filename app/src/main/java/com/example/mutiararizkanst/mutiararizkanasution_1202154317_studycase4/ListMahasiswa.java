package com.example.mutiararizkanst.mutiararizkanasution_1202154317_studycase4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import java.util.ArrayList;
public class ListMahasiswa extends AppCompatActivity {

    //Deklrasi variable
    private ListView mListView;
    private ProgressBar mProgressBar;
    private Button mStartAsyncTask;

    private String [] namaMahasiswa= {// daftar nama mahasiswa di variable array namaMahasiswa
            "lala", "po", "Dipsi", "Tingkiwingki",
            "Muti", "Tukijem", "Tukinem", "Paijo", "Tukiyem",
            "Butet", "Ucok", "Uni", "Uda", "Ajo"

    };

    private AddItemToListView mAddItemToListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);//untuk menyambungkan ke layout activity_list_mahasiswa

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);

        //Membuat progressbar visible ketika aplikasi berjalan
        mListView.setVisibility(View.GONE);


        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, new ArrayList<String>()));

        //Memulai async task ketika button diklik
        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Adapter proses dengan async task
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();
            }
        });
    }

    //Didalam class untuk proses async task
    public class AddItemToListView  extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswa.this);

        //Dipanggil di thread UI sebelum tugas dijalankan
        //Langkah ini biasanya digunakan untuk mensetup tugas, misalnya dengan menampilkan progress bar pada user interface
        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter(); //casting suggestion

            //Untuk progress dialog
            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setProgress(0);

            //Menghandle tombol cancel pada dialog
            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel Process", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mAddItemToListView.cancel(true);
                    //Menampilkan (Visible) progress bar pada layar dialog setelah diklik cancel
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialog.dismiss();
                }
            });
            mProgressDialog.show();
        }


        @Override
        protected Void doInBackground(Void... params) {
            for (String item : namaMahasiswa){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;
        }


        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);

            Integer current_status = (int)((counter/(float)namaMahasiswa.length)*100);
            mProgressBar.setProgress(current_status);

            //Set tampilan progress
            mProgressDialog.setProgress(current_status);

            //Set message berupa persentase progress
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;
        }


        @Override
        protected void onPostExecute(Void Void) {
            //Menyembunyikan progressbar
            mProgressBar.setVisibility(View.GONE);

            //Menghilangkan progress dialog
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);
        }


    }
}
