package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PLAYER")
@NamedQueries({
        @NamedQuery(name = "Player.findAll", query = "SELECT p FROM Player p")
})
@Getter
@Setter
@ToString(of = {"ID"})
public class Player implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "FULL_NAME")
    private String fullName;

    @JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")
    @ManyToOne(cascade = CascadeType.ALL)
    @JohnzonIgnore
    private Team team;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    @JohnzonIgnore
    private Integer optLockVersion;
}
