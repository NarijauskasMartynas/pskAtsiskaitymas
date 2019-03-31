package lt.vu.usecases.mybatis.dao;

import java.util.List;
import lt.vu.usecases.mybatis.model.TeamLeague;
import org.mybatis.cdi.Mapper;

@Mapper
public interface TeamLeagueMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM_LEAGUE
     *
     * @mbg.generated Sun Mar 31 14:21:15 EEST 2019
     */
    int insert(TeamLeague record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PUBLIC.TEAM_LEAGUE
     *
     * @mbg.generated Sun Mar 31 14:21:15 EEST 2019
     */
    List<TeamLeague> selectAll();
}