package in.technicalkeeda.coronavirusliveupdate;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


class CoronaVirusAdapter extends RecyclerView.Adapter<CoronaVirusAdapter.CoronaVirusAdapterholder> implements Filterable {


     CoronaVirusAdapter() {

    }
    public CoronaVirusAdapter(List<CoronaVirusData> product) {
        this.product = product;
        this.allproduct=new ArrayList<>(product);
    }

    public List<CoronaVirusData> getProduct() {
        return product;
    }

    public void setProduct(List<CoronaVirusData> product) {
        this.product = product;
    }

    private List<CoronaVirusData> product=new ArrayList<>();
    List<CoronaVirusData> allproduct=new ArrayList<>();

    @NonNull
    @Override
    public CoronaVirusAdapter.CoronaVirusAdapterholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.coronavirusdata,parent,false);
        return new CoronaVirusAdapter.CoronaVirusAdapterholder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull CoronaVirusAdapter.CoronaVirusAdapterholder holder, int position) {
        final CoronaVirusData currentnote=product.get(position);
        holder.Death.setText(currentnote.getTotalDeath()+"\nTotal Deads");
        holder.Recoved.setText(currentnote.getTotalRecovered()+"\nTotal Recovered");
        holder.CountryName.setText(currentnote.getCountryName());
        holder.TotalCase.setText(currentnote.getTotalCases()+"\nTotal Cases");
        holder.TodayDeads.setText(currentnote.getTodaydeads()+"\nToday Deaths");
        holder.TodayCases.setText(currentnote.getTodaycases()+"\nToday Cases");
        URL url = null;

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }

    @Override
    public int getItemCount() {
        return product.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter =new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CoronaVirusData> filteredList=new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filteredList.addAll(allproduct);
            }
            else {
                for(int i=0;i<allproduct.size();i++){
                    CoronaVirusData data=allproduct.get(i);
                    String country=data.getCountryName();
                    if(country.toLowerCase().contains(constraint.toString().toLowerCase())){
                        filteredList.add(allproduct.get(i));
                    }
                }
            }
            FilterResults results=new FilterResults();
            results.values=filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            product.clear();
            product.addAll((Collection<? extends CoronaVirusData>) results.values);
            notifyDataSetChanged();

        }
    };

    class CoronaVirusAdapterholder extends RecyclerView.ViewHolder{
        private TextView TotalCase;
        private TextView Recoved,TodayCases,TodayDeads;
        private TextView CountryName;

        private TextView Death;

        private ImageView flag;

        public CoronaVirusAdapterholder(@NonNull View itemView) {
            super(itemView);
           TotalCase=itemView.findViewById(R.id.Totalcasesincountry);
           Recoved=itemView.findViewById(R.id.TotalRecoveredincountry);
          // flag=itemView.findViewById(R.id.countryFlag);
           Death=itemView.findViewById(R.id.Totaldeathincountry);
           CountryName=itemView.findViewById(R.id.CountryName);
           TodayCases=itemView.findViewById(R.id.TodayCases);
           TodayDeads=itemView.findViewById(R.id.Todaydeadsincountry);
           //flag=itemView.findViewById(R.id.countryFlag);

           /* CropPrice=itemView.findViewById(R.id.PriceOfCrop);
            minquantity=itemView.findViewById(R.id.minproductquantity);
            title=itemView.findViewById(R.id.producttitle);
            category=itemView.findViewById(R.id.productcategory);
            quantity=itemView.findViewById(R.id.productquantity);*/
        }
    }
}
