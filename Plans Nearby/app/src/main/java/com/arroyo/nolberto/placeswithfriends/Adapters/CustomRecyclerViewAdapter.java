package com.arroyo.nolberto.placeswithfriends.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arroyo.nolberto.placeswithfriends.Activities.DetailsActivity;
import com.arroyo.nolberto.placeswithfriends.Activities.MainActivity;
import com.arroyo.nolberto.placeswithfriends.Activities.VenueDetailsActivity;
import com.arroyo.nolberto.placeswithfriends.Interfaces.ItemClickInterface;
import com.arroyo.nolberto.placeswithfriends.Models.EventBriteModels.Event;
import com.arroyo.nolberto.placeswithfriends.Models.FourSquareModels.Item;
import com.arroyo.nolberto.placeswithfriends.Models.FourSquareModels.Photo;
import com.arroyo.nolberto.placeswithfriends.Models.FourSquareModels.Venue;
import com.arroyo.nolberto.placeswithfriends.R;
import com.google.android.gms.location.places.Place;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by nolbertoarroyo on 8/19/16.
 */
public class CustomRecyclerViewAdapter extends RecyclerView.Adapter<CustomRecyclerViewAdapter.ViewHolder> {
    private ArrayList<Item> data;
    private static ItemClickInterface onVenueClickListener;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemImage;
        public TextView itemTitle, itemCategory, distanceMiles, itemRating, itemMessage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.list_item_image);
            itemTitle = (TextView) itemView.findViewById(R.id.list_item_title);
            itemCategory = (TextView) itemView.findViewById(R.id.list_item_category);
            distanceMiles = (TextView) itemView.findViewById(R.id.list_item_distance);
            itemRating = (TextView) itemView.findViewById(R.id.list_item_price);
            itemMessage = (TextView) itemView.findViewById(R.id.list_item_date);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String itemClicked = data.get(getLayoutPosition()).getVenue().getId();
                    onVenueClickListener.onItemClicked(itemClicked);
                    Intent intent = new Intent(context, VenueDetailsActivity.class);
                    intent.putExtra("venueId", itemClicked);
                    context.startActivity(intent);
                }
            });

        }
    }


    @Override
    public String toString() {
        return super.toString();
    }

    public CustomRecyclerViewAdapter(ArrayList<Item> inComingData, ItemClickInterface itemClicked) {
        this.onVenueClickListener = itemClicked;
        if (inComingData != null) {
            // if there is incoming data, use it
            this.data = inComingData;
        } else {
            // if there is no incoming data, make an empty list to avoid NullPointerExceptions
            this.data = new ArrayList<Item>();
        }

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

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Item dataItem = data.get(position);
        ImageView itemImage = holder.itemImage;
        TextView itemTitle = holder.itemTitle;
        TextView itemCategory = holder.itemCategory;
        TextView distanceMiles = holder.distanceMiles;
        TextView itemRating = holder.itemRating;
        TextView itemMessage = holder.itemMessage;
        if (dataItem.getVenue().getRating() != null) {

            itemRating.setText(dataItem.getVenue().getRating().toString());
            itemRating.setTextColor(Color.WHITE);
            itemRating.setTypeface(itemRating.getTypeface(),Typeface.BOLD);
            String ratingColor = "#"+dataItem.getVenue().getRatingColor();
            itemRating.setBackgroundColor(Color.parseColor(ratingColor));
        }
        if (dataItem.getVenue().getPrice() != null) {
            Integer venueCost = dataItem.getVenue().getPrice().getTier();
            if (venueCost == 1) {

                itemMessage.setText("$");
            } else if (venueCost == 2) {
                itemMessage.setText("$$");
            } else if (venueCost == 3) {
                itemMessage.setText("$$$");
            } else if (venueCost == 4) {
                itemMessage.setText("$$$$");
            }
        }

        itemTitle.setText(dataItem.getVenue().getName());
        itemCategory.setText((CharSequence) dataItem.getVenue().getCategories().get(0).getName());
        distanceMiles.setText(dataItem.getVenue().getLocation().getCity());
        String suffix = dataItem.getVenue().getFeaturedPhotos().getItems().get(0).getSuffix();
        String prefix = dataItem.getVenue().getFeaturedPhotos().getItems().get(0).getPrefix();

        String url = prefix + "original" + suffix;
        Picasso.with(context).load(url).into(holder.itemImage);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}