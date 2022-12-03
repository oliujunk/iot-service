package oliujunk.iotservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oliujunk.iotservice.entity.UserTree;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserTreeMapper extends BaseMapper<UserTree> {
}
