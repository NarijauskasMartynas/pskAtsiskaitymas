package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TEAM")
@NamedQueries({
        @NamedQuery(name = "Team.findAll", query = "SELECT t FROM Team t"),
        @NamedQuery(name = "Team.findById", query = "SELECT t FROM Team t WHERE t.id = :id")
})
@Getter
@Setter
@ToString(of = {"ID"})
public class Team implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "TEAM_NAME")
    private String teamName;

    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL)
    @JohnzonIgnore
    private List<Player> players = new ArrayList<>();

    @JoinTable(name = "TEAM_LEAGUE",
            joinColumns = {@JoinColumn(name = "TEAM_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "LEAGUE_ID", referencedColumnName = "ID")})
    @ManyToMany(cascade = CascadeType.ALL)
    @JohnzonIgnore
    private List<League> leagues = new ArrayList<>();

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    @JohnzonIgnore
    private Integer optLockVersion;
}
