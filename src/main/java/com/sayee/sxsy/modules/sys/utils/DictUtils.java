/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.sayee.sxsy.modules.sys.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.sayee.sxsy.modules.sys.entity.Dict;
import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.sayee.sxsy.common.mapper.JsonMapper;
import com.sayee.sxsy.common.utils.CacheUtils;
import com.sayee.sxsy.common.utils.SpringContextHolder;
import com.sayee.sxsy.modules.sys.dao.DictDao;

/**
 * 字典工具类
 * @author ThinkGem
 * @version 2013-5-29
 */
public class DictUtils {
	
	private static DictDao dictDao = SpringContextHolder.getBean(DictDao.class);

	public static final String CACHE_DICT_MAP = "dictMap";
	
	public static String getDictLabel(String value, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && value.equals(dict.getValue())){
					return dict.getLabel();
				}
			}
		}
		return defaultValue;
	}
	
	public static String getDictLabels(String values, String type, String defaultValue){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(values)){
			List<String> valueList = Lists.newArrayList();
			for (String value : StringUtils.split(values, ",")){
				valueList.add(getDictLabel(value, type, defaultValue));
			}
			return StringUtils.join(valueList, ",");
		}
		return defaultValue;
	}

	public static String getDictValue(String label, String type, String defaultLabel){
		if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(label)){
			for (Dict dict : getDictList(type)){
				if (type.equals(dict.getType()) && label.equals(dict.getLabel())){
					return dict.getValue();
				}
			}
		}
		return defaultLabel;
	}
	
	public static List<Dict> getDictList(String type){
		@SuppressWarnings("unchecked")
		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		if (dictMap==null){
			dictMap = Maps.newHashMap();
			for (Dict dict : dictDao.findAllList(new Dict())){
				List<Dict> dictList = dictMap.get(dict.getType());
				if (dictList != null){
					dictList.add(dict);
				}else{
					dictMap.put(dict.getType(), Lists.newArrayList(dict));
				}
			}
			CacheUtils.put(CACHE_DICT_MAP, dictMap);
		}
		List<Dict> dictList = dictMap.get(type);
		if (dictList == null){
			dictList = Lists.newArrayList();
		}
		return dictList;
	}

	/**
	 * 获取比这个数大一位的数
	 * @param value 比较的数
	 * @param type 类型
	 * @param defaultLabel 默认值
	 * @return fyy
	 */
	public static String big(String value,String type,String defaultLabel){
//		Map<String, List<Dict>> dictMap = (Map<String, List<Dict>>)CacheUtils.get(CACHE_DICT_MAP);
		List<Dict> dictList = getDictList(type);
		if (dictList != null){
            List<String> valueList = new ArrayList<>();
            for (int i = 0; i < dictList.size() - 1; i++){
                String value1 = dictList.get(i).getValue();
                valueList.add(value1);
            }
			List<String> newDictList = valueList
					.stream()	//得到流
					.distinct()	//去重
					.sorted()	//自然排序
					.collect(Collectors.toList()); //收集返回
            int aa;    //定义下标的数
            for (int a = 0; a < newDictList.size() - 1; a++){
                if (newDictList.get(a).equals(value)){
                    aa = a;
                    if (aa <= newDictList.size() - 1){
                        String dict = newDictList.get(aa + 1); //获取大一位的数
                        return dict;
                    }else {
                        String dict = newDictList.get(aa);
                        return dict;
                    }
                }
            }
//			CacheUtils.put(CACHE_DICT_MAP, dict);
		}
		return defaultLabel;
	}
	/**
	 * 返回字典列表（JSON）
	 * @param type
	 * @return
	 */
	public static String getDictListJson(String type){
		return JsonMapper.toJsonString(getDictList(type));
	}
	
}
