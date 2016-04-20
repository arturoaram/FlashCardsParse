package com.example.arturo.flashcardsparse;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 3/28/2016.
 */
public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.FlashcardViewHolder> {

private List<FlashCard> cardListt;
private Context context;
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
        if (fc.bp !=null) {

            holder.vImage.setImageBitmap(fc.bp);
            //holder.vImage.set
        }
        //holder.vImage.setImageResource();

    }

    @Override
    public int getItemCount() {
        return cardListt.size();
    }
 class FlashcardViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        protected TextView vTerm;
        protected TextView vDefinition;
        protected ImageView vImage;

        public FlashcardViewHolder(View v){
            super(v);
            context=v.getContext();
            vTerm=(TextView)v.findViewById(R.id.termC);
            vDefinition=(TextView)v.findViewById(R.id.definitionC);
            vImage = (ImageView)v.findViewById(R.id.ImageC);
            v.setClickable(true);
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            Log.e("where are you going?", "not here");
            Toast.makeText(context,"The Item Clicked is: "+getPosition(),Toast.LENGTH_SHORT).show();
            Intent i = new Intent(context, StudyCardsActivity.class);
            i.putExtra("list", (Serializable) removeBitmaps(cardListt));
            i.putExtra("index",getPosition());

            context.startActivity(i);
        }
    }

    public List<FlashCard> removeBitmaps(List<FlashCard> lFC){
        List<FlashCard> noBitmaps=new ArrayList<FlashCard>();
        FlashCard test;

        for(int i=0; i<lFC.size();i++){
            test = lFC.get(i);
            test.setBp(null);
            noBitmaps.add(test);

        }
        return noBitmaps;
    }
}
