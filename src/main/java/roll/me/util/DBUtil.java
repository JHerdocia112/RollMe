package roll.me.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DBUtil {
    private static EntityManagerFactory entitymanager = Persistence.createEntityManagerFactory("default");

    public static EntityManagerFactory getEntityManagerFactory(){
      
        return entitymanager;
    }

    public static <T> List<T> castList(Class<? extends T> _class, Collection<?> collection) {
        List<T> list = new ArrayList<>(collection.size());
        for(Object o: collection){
            list.add(_class.cast(o));
        }
        return list;
    }
}
