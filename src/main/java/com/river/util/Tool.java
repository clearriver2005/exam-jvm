package com.river.util;
/****
 * @desc 工具类
 * @author wuqinghe
 * @date 205-09-01
 **/
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;

public class Tool {
	/** 
     * 查询数据库是否有某表 
     * @param jdbcTemplate 
     * @param tableName 
     * @return boolean
     */  
    public static boolean existTableName(JdbcTemplate jdbcTemplate,String tableName){  
        ResultSet tabs = null;  
        Connection conn=null;
        try {  
        	conn = jdbcTemplate.getDataSource().getConnection();  
            DatabaseMetaData dbMetaData = conn.getMetaData();  
            String[]   types   =   { "TABLE" };  
            tabs = dbMetaData.getTables(null,null, tableName, types);  
            if (tabs.next()) {  
                return true;  
            }
            tabs.close();
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}  
        }  
        return false;  
    }
    /** 
     * 初始化数据库
     * @param jdbcTemplate 
     */ 
    public static void createTable(JdbcTemplate jdbcTemplate){
		jdbcTemplate.execute("DROP TABLE IF EXISTS R_TRADE;"
				+ "CREATE TABLE R_TRADE("
				+ "TID BIGINT IDENTITY PRIMARY KEY,"
				+ "AMOUNT DECIMAL(20, 2),"
				+ "CREATE_DATE TIMESTAMP DEFAULT  CURRENT_TIMESTAMP,"
				+ "CID BIGINT,"
				+ "MONEY_TYPE TINYINT,"
				+ "STATUS TINYINT,"
				+ "TRADE_TYPE TINYINT);");
		jdbcTemplate.execute("DROP TABLE IF EXISTS R_CUSTOMER;"
					+ "CREATE TABLE R_CUSTOMER("
					+ "CID BIGINT IDENTITY PRIMARY KEY,"
					+ "NAME VARCHAR(100));");
	}
    /** 
     * 将实体类转化成字符串
     * @param objectMapper 转化器
     * @param s  实体类
     * @return 字体串
     */ 
    public static Object mapper(ObjectMapper objectMapper,Object s) {
    	Object r=s;
    	try {
			r=objectMapper.writeValueAsString(s);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return r;
    }
}
