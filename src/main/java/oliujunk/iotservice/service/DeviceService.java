package oliujunk.iotservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import oliujunk.iotservice.entity.Device;
import oliujunk.iotservice.entity.ThingModel;

import java.util.List;

public interface DeviceService extends IService<Device> {

    Device getDevice(Long id);

    Device getDeviceBySn(String sn);

    Integer saveDevice(Device device);

    Integer updateDevice(String sn, Device device);

    Integer deleteDevice(String sn);

    Boolean updateThingModel(String sn, List<Object> properties, List<Object> events, List<Object> services);

    ThingModel getThingModel(String sn);

}
