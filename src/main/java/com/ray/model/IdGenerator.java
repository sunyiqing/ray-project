package com.ray.model;


public class IdGenerator {
	private Long id;
	/**
	 * 业务主键标识
	 */
	private String primaryKey;
	/**
	 * 更新步长
	 */
	private Integer step;
	/**
	 * 主键编号
	 */
	private Long sequenceNo;
	/**
	 * 创建时间
	 */
	private java.util.Date createTime;
	/**
	 * 创建人
	 */
	private String createPin;
	/**
	 * 更新时间
	 */
	private java.util.Date updateTime;
	/**
	 * 更新人
	 */
	private String updatePin;
	/**
	 * 版本号
	 */
	private Integer sysVersion;
	/**
	 * 删除标识 0:有效 1:无效
	 */
	private Integer yn;
	/**
	 * 时间戳
	 */
	private java.util.Date ts;

    
	public Long getId(){
		return id;
	}
	public void setId(Long id){
		this.id = id;
	}
	public String getPrimaryKey(){
		return primaryKey;
	}
	public void setPrimaryKey(String primaryKey){
		this.primaryKey = primaryKey;
	}
	public Integer getStep(){
		return step;
	}
	public void setStep(Integer step){
		this.step = step;
	}
	public Long getSequenceNo(){
		return sequenceNo;
	}
	public void setSequenceNo(Long sequenceNo){
		this.sequenceNo = sequenceNo;
	}
	public java.util.Date getCreateTime(){
		return createTime;
	}
	public void setCreateTime(java.util.Date createTime){
		this.createTime = createTime;
	}
	public String getCreatePin(){
		return createPin;
	}
	public void setCreatePin(String createPin){
		this.createPin = createPin;
	}
	public java.util.Date getUpdateTime(){
		return updateTime;
	}
	public void setUpdateTime(java.util.Date updateTime){
		this.updateTime = updateTime;
	}
	public String getUpdatePin(){
		return updatePin;
	}
	public void setUpdatePin(String updatePin){
		this.updatePin = updatePin;
	}
	public Integer getSysVersion(){
		return sysVersion;
	}
	public void setSysVersion(Integer sysVersion){
		this.sysVersion = sysVersion;
	}
	public Integer getYn(){
		return yn;
	}
	public void setYn(Integer yn){
		this.yn = yn;
	}
	public java.util.Date getTs(){
		return ts;
	}
	public void setTs(java.util.Date ts){
		this.ts = ts;
	}
}