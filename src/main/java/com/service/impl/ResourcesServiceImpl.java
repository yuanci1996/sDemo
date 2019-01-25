package com.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mapper.ResourcesMapper;
import com.pojo.Resources;
import com.service.ResourcesService;

@Service("resourcesService")
public class ResourcesServiceImpl implements ResourcesService{
	
	@Resource
	private ResourcesMapper resourcesDao;
	
	
	@Override
	@Cacheable(cacheNames="myCache",key="#resources.resUrl + #resources.username+#resources.type")
	public List<Resources> loadMenu(Resources resources) {
		return resourcesDao.loadMenu(resources);
	}

}
