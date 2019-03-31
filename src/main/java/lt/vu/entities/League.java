package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "LEAGUE")
@NamedQueries({
        @NamedQuery(name = "League.findByName", query = "SELECT l FROM League l WHERE l.leagueName LIKE :leagueName"),
})
@Getter
@Setter
@ToString(of = {"id"})
public class League implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "LEAGUE_NAME")
    private String leagueName;

    @ManyToMany(mappedBy = "leagues")
    private List<Team> teams = new ArrayList<>();

}