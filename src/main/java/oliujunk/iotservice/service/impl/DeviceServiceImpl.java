package oliujunk.iotservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mongodb.BasicDBObject;
import lombok.SneakyThrows;
import oliujunk.iotservice.entity.Device;
import oliujunk.iotservice.entity.ThingModel;
import oliujunk.iotservice.mapper.DeviceMapper;
import oliujunk.iotservice.service.DeviceService;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;

    @Resource
    private MongoTemplate mongoTemplate;

    @Override
    public Device getDevice(Long id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public Device getDeviceBySn(String sn) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(sn), Device::getDeviceSn, sn);
        return deviceMapper.selectOne(wrapper);
    }

    @Override
    public Integer saveDevice(Device device) {
        return deviceMapper.insert(device);
    }

    @Override
    public Integer updateDevice(String sn, Device device) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(sn), Device::getDeviceSn, sn);

        return deviceMapper.update(device, wrapper);
    }

    @Override
    public Integer deleteDevice(String sn) {
        LambdaQueryWrapper<Device> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasText(sn), Device::getDeviceSn, sn);

        return deviceMapper.delete(wrapper);
    }

    @SneakyThrows
    @Override
    public Boolean updateThingModel(String sn, List<Object> properties, List<Object> events, List<Object> services) {
        Query query = Query.query(Criteria.where("sn").is(sn));
        Update update = new Update();
        update.set("properties", properties);
        update.set("events", events);
        update.set("services", services);
        update.set("updateTime", LocalDateTime.now());
        mongoTemplate.upsert(query, update, ThingModel.class, "thing_model");

        if (!mongoTemplate.collectionExists(sn)) {
            CollectionOptions collectionOptions = CollectionOptions.timeSeries("time");
            mongoTemplate.createCollection(sn, collectionOptions);
        } else {
            BasicDBObject data = new BasicDBObject("time", new Date());
            data.append("temp", "temp");
            data.append("temp1", 12);
            data.append("temp3", 1543);
            mongoTemplate.insert(data, sn);
        }
        return true;
    }

    @Override
    public ThingModel getThingModel(String sn) {
        Query query = Query.query(Criteria.where("sn").is(sn));
        return mongoTemplate.findOne(query, ThingModel.class, "thing_model");
    }
}
