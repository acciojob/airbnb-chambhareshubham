package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class Repositorylayer {
    HashMap<String, Hotel>hm = new HashMap<>();
    HashMap<Integer, User>userdetails = new HashMap<>();
    HashMap<String, Booking>bookingHashMap= new HashMap<>();
    public String addhotel(Hotel hotel){
        if(hm.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hm.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
    }
    public int addUser(User user){
        userdetails.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
    }
    public int bookhotel(Booking booking){
        bookingHashMap.put(booking.getBookingId(), booking);
        Hotel hotel = hm.get(booking.getHotelName());
        int price = hotel.getPricePerNight();
        int availablerooms= hotel.getAvailableRooms();
        if(availablerooms<booking.getNoOfRooms()){
            return -1;
        }
        return price*booking.getNoOfRooms();
    }
    public List<Booking> getallbooking(){
        List<Booking>list = new ArrayList<>();
        for(Booking booking:bookingHashMap.values()){
            list.add(booking);
        }
        return list;
    }
    public List<Hotel> getallHotel(){
        List<Hotel>list= new ArrayList<>();
        for(Hotel hotel: hm.values()){
            list.add(hotel);
        }
        return list;
    }
    public Hotel updateFacilities(List<Facility>list,String hotelname){
      Hotel cur=  hm.get(hotelname);
        List<Facility>curfaciliie= cur.getFacilities();
for(int i =0; i<list.size(); i++){
    if(curfaciliie.contains(list.get(i))){
        continue;
    }
    else{
        curfaciliie.add(list.get(i));
    }
   // cur.setFacilities(curfaciliie);
//hm.put(cur.getHotelName(),cur);
   // return cur;
}
        cur.setFacilities(curfaciliie);
        hm.put(cur.getHotelName(),cur);
        return cur;

    }
}
