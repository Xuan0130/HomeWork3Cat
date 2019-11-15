package com.homework.cat;

import java.util.ArrayList;
import java.util.List;

public class FavouriteUtils {
    static ArrayList<Favourite> favourites = new ArrayList();
    public static List<Favourite> getAllFavourite(){

        return favourites;
    }
    public static boolean addFavourite(Favourite favourite){
        boolean isFavourite = false;
        for (int i = 0; i < favourites.size(); i++) {
            if (favourites.get(i).getCatId().equals(favourite.getCatId())){
                isFavourite = true;
            }
        }
        if (isFavourite){
            return  false;

        }else {
            favourites.add(favourite);
            return  true;
        }
    }
}
