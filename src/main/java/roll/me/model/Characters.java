package roll.me.model;

import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "Characters.selectAll", query = "SELECT c FROM Characters c"),
    @NamedQuery(name = "Characters.selectByUserId", query = "SELECT c FROM Characters c WHERE c.userId = :userId")
})
public class Characters {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "name", nullable = true, length = 64)
    private String name;

    @Basic
    @Column(name = "lvl", nullable = false)
    private int lvl;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private int userToCharacterId;



}
