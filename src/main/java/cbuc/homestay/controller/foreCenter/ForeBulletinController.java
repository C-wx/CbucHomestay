package cbuc.homestay.controller.foreCenter;

import cbuc.homestay.base.Result;
import cbuc.homestay.bean.Bulletin;
import cbuc.homestay.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Explain: 小程序端公告控制器
 * @Author: Cbuc
 * @Date: 2020/2/3
 */
@Controller
public class ForeBulletinController {

    @Autowired
    private BulletinService bulletinService;

    @ResponseBody
    @RequestMapping("/getLastBulletin")
    public Object getLastBulletin() {
        Bulletin bulletin = bulletinService.getLastBulletin();
        return Result.success(bulletin);
    }

}
