package com.example.bookapp;

import android.annotation.SuppressLint;
import android.widget.Filter;

import java.util.ArrayList;

public class FilterCategory extends Filter {

    //arraylist in which we want to search
    ArrayList<ModelCategory> filterList;
    //adapter in which filter need to be implemented
    MyAdapter adapter;

    //constructor
    public FilterCategory(ArrayList<ModelCategory> filerList, MyAdapter adapter) {
        this.filterList = filerList;
        this.adapter = adapter;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //value should not be null and empty
        if(charSequence !=null && charSequence.length() >0){
            //change to upper case, ore lower case to avoid case sensitivity
            charSequence= charSequence.toString().toUpperCase();
            ArrayList<ModelCategory> filteredModels = new ArrayList<>();
            for (int i=0; i<filterList.size();i++){
                //validate
                if(filterList.get(i).getCategory().toUpperCase().contains(charSequence)){
                    //add to filtered list
                    filteredModels.add(filterList.get(i));
                }

            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
        //apply filter changes
        adapter.list = (ArrayList<ModelCategory>)filterResults.values;

        //notify changes
        adapter.notifyDataSetChanged();

    }
}
