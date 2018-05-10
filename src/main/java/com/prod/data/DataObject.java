package com.prod.data;

/**
 * TODO: Change this to actual object, not placeholder
 * 
 * When updating this to actual production object make sure to override 
 * 
 * toString()
 * equals()
 * no-args constructor
 * all-args constructor
 *
 */
public class DataObject {

	private String name;

	private String startTime;

	private String endTime;

	private String duration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	@Override
	public String toString() {
		return "DataObject [name=" + name + ", startTime=" + startTime + ", endTime=" + endTime + ", duration="
				+ duration + "]";
	}

	public DataObject() {
	}

	public DataObject(String name, String startTime, String endTime, String duration) {
		super();
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = duration;
	}

	//for testing purposes
	@Override
	public boolean equals(Object obj) {
		DataObject clone;
		try {
			clone = (DataObject) obj;
		} catch (ClassCastException e) {
			return false;
		}

		return (getName().equals(clone.getName())) && 
				(getStartTime().equals(clone.getStartTime())) && 
				(getEndTime().equals(clone.getEndTime())) && 
				(getDuration().equals(clone.getDuration()));

	}

}
