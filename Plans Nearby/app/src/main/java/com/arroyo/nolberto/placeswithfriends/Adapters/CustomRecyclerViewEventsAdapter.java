package com.arroyo.nolberto.placeswithfriends.Adapters;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.placeswithfriends.Activities.DetailsActivity;
import com.arroyo.nolberto.placeswithfriends.Interfaces.ItemClickInterface;
import com.arroyo.nolberto.placeswithfriends.Models.EventBriteModels.Event;
import com.arroyo.nolberto.placeswithfriends.R;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by nolbertoarroyo on 8/20/16.
 */
public class CustomRecyclerViewEventsAdapter extends RecyclerView.Adapter<CustomRecyclerViewEventsAdapter.ViewHolder> {
    private static ItemClickInterface onEventClickListener;
    Context context;
    String price;
    String aRevisedDate;
    private ArrayList<Event> data;

    public CustomRecyclerViewEventsAdapter(ArrayList<Event> inComingData, ItemClickInterface eventClicked) {
        this.onEventClickListener = eventClicked;
        if (inComingData != null) {
            // if there is incoming data, use it
            this.data = inComingData;
        } else {
            // if there is no incoming data, make an empty list to avoid NullPointerExceptions
            this.data = new ArrayList<Event>();
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get context from parent ViewGroup
        context = parent.getContext();

        // Get layoutInflater, which will inflate our custom list item layout for us
        LayoutInflater inflater = LayoutInflater.from(context);

        View listItemLayout = inflater.inflate(R.layout.list_item, parent, false);

        // Return a new SampleViewHolder instance
        ViewHolder viewHolder = new ViewHolder(listItemLayout);
        return viewHolder;
    }

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Event dataItem = data.get(position);
        ImageView itemImage = holder.itemImage;
        TextView itemTitle = holder.itemTitle;
        TextView itemCategory = holder.itemCategory;
        TextView distanceMiles = holder.distanceMiles;
        TextView itemPrice = holder.itemPrice;
        TextView itemDate = holder.itemDate;

        YoYo.with(Techniques.FadeIn).playOn(holder.cardView);
        //populate the views with dataItem properties

        if (dataItem.getLogo() != null) {
            Picasso.with(context).load(dataItem.getLogo().getUrl()).into(holder.itemImage);
        } else {
            itemImage.setImageResource(R.drawable.no_images);
        }

        itemTitle.setText(dataItem.getName().getText());

        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        try {
            Date dateObj = sdf.parse(dataItem.getStart().getLocal());
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));   // This line converts the given date into UTC time zone

            aRevisedDate = new SimpleDateFormat("EEE, MMM d"+ "\n"+" hh:mm a").format(dateObj);

            if (aRevisedDate.charAt(13)!='0'&& aRevisedDate.charAt(12)<=10 || aRevisedDate.charAt(12)!='0'&& aRevisedDate.charAt(11)<=9){
                aRevisedDate = new SimpleDateFormat("EEE, MMM d"+ "\n"+" hh:mm a").format(dateObj);

            }else{
                aRevisedDate = new SimpleDateFormat("EEE, MMM d"+ "\n"+" h:mm a").format(dateObj);
            }
            itemDate.setTextSize(14);
            itemDate.setText(aRevisedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        if (dataItem.getVenue() != null) {
            distanceMiles.setText(dataItem.getVenue().getAddress().getLocalizedAreaDisplay());
        }

        if (dataItem.getCategory() != null) {
            itemCategory.setText(dataItem.getCategory().getNameLocalized());
        }
//below code has a bug keep testing, crashes app when you scroll or search for free items
        if (dataItem.getTicketClasses().get(0).getCost() != null) {
            price = dataItem.getTicketClasses().get(0).getCost().getDisplay();
            itemPrice.setText(price);

        } else if (dataItem.getTicketClasses().get(0).getFree()) {
            price = "Free";
            itemPrice.setText(price);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemCategory;
        public TextView distanceMiles;
        public TextView itemPrice;
        public TextView itemDate;
        public CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            itemImage = (ImageView) itemView.findViewById(R.id.list_item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.list_item_title);
            itemCategory = (TextView) itemView.findViewById(R.id.list_item_category);
            distanceMiles = (TextView) itemView.findViewById(R.id.list_item_distance);
            itemPrice = (TextView) itemView.findViewById(R.id.list_item_price);
            itemDate = (TextView) itemView.findViewById(R.id.list_item_date);
            cardView = (CardView) itemView.findViewById(R.id.list_item_cardview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemClicked = data.get(getLayoutPosition()).getId();
                    Log.i("check", itemClicked);
                    onEventClickListener.onItemClicked(itemClicked);
                    Intent intent = new Intent(context, DetailsActivity.class);
                    intent.putExtra("tag", itemClicked);
                    context.startActivity(intent);
                }
            });

        }
    }

    public static Date fromISO8601UTC(String dateStr) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        df.setTimeZone(tz);

        try {
            return df.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}

