package com.xialuo.shardingjdbcreadorwrite.controller;

import com.xialuo.shardingjdbcreadorwrite.common.ResultDto;
import com.xialuo.shardingjdbcreadorwrite.dao.UserDao;
import com.xialuo.shardingjdbcreadorwrite.entity.User;
import io.shardingsphere.api.HintManager;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

  @Autowired
  UserDao userDao;

  @GetMapping("get")
  public ResultDto<?> get() throws Exception {

    HintManager hintManager = HintManager.getInstance();
    hintManager.setMasterRouteOnly();

    List<User> users = userDao.get();

    hintManager.close();
    return ResultDto.success(users);
  }

  @GetMapping("insert")
  public ResultDto<?> insert(Long id) throws Exception {

    User zhangsan = new User(id, "zhangsan", id.intValue());
    userDao.addOne(zhangsan);

    return ResultDto.success("ok");
  }
}

