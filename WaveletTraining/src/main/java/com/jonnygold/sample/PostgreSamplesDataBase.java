package com.jonnygold.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class PostgreSamplesDataBase<S extends IsBlock, L extends IsBlock> implements IsSamplesDataSource<S, L> {

	private final String URL;
	
	private final String userName;
	
	private final String password;
	
	private Connection connection;
	
	public PostgreSamplesDataBase(String URL, String userName, String password) {
		this.URL = URL;
		this.userName = userName;
		this.password = password;
	}

	@Override
	public void connect() throws DataSourceException {
		System.out.println("Подключение...");
		try {			
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(URL, userName, password);
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new DataSourceException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataSourceException(e);
		}        
        System.out.println("Подключено");
	}

	@Override
	public void disconnect() throws DataSourceException {
		System.out.println("Отключение...");
        try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataSourceException(e);
		}
        System.out.println("Отключено");
	}

	@Override
	public void saveSample(IsSamplesDataSource.Sample<S, L> sample) throws DataSourceException {
		PreparedStatement st;
		try {
			st = connection.prepareStatement("select save_sample(?, ?)");
			double[] dataSmall = sample.getSmall().getData();
			Object[] intDataSmall = new Object[dataSmall.length];
			for(int i=0; i<dataSmall.length; i++){
				intDataSmall[i] = (int) dataSmall[i];
			}
			
			double[] dataLarge = sample.getBig().getData();
			Object[] intDataLarge = new Object[dataLarge.length];
			for(int i=0; i<dataLarge.length; i++){
				intDataLarge[i] = (int) dataLarge[i];
			}
			st.setArray(1, connection.createArrayOf("integer", intDataSmall));
			st.setArray(2, connection.createArrayOf("integer", intDataLarge));
			
			st.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataSourceException(e);
		}
		
		
		
//		switch (sample.getSegment()) {
//		case LH:
//			
//			break;
//		case HL:
//					
//			break;
//		case HH:
//			
//			break;
//
//		default:
//			break;
//		}
	}

	@Override
	public IsSamplesDataSource.Sample<S, L> findSample(S small) throws DataSourceException {
		// TODO Auto-generated method stub
		return null;
	}

}
