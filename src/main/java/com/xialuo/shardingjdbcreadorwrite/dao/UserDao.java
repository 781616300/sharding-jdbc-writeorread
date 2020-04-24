package com.xialuo.shardingjdbcreadorwrite.dao;

import com.xialuo.shardingjdbcreadorwrite.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {

  void addOne(User user);

  User getOneById(long id);

  List<User> get();
}
