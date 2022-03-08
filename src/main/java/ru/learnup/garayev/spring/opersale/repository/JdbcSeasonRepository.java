//package ru.learnup.garayev.spring.opersale.repository;
//
//import org.springframework.stereotype.Repository;
//import ru.learnup.garayev.spring.opersale.repository.entity.ListSeasonEntity;
//import ru.learnup.garayev.spring.opersale.repository.interfaces.SeasonRepository;
//
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//
//import static ru.learnup.garayev.spring.opersale.config.Config.*;
//
////@Repository
//public class JdbcSeasonRepository implements SeasonRepository {
//
//    private Connection connection;
//
//    public JdbcSeasonRepository() {
//        try {
//            this.connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }
//
//    @Override
//    public Collection<ListSeasonEntity> getAll() {
//        List<ListSeasonEntity> result = new ArrayList<>();
//        String sql = "select * from list_season;";
//        try (Statement statement = connection.createStatement()) {
//            final ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                final long id = resultSet.getLong("id");
//                final String name = resultSet.getString("name_season");
//                result.add(new ListSeasonEntity(id, name));
//            }
//            return result;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//
//    @Override
//    public void saveSeason(ListSeasonEntity season) {
//        String sql = "insert into list_season (name_season) values (?);";
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setString(1, season.getNameSeason());
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void deleteSeason(long id) {
//        String sql = "delete from list_season where id = ?;";
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            statement.setLong(1, id);
//            statement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
