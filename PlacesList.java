package edu.oakland.cit480.cit_480;

import java.io.Serializable;
import java.util.List;

import com.google.api.client.util.Key;

public class PlacesList implements Serializable {

    @Key
    public String status;

    @Key
    public List<Place> results;

}
