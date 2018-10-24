package com.blue.miqdad.e_fisika.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blue.miqdad.e_fisika.Models.Book;
import com.blue.miqdad.e_fisika.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListBookAdapter extends RecyclerView.Adapter<ListBookAdapter.BookViewHolder> {

    private static final String TAG = ListBookAdapter.class.getSimpleName();
    private ArrayList<Book> bookArrayList;
    private Context context;
    private LayoutInflater inflater;

    public ListBookAdapter(Context context) {
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_item, parent, false);
        return new BookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.tvBook.setText(bookArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public void setBookArrayList(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_book) TextView tvBook;
        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
