package tqduy.bookstore.conn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import tqduy.bookstore.bean.Book;
import tqduy.bookstore.bean.User;

public class BookDAO {
	public final static String DB_NAME = "BookStore";
	public final static String TB_NAME = "Book";
	public final static String TB_NAME_USER = "UserBook";
	public final static String USER_NAME = "duy";
	public final static String PASSWORD = "1234";
	public static Connection con;
	
	public static Connection conn() {
		con = null;
		String url = "jdbc:sqlserver://DESKTOP-6T1NTE9\\SQLEXPRESS:1433;" + "databaseName="+BookDAO.DB_NAME+";";
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, BookDAO.USER_NAME, BookDAO.PASSWORD);
            System.out.println("Sucess !!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
        return con;
	}
	
	public static void main(String[] args) {
		BookDAO.conn();
	}
	
	public static void disconnect() throws SQLException {
		if (con != null && !con.isClosed()) {
			con.close();
		}
	}
	
	public static void insertBook(Book book) throws SQLException {
		conn();
		execute("INSERT INTO "+BookDAO.TB_NAME+" (title, author, price) VALUES ('"+book.getTitle()+"','"+book.getAuthor()+"', "+book.getPrice()+")");
		disconnect();
	}
	
	public static void deleteBook(Book book) throws SQLException {
		conn();
		execute("DELETE FROM "+BookDAO.TB_NAME+" WHERE BookID = "+book.getId()+"");
		disconnect();
	}
	
	public static void updateBook(Book book) throws SQLException {
		conn();
		execute("UPDATE "+BookDAO.TB_NAME+" SET title = '"+book.getTitle()+"', author = '"+book.getAuthor()+"', price = "+book.getPrice()+" WHERE BookID = "+book.getId()+"");
		disconnect();
	}
	
	public static List<Book> listAllBooks() throws SQLException {
		List<Book> listBook = new ArrayList<>();
		
		String sql = "SELECT * FROM "+TB_NAME+"";
		
		conn();
		
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			int id = resultSet.getInt("BookID");
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			
			Book book = new Book(id, title, author, price);
			listBook.add(book);
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		
		return listBook;
	}
	
	public static boolean checkUser(User user) throws SQLException {
		String sql = "SELECT * FROM "+BookDAO.TB_NAME_USER+"";
		
		conn();
		
		Statement statement = con.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		
		while (resultSet.next()) {
			String userName = resultSet.getString("UserName");
			String passWord = resultSet.getString("PassWord");
			System.out.println("UserName: " + userName + " - PassWord: " + passWord);
			
			if(user.getUserName().trim().equals(userName) && user.getPassWord().trim().equals(passWord)) {
				return true;
			}
		}
		
		resultSet.close();
		statement.close();
		
		disconnect();
		return false;
	}
	
	public static Book getBook(int id) throws SQLException {
		Book book = null;
		String sql = "SELECT * FROM Book WHERE BookID = ?";
		
		conn();
		
		PreparedStatement statement = con.prepareStatement(sql);
		statement.setInt(1, id);
		
		ResultSet resultSet = statement.executeQuery();
		
		if (resultSet.next()) {
			String title = resultSet.getString("title");
			String author = resultSet.getString("author");
			float price = resultSet.getFloat("price");
			
			book = new Book(id, title, author, price);
		}
		
		resultSet.close();
		statement.close();
		
		return book;
	}
	
	public static void execute(String sql) throws SQLException {
		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			st.close();
		}
	}
}
