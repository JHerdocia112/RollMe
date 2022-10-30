package roll.me.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;

import roll.me.model.Users;
import roll.me.util.DBUtil;

public class UsersDB{

    public static boolean hasUserByName(String Name){

        Users userName = selectByName(Name);
        return userName != null;

    }

    public static void insertUser(Users User){

        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();

        String Email = User.getEmail();
        String Name = User.getUserName();

        try{

            if (UsersDB.hasUserByName(Name)){

                throw new NonUniqueResultException();

            }
            else{

                em.persist(User);
                et.commit();

            }
        } catch (Exception e){

            et.rollback();
            e.printStackTrace();

        }
    }

    public static Users selectByEmail(String Email){

        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Users> tq = em.createNamedQuery("Users.selectByEmail", Users.class);
        tq.setParameter("email", Email);
        Users found = null;

        try{

            found = tq.getSingleResult();

        } catch (NoResultException e){
            
            e.printStackTrace();

        }

        return found;
    }

    public static Users selectByName(String Name){

        EntityManager em = DBUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Users> tq = em.createNamedQuery("Users.selectByName", Users.class);
        tq.setParameter("name", Name);
        Users found = null;

        try{
            found = tq.getSingleResult();

        } catch (NoResultException e){
            
            e.printStackTrace();

        }

        return found;
    }

}