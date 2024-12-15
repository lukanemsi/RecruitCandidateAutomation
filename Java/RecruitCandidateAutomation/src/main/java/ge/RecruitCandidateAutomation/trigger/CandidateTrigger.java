package ge.RecruitCandidateAutomation.trigger;

import org.h2.api.Trigger;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class CandidateTrigger implements Trigger {

    @Override
    public void fire(Connection connection, Object[] oldRow, Object[] newRow) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO CANDIDATE_VERSION VALUES (?,?,?,?,?,?)");
        preparedStatement.setLong(1, (Long) oldRow[0]);
        preparedStatement.setString(2, (String) oldRow[1]);
        preparedStatement.setString(3, (String) oldRow[2]);
        preparedStatement.setString(4, (String) oldRow[3]);
        preparedStatement.setBoolean(5, (Boolean) oldRow[4]);
        preparedStatement.setDate(6, Date.valueOf(LocalDate.now()));
        preparedStatement.executeUpdate();
    }

}