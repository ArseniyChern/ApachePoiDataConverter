package com.prod.data;

import java.sql.SQLException;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.prod.data.DAO;
import com.prod.data.SpringJdbcConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { SpringJdbcConfig.class,DAO.class })
public class DAOTest {

	@Autowired		
	DAO dao;
	
	@Test
	public void testDao() {	
		assertNotNull(dao);							
	}

	@Test
	public void testDaoRead() throws SQLException {
		assertNotNull(dao.read());
	}
}
