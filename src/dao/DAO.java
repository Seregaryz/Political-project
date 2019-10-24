package dao;

import obj.Debates;
import obj.News;
import obj.User;

import java.util.ArrayList;

public abstract class DAO {

    public User findUser(String idOfUser, ArrayList<User> users){
        for (User u : users){
            if(idOfUser.equals(u.getId() + "")){
                return u;
            }
        }
        return null;
    }

    public News findNews(String idOfNews, ArrayList<News> news){
        for (News n : news){
            if(idOfNews.equals(n.getId() + "")){
                return n;
            }
        }
        return null;
    }

    public Debates findDebates(String idOfDebates, ArrayList<Debates> debates){
        for (Debates d : debates){
            if(idOfDebates.equals(d.getId() + "")){
                return d;
            }
        }
        return null;
    }

}
