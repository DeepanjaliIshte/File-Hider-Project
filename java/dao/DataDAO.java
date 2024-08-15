package dao;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import database.MyConnection;
import model.Data;

public class DataDAO {

    public static List<Data> getAllFiles(String email) throws SQLException {
        List<Data> files = new ArrayList<>();
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT * FROM data WHERE email = ?")) {
            
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    String name = rs.getString(2);
                    String path = rs.getString(3);
                    files.add(new Data(id, name,name,path));
                }
            }
        }
        return files;
    }

    public static int hideFile(Data file) throws SQLException, IOException {
        int ans = 0;
        File f = new File(file.getPath());
        
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO data(name, path, email, bin_data) VALUES(?,?,?,?)");
             FileReader fr = new FileReader(f)) {
            
            ps.setString(1, file.getFilename());
            ps.setString(2, file.getPath());
            ps.setString(3, file.getEmail());
            ps.setCharacterStream(4, fr, (int) f.length());
            
            ans = ps.executeUpdate();
            
            // Delete the original file only if insertion was successful
            if (ans > 0) {
                if (!f.delete()) {
                    System.err.println("Failed to delete the original file: " + f.getPath());
                }
            }
        }
        return ans;
    }

    public static void unhide(int id) throws SQLException, IOException {
        try (Connection connection = MyConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement("SELECT path, bin_data FROM data WHERE id = ?")) {
            
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String path = rs.getString("path");
                    Clob c = rs.getClob("bin_data");

                    try (Reader r = c.getCharacterStream();
                         FileWriter fw = new FileWriter(path)) {
                        int i;
                        while ((i = r.read()) != -1) {
                            fw.write((char) i);
                        }
                    }

                    // Delete the record from the database
                    try (PreparedStatement deletePS = connection.prepareStatement("DELETE FROM data WHERE id = ?")) {
                        deletePS.setInt(1, id);
                        deletePS.executeUpdate();
                    }
                    
                    System.out.println("Successfully Unhidden");
                }
            }
        }
    }
}
