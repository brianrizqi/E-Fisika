package com.blue.miqdad.e_fisika;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.blue.miqdad.e_fisika.Adapters.ListBookAdapter;
import com.blue.miqdad.e_fisika.DB.BookHelper;
import com.blue.miqdad.e_fisika.Models.Book;
import com.blue.miqdad.e_fisika.Models.ItemClickSupport;
import com.folioreader.Config;
import com.folioreader.FolioReader;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_books)
    RecyclerView rvBooks;
    private ListBookAdapter listBookAdapter;
    private BookHelper helper;
    private ArrayList<Book> bookArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listBookAdapter = new ListBookAdapter(this);
        helper = new BookHelper(this);
        rvBooks.setLayoutManager(new LinearLayoutManager(this));

        helper.open();
        bookArrayList = helper.getBooks();
        helper.close();

        listBookAdapter.setBookArrayList(bookArrayList);
        rvBooks.setAdapter(listBookAdapter);

        ItemClickSupport.addTo(rvBooks).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                FolioReader folioReader = FolioReader.getInstance(getApplicationContext());
                Config config = new Config();
                config.setShowTts(false);
                folioReader.setConfig(config, true);
                folioReader.openBook((int) bookArrayList.get(position).getRaw_id());
            }
        });
    }
}
