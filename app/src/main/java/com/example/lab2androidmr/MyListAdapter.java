package com.example.lab2androidmr;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.MyViewHolder> {

    private List<Integer> mListaOcen;
    private Activity mActivity;
    //private Context context;

    public MyListAdapter(Activity activity, List<Integer> listaOcen){
        mActivity = activity;
        this.mListaOcen = listaOcen;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rowRootView = mActivity.getLayoutInflater().inflate(R.layout.my_row,parent,false);
        MyViewHolder myAdapterViewHolder= new MyViewHolder(rowRootView);
        return myAdapterViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //ModelOceny model = mListaOcen.get(position);
        //holder.ocenaGroup.check(model.getSelectedId());
//******************coś działa
//        holder.ocenaGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup radioGroup, int i) {
//                model.setSelectedId(i);
//                switch(i){
//                    case R.id.radio2:
//                        //Toast.makeText(context, "Wybrane 2" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                        model.setSelectedId(i);
//                        model.setOcena(2);
//                        break;
//                    case R.id.radio3:
//                        model.setSelectedId(i);
//                        //Toast.makeText(context, "Wybrane 3" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                        model.setOcena(3);
//                        break;
//                    case R.id.radio4:
//                        model.setSelectedId(i);
//                        //Toast.makeText(context, "Wybrane 4" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                        model.setOcena(4);
//                        break;
//                    case R.id.radio5:
//                        model.setSelectedId(i);
//                        // Toast.makeText(context, "Wybrane 5" + holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
//                        model.setOcena(5);
//                        break;
//                }
//            }
//        });
        //*********************************

        int value = mListaOcen.get(position);
        holder.ocenaGroup.setTag(position);
        switch (value) {
            case 2:
                holder.ocenaGroup.check(R.id.radio2);
                break;
            case 3:
                holder.ocenaGroup.check(R.id.radio3);
                break;
            case 4:
                holder.ocenaGroup.check(R.id.radio4);
                break;
            case 5:
                holder.ocenaGroup.check(R.id.radio5);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + value);
        }


    }

    @Override
    public int getItemCount() {
        return mListaOcen.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements RadioGroup.OnCheckedChangeListener {

        TextView mTextSub;
        Button mAVGButton;
        RadioGroup ocenaGroup;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            ocenaGroup = itemView.findViewById(R.id.radioGroup1);
            mAVGButton = itemView.findViewById(R.id.AvgButton);
            mTextSub = itemView.findViewById(R.id.IDTextSub);
            ocenaGroup.setOnCheckedChangeListener(this);

        }

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            RadioButton checkedRadioButton = (RadioButton)radioGroup.findViewById(i);
            boolean isChecked = checkedRadioButton.isChecked();
            if (isChecked){
                int value = Integer.parseInt(checkedRadioButton.getText().toString());
                String valueString = radioGroup.getTag().toString();
                int positionNumber = Integer.parseInt(valueString);
                mListaOcen.set(positionNumber,value);
            }
        }
    }
}
