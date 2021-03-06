package com.arroyo.b.plansnearby.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arroyo.b.plansnearby.Utils.Constants;
import com.arroyo.b.plansnearby.R;

import java.util.ArrayList;

/**
 * this activity allows user to select event interests and save selections into ArrayList as selected
 * once all selections are made it converts list to array and save in shared prefs
 * activity goes to Main after user completes selections
 */
public class PickInterestsActivity extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> userInterests;
    private TextView music, businessProfessional, foodDrink, communityCulture, performingVisualArts, filmMediaEntertainment, other, sportsFitness,
            healthWellness, scienceTech, travelOutdoor, charityCauses, religionSpirituality,
            familyEducation, seasonalHoliday, govPolitics, fashionBeauty, autoAirBoat, homeLifestyle, hobbiesSpecialInterests;
    private Button finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_interests);
        setViews();
        setTextViewListeners();
        userInterests = new ArrayList<>();
        convertArraylistToArray();


    }

    public void setViews() {
        music = (TextView) findViewById(R.id.music);
        businessProfessional = (TextView) findViewById(R.id.business_professional);
        foodDrink = (TextView) findViewById(R.id.food_drink);
        communityCulture = (TextView) findViewById(R.id.community_culture);
        performingVisualArts = (TextView) findViewById(R.id.performing_visual_arts);
        filmMediaEntertainment = (TextView) findViewById(R.id.film_media_entertainment);
        other = (TextView) findViewById(R.id.other);
        sportsFitness = (TextView) findViewById(R.id.sports_fitness);
        healthWellness = (TextView) findViewById(R.id.health_wellness);
        scienceTech = (TextView) findViewById(R.id.science_tech);
        travelOutdoor = (TextView) findViewById(R.id.travel_outdoor);
        charityCauses = (TextView) findViewById(R.id.charity_causes);
        religionSpirituality = (TextView) findViewById(R.id.religion_spirituality);
        familyEducation = (TextView) findViewById(R.id.family_education);
        seasonalHoliday = (TextView) findViewById(R.id.seasonal_holiday);
        govPolitics = (TextView) findViewById(R.id.gov_politics);
        fashionBeauty = (TextView) findViewById(R.id.fashion_beauty);
        autoAirBoat = (TextView) findViewById(R.id.auto_air_boat);
        homeLifestyle = (TextView) findViewById(R.id.home_lifestyle);
        hobbiesSpecialInterests = (TextView) findViewById(R.id.hobbies_special_interests);
        finish = (Button) findViewById(R.id.finishButton);


    }

    //click listener, switch statement for selecting categories, when clicked value of category is added to
    //userInterests ArrayList, TextView background color changed to blue showing selection
    @Override
    public void onClick(View view) {
        String categoryId;
        switch (view.getId()) {
            case R.id.music:
                categoryId = "103";
                userInterests.add(categoryId);
                music.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                break;
            case R.id.business_professional:
                categoryId = "101";
                businessProfessional.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.food_drink:
                categoryId = "110";
                foodDrink.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.community_culture:
                categoryId = "113";
                communityCulture.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.performing_visual_arts:
                categoryId = "105";
                performingVisualArts.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.film_media_entertainment:
                categoryId = "104";
                filmMediaEntertainment.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.sports_fitness:
                categoryId = "108";
                sportsFitness.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.health_wellness:
                categoryId = "107";
                healthWellness.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.science_tech:
                categoryId = "102";
                scienceTech.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.travel_outdoor:
                categoryId = "109";
                travelOutdoor.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.charity_causes:
                categoryId = "111";
                charityCauses.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.religion_spirituality:
                categoryId = "114";
                religionSpirituality.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.family_education:
                categoryId = "115";
                familyEducation.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.seasonal_holiday:
                categoryId = "116";
                seasonalHoliday.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.gov_politics:
                categoryId = "112";
                govPolitics.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.fashion_beauty:
                categoryId = "106";
                fashionBeauty.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.home_lifestyle:
                categoryId = "107";
                homeLifestyle.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.auto_air_boat:
                categoryId = "118";
                autoAirBoat.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.hobbies_special_interests:
                categoryId = "119";
                hobbiesSpecialInterests.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.other:
                categoryId = "199";
                other.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                userInterests.add(categoryId);
                break;
            case R.id.finishButton:
                String[] interests = userInterests.toArray(new String[userInterests.size()]);
                saveArray(interests, Constants.PREFS_INTEREST_ARRAY, this);
                Intent intent = new Intent(PickInterestsActivity.this, MainActivity.class);
                startActivity(intent);
                break;

        }

    }

    /**
     * saveArray() saves array of categories selected by user into shared prefs
     *
     * @param array
     * @param arrayName
     * @param mContext
     * @return
     */
    public boolean saveArray(String[] array, String arrayName, Context mContext) {
        SharedPreferences prefs = mContext.getSharedPreferences(Constants.SHAREPREFS, 0);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(arrayName + "_size", array.length);
        for (int i = 0; i < array.length; i++)
            editor.putString(arrayName + "_" + i, array[i]);
        return editor.commit();
    }


    //setting textViewListeners and button listeners for selecting categories in flowLayout
    public void setTextViewListeners() {
        music.setOnClickListener(this);
        businessProfessional.setOnClickListener(this);
        foodDrink.setOnClickListener(this);
        communityCulture.setOnClickListener(this);
        performingVisualArts.setOnClickListener(this);
        filmMediaEntertainment.setOnClickListener(this);
        other.setOnClickListener(this);
        sportsFitness.setOnClickListener(this);
        healthWellness.setOnClickListener(this);
        scienceTech.setOnClickListener(this);
        travelOutdoor.setOnClickListener(this);
        charityCauses.setOnClickListener(this);
        religionSpirituality.setOnClickListener(this);
        familyEducation.setOnClickListener(this);
        seasonalHoliday.setOnClickListener(this);
        govPolitics.setOnClickListener(this);
        fashionBeauty.setOnClickListener(this);
        autoAirBoat.setOnClickListener(this);
        homeLifestyle.setOnClickListener(this);
        hobbiesSpecialInterests.setOnClickListener(this);
        finish.setOnClickListener(this);
    }

    //converting arraylist to Array and saving array to shareprefs
    public void convertArraylistToArray() {
        String[] interests = userInterests.toArray(new String[userInterests.size()]);
        saveArray(interests, Constants.PREFS_INTEREST_ARRAY, this);
    }

}


