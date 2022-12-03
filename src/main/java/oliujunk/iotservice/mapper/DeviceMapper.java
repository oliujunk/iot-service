package oliujunk.iotservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import oliujunk.iotservice.entity.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {
}
