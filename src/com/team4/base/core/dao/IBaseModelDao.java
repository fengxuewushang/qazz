package com.team4.base.core.dao;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.team4.base.core.exception.AppException;
import com.team4.base.core.model.BaseModel;

/**
 * ����Dao�ӿ�
 * @author {guozichen}
 * 2014��11��3��
 */
public interface IBaseModelDao {
	/**
	 * 
	 * @param baseModel
	 * @return
	 * @throws AppException
	 */
	public <T extends BaseModel> void insert(T baseModel) throws AppException;
	
	public <T extends BaseModel> void deleteByModel(T baseModel) throws AppException;
	
	public <T extends BaseModel> void update(T baseModel) throws AppException;
	
	public <T extends BaseModel> T searchById(String id, T baseModel) throws AppException;
	
	public <T extends BaseModel> Integer count(T baseModel) throws AppException;
	
	public <T extends BaseModel> List<T> search(T baseModel, Integer startIndex, Integer pageRows) throws AppException;

	public <T extends BaseModel> List<T> search(T baseModel) throws AppException;


}
