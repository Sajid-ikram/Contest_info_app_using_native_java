package com.example.listview;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.listview.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private  ArrayList<ContestModel> localDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textName;
        private final TextView textUrl;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textName = (TextView) view.findViewById(R.id.contestName);
            textUrl = (TextView) view.findViewById(R.id.contestUrl);
        }

        public TextView getTextView() {
            return textName;
        }
    }


    public CustomAdapter( ArrayList<ContestModel> dataSet) {
        localDataSet = dataSet;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.individual_contest, viewGroup, false);

        return new ViewHolder(view);
    }


    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {


        viewHolder.getTextView().setText(localDataSet.get(position).name);
        viewHolder.textUrl.setText(localDataSet.get(position).url);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}
