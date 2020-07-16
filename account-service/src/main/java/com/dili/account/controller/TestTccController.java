package com.dili.account.controller;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.entity.UserCardDo;
import com.dili.ss.domain.BaseOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/7/15 13:33
 */
@RestController
@RequestMapping("/api/tcc")
public class TestTccController {

    @Autowired
    private IUserCardDao userCardDao;
    /**
    *
    * @author miaoguoxin
    * @date 2020/7/15
    */
    @PostMapping("/testTry")
    @Transactional
    public BaseOutput<Long> testTry(@RequestBody UserCardDo userCardDo){
        userCardDao.save(userCardDo);
        return BaseOutput.successData(userCardDo.getId());
    }

    /**
    * 测试confirm
    * @author miaoguoxin
    * @date 2020/7/15
    */
    @PostMapping("/testConfirm")
    @Transactional
    public BaseOutput<Long> testConfirm(@RequestBody UserCardDo userCardDo){
        userCardDao.update(userCardDo);
        return BaseOutput.successData(userCardDo.getId());
    }

    /**
    * @author miaoguoxin
    * @date 2020/7/15
    */
    @PostMapping("/testCancel")
    @Transactional
    public BaseOutput<String> testCancel(@RequestBody UserCardDo userCardDo){
        userCardDao.batchRemove(new Long[]{userCardDo.getId()});
        return BaseOutput.success();
    }
}
