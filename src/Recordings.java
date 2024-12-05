package src;

import src.db.DatabaseConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Recordings {
    public static void saveRecording(int userId, List<Character> recordedData) {
        String sql = "INSERT INTO Recordings (user_id, recorded_data) VALUES (?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, userId); // 사용자 ID
            pstmt.setString(2, convertToString(recordedData)); // 키 시퀀스를 문자열로 변환
            pstmt.executeUpdate();
            System.out.println("녹음 데이터가 저장되었습니다!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("녹음 데이터를 저장하는 중 오류가 발생했습니다.");
        }
    }

    // List<Character>를 문자열로 변환
    private static String convertToString(List<Character> recordedData) {
        StringBuilder sb = new StringBuilder();
        for (char c : recordedData) {
            sb.append(c);
        }
        return sb.toString();
    }
}
