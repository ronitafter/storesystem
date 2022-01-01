package com.ronit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.mysql.cj.protocol.Resultset;

public class ConnectionPool {

	// connection pool attributes
	private static final String JdbcUrl = "jdbc:mysql://localhost:3306/coupons_system";
	private Set<Connection> connections = new HashSet<Connection>();
	private static final String USERNAME = "";
	private static final String PASSWORD = "";
	// constant
	public static final int POOL_SIZE = 10;

	// ********** singleton design patterns *************
	private static ConnectionPool instance;

	private ConnectionPool() throws SQLException {
		for (int i = 0; i < POOL_SIZE; i++) {
			connections.add(DriverManager.getConnection(JdbcUrl, "root", "wNp2Y2"));
		}

		System.out.println("connection pool is open with " + connections.size() + " connections");
	}

	public static ConnectionPool getInstance() {
		if (instance == null) {
			try {
				instance = new ConnectionPool();
			} catch (SQLException e) {
				throw new RuntimeException("getting connection pool failed", e);
			}
		}
		return instance;
	}

	// == end of singleton design patterns ====================
	public synchronized Connection getConnection() throws SQLException {
		// 1. check if there are available connections (wait while needed)
		while (connections.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// 2. get on connection from the set and remove it
		Iterator<Connection> it = connections.iterator();
		Connection con = it.next();
		it.remove(); // remove the current element from the set
		// 3. return connection to user
		return con;
	}

	public synchronized void returnConnection(Connection con) {
		if (con != null) {
			this.connections.add(con);
			notify(); // let the waiting threads know that a connection is back in the pool
		}
	}

	public synchronized void closeConnections() {
		// wait for all connections to return back to the pool
		while (connections.size() < POOL_SIZE) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		// once all connection are back - close all
		for (Connection connection : connections) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		System.out.println("all connections are closed");
	}

}
