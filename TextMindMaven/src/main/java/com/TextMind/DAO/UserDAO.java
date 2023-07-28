/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.TextMind.DAO;

import com.TextMind.Auth.Auth;
import static com.TextMind.Socket.SocketManager.getSocket;
import com.TextMind.entity.User;
import io.socket.emitter.Emitter;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author hoanl
 */
public class UserDAO {
    private ArrayList<User> listFriend = new ArrayList<>();
    
    private ListUpdateListener listUpdateListener;

    public UserDAO() {
        fillList();
    }

    public interface ListUpdateListener {
        void onListUpdated();
    }

    public void setListUpdateListener(ListUpdateListener listener) {
        this.listUpdateListener = listener;
    }

    public ArrayList<User> getListFriend() {
        
        return listFriend;
    }
    
    private void fillList() {
        getSocket().emit("getListFriend", Auth.user.getuID());
        getSocket().on("pushListFriend", new Emitter.Listener() {
            @Override
            public void call(Object... os) {
                String jsonString = os[0].toString();
                try {                  
                    JSONArray jsonArray = new JSONArray(jsonString);
                    
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String name = jsonObject.optString("name");
                        String username = jsonObject.optString("username");
                        String password = jsonObject.optString("password");
                        String uID = jsonObject.optString("uID");
                        if(!checkDeducate(username)){
                            continue;
                        }
                        listFriend.add(new User(uID, name, username, password));
                        if (listUpdateListener != null) {
                            listUpdateListener.onListUpdated();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    private boolean checkDeducate(String username){
        for(User friend : listFriend){
            if(username.equalsIgnoreCase(friend.getUsername())){
                return false;
            }
        }
        return true;
    }
}
