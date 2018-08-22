package com.blueskykong.mybatis.service.impl;

import com.blueskykong.mybatis.dao.TestDao;
import com.blueskykong.mybatis.entity.TestModel;
import com.blueskykong.mybatis.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author keets
 * @data 2018/8/22.
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired private TestDao testDao;

    @Override
    public TestModel getEntity(Integer id) {
        return testDao.selectByPrimaryKey(id);
    }

    @Override
    public Integer insertModel(TestModel testModel) {

        return testDao.insertTestModel(testModel);
    }
}
