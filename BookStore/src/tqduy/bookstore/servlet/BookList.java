package tqduy.bookstore.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import tqduy.bookstore.bean.Book;
import tqduy.bookstore.bean.User;
import tqduy.bookstore.conn.BookDAO;

/**
 * Servlet implementation class BookList
 */
public class BookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();

		try {
//			listBook(request, response);
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertBook(request, response);
				break;
			case "/delete":
				deleteBook(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateBook(request, response);
				break;
			case "/list":
				listBook(request, response);
				break;
			case "/logout":
				logout(request, response);
				break;
			default:
				login(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void logout(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("Login.jsp");
		dispatcher.forward(request, response);
	}

	private void login(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String userName = request.getParameter("username");
		String passWord = request.getParameter("password");
		System.out.println("UserName request: " + userName + " - PassWord Requeset: " + passWord);
		User user = new User();
		user.setUserName(userName);
		user.setPassWord(passWord);
		if(!BookDAO.checkUser(user)) {
			response.sendRedirect("Error.jsp");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}

	private void listBook(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Book> listBook = BookDAO.listAllBooks();
		// List<Book> listBook = listAllBooks();
		request.setAttribute("listBook", listBook);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookList.jsp");
		dispatcher.forward(request, response);
	}

	private void showNewForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Book existingBook = BookDAO.getBook(id);
		// Book existingBook = getBook(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BookForm.jsp");
		request.setAttribute("book", existingBook);
		dispatcher.forward(request, response);

	}

	private void insertBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book newBook = new Book(title, author, price);
		BookDAO.insertBook(newBook);
		// insertBook(newBook);
		response.sendRedirect("list");
	}

	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		float price = Float.parseFloat(request.getParameter("price"));

		Book book = new Book(id, title, author, price);
		BookDAO.updateBook(book);
//		updateBook(book);
		response.sendRedirect("list");
	}

	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));

		Book book = new Book(id);
		BookDAO.deleteBook(book);
//		deleteBook(book);
		response.sendRedirect("list");
	}

//	public Connection conn() {
//		BookDAO.con = null;
//		String url = "jdbc:sqlserver://DESKTOP-6T1NTE9\\SQLEXPRESS:1433;" + "databaseName=" + BookDAO.DB_NAME + ";";
//		try {
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			BookDAO.con = DriverManager.getConnection(url, BookDAO.USER_NAME, BookDAO.PASSWORD);
//			// JOptionPane.showMessageDialog(null, "Connect Success !!");
//		} catch (Exception ex) {
//			JOptionPane.showMessageDialog(null, ex);
//		}
//		return BookDAO.con;
//	}
//
//	public void disconnect() throws SQLException {
//		if (bookDAO.con != null && !bookDAO.con.isClosed()) {
//			bookDAO.con.close();
//		}
//	}
//
//	public List<Book> listAllBooks() throws SQLException {
//		List<Book> listBook = new ArrayList<>();
//
//		String sql = "SELECT * FROM Book";
//
//		conn();
//
//		Statement statement = bookDAO.con.createStatement();
//		ResultSet resultSet = statement.executeQuery(sql);
//
//		while (resultSet.next()) {
//			int id = resultSet.getInt("BookID");
//			String title = resultSet.getString("title");
//			String author = resultSet.getString("author");
//			float price = resultSet.getFloat("price");
//
//			Book book = new Book(id, title, author, price);
//			listBook.add(book);
//		}
//
//		resultSet.close();
//		statement.close();
//		disconnect();
//
//		return listBook;
//	}
//
//	public void insertBook(Book book) throws SQLException {
//		conn();
//		execute("INSERT INTO " + BookDAO.TB_NAME + " (title, author, price) VALUES ('" + book.getTitle() + "','"
//				+ book.getAuthor() + "', " + book.getPrice() + ")");
//		disconnect();
//	}
//
//	public void deleteBook(Book book) throws SQLException {
//		conn();
//		execute("DELETE FROM " + BookDAO.TB_NAME + " WHERE BookID = " + book.getId() + "");
//		disconnect();
//	}
//
//	public void updateBook(Book book) throws SQLException {
//		conn();
//		execute("UPDATE " + BookDAO.TB_NAME + " SET title = '" + book.getTitle() + "', author = '" + book.getAuthor()
//				+ "', price = " + book.getPrice() + " WHERE BookID = " + book.getId() + "");
//		disconnect();
//	}
//
//	public Book getBook(int id) throws SQLException {
//		Book book = null;
//		String sql = "SELECT * FROM Book WHERE BookID = ?";
//
//		conn();
//
//		PreparedStatement statement = BookDAO.con.prepareStatement(sql);
//		statement.setInt(1, id);
//
//		ResultSet resultSet = statement.executeQuery();
//
//		if (resultSet.next()) {
//			String title = resultSet.getString("title");
//			String author = resultSet.getString("author");
//			float price = resultSet.getFloat("price");
//
//			book = new Book(id, title, author, price);
//		}
//
//		resultSet.close();
//		statement.close();
//
//		return book;
//	}
//
//	public void execute(String sql) throws SQLException {
//		Statement st = null;
//		try {
//			st = BookDAO.con.createStatement();
//			st.executeUpdate(sql);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			st.close();
//		}
//	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
