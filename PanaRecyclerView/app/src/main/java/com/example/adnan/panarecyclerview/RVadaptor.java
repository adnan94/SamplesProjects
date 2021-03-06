package com.example.adnan.panarecyclerview;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by adnan on 4/9/2016.
 */
public class RVadaptor extends RecyclerView.Adapter<RVadaptor.personViewHolder> implements ItemTouchHelperAdaptor {
    ArrayList<Person> list;
    CustomItemClickListener listener;

    public RVadaptor(ArrayList<Person> list, CustomItemClickListener listener) {
        this.list = list;
        this.listener = listener;

    }

    @Override

    public personViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vv = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        final personViewHolder pv = new personViewHolder(vv);
        vv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(v, pv.getAdapterPosition());
            }
        });
        return pv;
    }
    public void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, list.size());
    }
    public void addItem(Person p) {
        list.add(p);
        notifyItemInserted(list.size());
    }



//    public void swap(int firstPosition, int secondPosition){
//        Collections.swap(list, firstPosition, secondPosition);
//        notifyItemMoved(firstPosition, secondPosition);
//    }
    @Override
    public void onBindViewHolder(personViewHolder holder, final int position) {
        holder.personName.setText(list.get(position).getName());
        holder.personAge.setText(list.get(position).getAge());
        Log.d("PersonName", "" + list.get(position).getName());
        Log.d("PersonAge", "" + list.get(position).getAge());
        holder.personPhoto.setImageResource(list.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * Adaptor k andar click listner sahi approach nahi hai me tumhy ek link dunga usko follow kerna....
     * or list View ka code dikhao
     */
    public static class personViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
        CardView cv;
        TextView personName;
        TextView personAge;
        ImageView personPhoto;

        public personViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            personName = (TextView) itemView.findViewById(R.id.person_name);
            personAge = (TextView) itemView.findViewById(R.id.person_age);
            personPhoto = (ImageView) itemView.findViewById(R.id.person_photo);
        }
        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }
        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }

    }

}


