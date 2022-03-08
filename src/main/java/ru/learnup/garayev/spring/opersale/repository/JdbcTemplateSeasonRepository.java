/*package ru.learnup.garayev.spring.opersale.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
import ru.learnup.garayev.spring.opersale.repository.interfaces.SeasonRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.learnup.garayev.spring.opersale.config.Config.*;

//@Repository
public class JdbcTemplateSeasonRepository implements SeasonRepository {

    //private Connection connection;
    private JdbcTemplate jdbc;

    @Autowired
    public JdbcTemplateSeasonRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    //@Autowired
    //public JdbcTemplateSeasonRepository() {
    //    this.jdbc = new JdbcTemplate();
    //}

    @Override
    public Collection<ListSeasonEntity> getAll() {
        List<ListSeasonEntity> result = new ArrayList<>();
        String sql = "select * from list_season;";

        final SqlRowSet sqlRowSet = jdbc.queryForRowSet(sql);

        while (sqlRowSet.next()) {
            final long id = sqlRowSet.getLong("id");
            final String name = sqlRowSet.getString("name_season");
            result.add(new ListSeasonEntity(id, name));
        }
        return result;
    }


    @Override
    public void saveSeason(ListSeasonEntity season) {
        String sql = "insert into list_season (name_season) values (?);";

        final int updatedRow = jdbc.update(
                sql,
                season.getNameSeason());
    }

    @Override
    public void deleteSeason(long id) {
        String sql = "delete from list_season where id = ?;";

        final int deletedRows = jdbc.update(
                sql,
                id);
    }

}

*/