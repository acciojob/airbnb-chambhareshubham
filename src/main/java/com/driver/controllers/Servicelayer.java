package com.driver.controllers;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class Servicelayer {
    Repositorylayer obj = new Repositorylayer();
    public String addhotel(Hotel hotel){
        if (hotel.getHotelName()==null){
            return "FAILURE";
        }
        return obj.addhotel(hotel);
    }
    public int adduser(User user){
       return obj.addUser(user);
    }
    public int bookrooms(Booking booking){
        return obj.bookhotel(booking);
    }
    public int noofbookings(int adharno){
        int ans=0;
        List<Booking>list=obj.getallbooking();
        for(Booking booking: list){
            if(booking.getBookingAadharCard()==adharno){
                ans++;
            }
        }
        return ans;
    }
    public String hotelwithmostfacilities(){
        List<Hotel>list=obj.getallHotel();
        PriorityQueue<Hotel>pq= new PriorityQueue<>((a,b)->{
        return b.getFacilities().size()-a.getFacilities().size();
    });
for(Hotel hotel: list){
    pq.add(hotel);
}
if(pq.peek().getFacilities().size()==0){
    return "";
}
List<String> ans = new ArrayList<>();
Hotel cur = pq.remove();
int max =cur.getFacilities().size();
ans.add(cur.getHotelName());
while(pq.size()>0&&pq.peek().getFacilities().size()==max){
    ans.add(pq.remove().getHotelName());
}
if(ans.size()==1){
    return ans.get(0);
}
String smallest =ans.get(0);
for(int i =1; i<ans.size(); i++){
    int cheack= smallest.compareTo(ans.get(i));
    if(cheack<0){
        smallest=ans.get(i);
    }
}
   return smallest; }

public Hotel updatefacalities(List<Facility>list,String name){
        return obj.updateFacilities(list,name);
}



}
