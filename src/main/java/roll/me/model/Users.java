package roll.me.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Users.selectByEmail", query = "SELECT e FROM Users e WHERE e.email = :email"),
    @NamedQuery(name = "Users.selectByName", query = "SELECT n FROM Users n WHERE n.name = :name")
})
public class Users {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = false, length = 32)
    private String name;

    @Basic
    @Column(name = "email", nullable = false, length = 32)
    private String email;

    @Basic
    @Column(name = "pass", nullable = false, length = 32)
    private String pass;

    public Users(){

    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id = id;
    }

    public String getUserName(){
        return name;
    }

    public void setUserName(String name){
        this.name = name;
    }

    public String getPassWord(){
        return pass;
    }

    public void setPassWord(String pass){
        this.pass = pass;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
