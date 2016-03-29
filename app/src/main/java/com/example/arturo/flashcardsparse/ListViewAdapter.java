package com.example.arturo.flashcardsparse;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Owner on 3/28/2016.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.FlashcardViewHolder> {

private List<FlashCard> cardListt;

    public ListViewAdapter(List<FlashCard> cardListt){
        this.cardListt=cardListt;

    }


    @Override
    public ListViewAdapter.FlashcardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.studying_cards,parent,false);
        return new FlashcardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ListViewAdapter.FlashcardViewHolder holder, int position) {
            FlashCard fc= cardListt.get(position);
        holder.vTerm.setText(fc.term);
        holder.vDefinition.setText(fc.definition);
    }

    @Override
    public int getItemCount() {
        return cardListt.size();
    }

    public static class FlashcardViewHolder extends RecyclerView.ViewHolder{

        protected TextView vTerm;
        protected TextView vDefinition;

        public FlashcardViewHolder(View v){
            super(v);
            vTerm=(TextView)v.findViewById(R.id.termC);
            vDefinition=(TextView)v.findViewById(R.id.definitionC);
        }
    }
}
