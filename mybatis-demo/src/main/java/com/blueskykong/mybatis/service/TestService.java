package com.blueskykong.mybatis.service;

import com.blueskykong.mybatis.entity.TestModel;

/**
 * @author keets
 * @data 2018/8/22.
 */
public interface TestService {


    TestModel getEntity(Integer id);

    Integer insertModel(TestModel testModel);

}
