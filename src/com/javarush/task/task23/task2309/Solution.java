package com.javarush.task.task23.task2309;

import com.javarush.task.task23.task2309.vo.*;

import java.util.List;

/* 
Анонимность иногда так приятна!
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        print(solution.getUsers());
        print(solution.getLocations());
    }

    public static void print(List list) {
        String format = "Id=%d, name='%s', description=%s";
        for (Object obj : list) {
            NamedItem item = (NamedItem) obj;
            System.out.println(String.format(format, item.getId(), item.getName(), item.getDescription()));
        }
    }

    public List<User> getUsers(){
         AbstractDbSelectExecutor<User> abstractExecutor = new AbstractDbSelectExecutor<User>() {
             @Override
             public String getQuery() {
                 return "SELECT * FROM "+new User().getClass().getSimpleName().toUpperCase();
             }

             public List<User> execute(){
                 return super.execute();
             }
         };
         return abstractExecutor.execute();
    }

    public List<Location> getLocations(){
        AbstractDbSelectExecutor<Location> abstractExecutor = new AbstractDbSelectExecutor<Location>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM "+new Location().getClass().getSimpleName().toUpperCase();
            }

            public List<Location> execute(){
                return super.execute();
            }
        };
        return abstractExecutor.execute();
    }

    public List<Server> getServers(){
        AbstractDbSelectExecutor<Server> abstractExecutor = new AbstractDbSelectExecutor<Server>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM "+new Server().getClass().getSimpleName().toUpperCase();
            }

            public List<Server> execute(){
                return super.execute();
            }
        };
        return abstractExecutor.execute();
    }

    public List<Subject> getSubjects(){
        AbstractDbSelectExecutor<Subject> abstractExecutor = new AbstractDbSelectExecutor<Subject>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM "+new Subject().getClass().getSimpleName().toUpperCase();
            }

            public List<Subject> execute(){
                return super.execute();
            }
        };
        return abstractExecutor.execute();
    }

    public List<Subscription> getSubscriptions(){
        AbstractDbSelectExecutor<Subscription> abstractExecutor = new AbstractDbSelectExecutor<Subscription>() {
            @Override
            public String getQuery() {
                return "SELECT * FROM "+new Subscription().getClass().getSimpleName().toUpperCase();
            }

            public List<Subscription> execute(){
                return super.execute();
            }
        };
        return abstractExecutor.execute();
    }
}
