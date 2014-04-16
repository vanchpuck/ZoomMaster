package com.jonnygold.sample;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLData;
import java.sql.SQLException;
import java.sql.SQLInput;
import java.sql.SQLOutput;
import java.sql.Struct;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.jdbc4.Jdbc4Array;

import com.jonnygold.wavelet.Signal;
import com.jonnygold.wavelet.WaveletData2D;
import com.jonnygold.wavelet.WaveletData2D.Segment;

public abstract class PostgreSamplesDataBase<S extends IsBlock, L extends IsBlock> implements IsSamplesDataSource<S, L> {

//	protected final static class Segment {
//		public static final int LL_ID = 1;
//		public static final int LH_ID = 2;
//		public static final int HL_ID = 3;
//		public static final int HH_ID = 4;
//		
//		private final Map<WaveletData2D.Segment, Integer> idMap = new HashMap<WaveletData2D.Segment, Integer>(8);
//		
//		protected Segment(){
//			idMap.put(WaveletData2D.Segment.LL, LL_ID);
//			idMap.put(WaveletData2D.Segment.LH, LH_ID);
//			idMap.put(WaveletData2D.Segment.HL, HL_ID);
//			idMap.put(WaveletData2D.Segment.HH, HH_ID);
//		}
//		
//		public int getSegmentId(WaveletData2D.Segment segment){
//			return idMap.get(segment);
//		}
//	}
	
	private class Buffer{
		
		private static final int CAPACITY = 500;
		
		private Object[] samples = new Object[CAPACITY];
		
		private int mark = 0;
		
		public boolean isFull(){
			if(mark >= CAPACITY){
				return true;
			}
			else{
				return false;
			}
		}
		
		public void addData(IsSamplesDataSource.Sample sample){
			samples[mark] = sample;
//			samples.add(sample);
			mark++;
		}
		
		public void saveData(){
			PreparedStatement st;
			try {
				st = connection.prepareStatement("select save_samples(?)");
				st.setArray(1, connection.createArrayOf("sample_type", samples));
				st.executeQuery();
				mark = 0;
			} catch (Exception exc){
				exc.printStackTrace();
			}
		}
		
	}
	
	private final String URL;
	
	private final String userName;
	
	private final String password;
	
	private Connection connection;
	
//	private Map<String, Class<?>> typeMap;
	
	private Buffer buffer;
	
	public PostgreSamplesDataBase(String URL, String userName, String password) {
		this.URL = URL;
		this.userName = userName;
		this.password = password;
		this.buffer = new Buffer();
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
//		try {
//			this.typeMap = connection.getTypeMap();
//			this.typeMap.put("test_type", Class.forName("Test"));
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
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
	public void saveSample(IsSamplesDataSource.Sample sample) throws DataSourceException {
		if(!buffer.isFull()){
			buffer.addData(sample);
		}
		else{
			buffer.saveData();
		}
	}

	@Override
	public IsSamplesDataSource.Sample findSample(Signal signal, Segment segment) throws DataSourceException {
		Object o = null;
		String str = null;
		Sample sample = new Sample(signal, new Signal(4, 4), segment);
		try {
//			PreparedStatement st;
			CallableStatement st;
//			st = connection.prepareStatement("select get_increased(?)");
			st = connection.prepareCall("{ ? = call get_increased( ? ) }");
			st.registerOutParameter(1, Types.ARRAY);
			st.setObject(2, sample, Types.OTHER);
			st.execute();
			Object s = st.getObject(1);
			
			if(s != null){
				String res = s.toString().substring(1, st.getObject(1).toString().length()-1);
				String[] vals = res.split(",");
				for(int i=0;i<vals.length;i++){
					sample.getBig().getData()[i] = Double.valueOf(vals[i]);
				}
			}
			
//			st.setObject(1, sample, Types.OTHER); 
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataSourceException();
		}
		
		return sample;
	}
	
	@Override
	public void flush() {
		buffer.saveData();
	}

}
