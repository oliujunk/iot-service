package oliujunk.iotservice.controller;

import lombok.extern.slf4j.Slf4j;
import oliujunk.iotservice.entity.Device;
import oliujunk.iotservice.entity.Response;
import oliujunk.iotservice.entity.ThingModel;
import oliujunk.iotservice.service.DeviceService;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin
@RestController
@Slf4j
public class DeviceController {

    @Resource
    private DeviceService deviceService;

    @GetMapping("/devices/{id}")
    public Response queryDevice(@PathVariable("id") Long id) {
        Device device = deviceService.getDevice(id);
        return Response.success(device);
    }

    @GetMapping("/devices")
    public Response queryDeviceBySn(@RequestParam("sn") String sn) {
        Device device = deviceService.getDeviceBySn(sn);
        return Response.success(device);
    }

    @PostMapping("/devices")
    public Response createDevice(@RequestBody Device device) {
        int result = deviceService.saveDevice(device);
        if (result == 1)
            return Response.success("设备创建成功");
        else
            return Response.fail("设备创建失败");
    }

    @PutMapping("/devices")
    public Response updateDevice(@RequestParam("sn") String sn, @RequestBody Device device) {
        int result = deviceService.updateDevice(sn, device);
        if (result == 1)
            return Response.success("设备更新成功");
        else
            return Response.fail("创建更新失败");
    }

    @DeleteMapping("/devices")
    public  Response deleteDevice(@RequestParam("sn") String sn) {
        int result = deviceService.deleteDevice(sn);
        if (result == 1)
            return Response.success("设备删除成功");
        else
            return Response.fail("设备删除失败");
    }

    @PostMapping("/devices/thingModel")
    public Response updateThingModel(@RequestBody ThingModelParam thingModelParam) {
        if (deviceService.updateThingModel(thingModelParam.sn, thingModelParam.properties, thingModelParam.events, thingModelParam.services)) {
            return Response.success("物模型更新成功");
        }
        return Response.fail("物模型更新失败");
    }

    @GetMapping("/devices/thingModel")
    public Response queryThingModel(@RequestParam("sn") String sn) {
        ThingModel thingModel = deviceService.getThingModel(sn);
        Assert.notNull(thingModel, "物模型为空");
        return Response.success("物模型查询成功", thingModel);
    }

    private record ThingModelParam(String sn, List<Object> properties, List<Object> events, List<Object> services) {
    }


}
