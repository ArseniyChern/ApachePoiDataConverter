package com.prod.data;

import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class DAO {

	Logger logger = Logger.getLogger(DAO.class);

	@Autowired
	public JdbcTemplate jdbcTemplate;

	// don't need create as of now
	private void create() {
	}

	public List<DataObject> read() {

		List<DataObject> dataObjects = jdbcTemplate.query("SELECT * FROM DATAOBJECT", (rs, num) -> {
			return new DataObject(
					rs.getString("NAME"), 
					rs.getString("START_TIME"), 
					rs.getString("END_TIME"),
					rs.getString("DURATION"));
		});

		logger.debug("read data objects " + dataObjects);

		return dataObjects;
	}

	// don't need update as of now
	private void update() {
	}

	// don't need delete as of now
	private void delete() {
	}

}
