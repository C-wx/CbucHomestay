package cbuc.homestay.controller.BulletinCenter;

import cbuc.homestay.bean.Bulletin;
import cbuc.homestay.service.BulletinService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Explain: 商户端公告中心
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/2/11
 */
@Slf4j
@Controller
@RequestMapping("/merchant")
@Api(value = "商户端公告中心",description = "展示相关公告")
public class MerchantBulletinController {

    @Autowired
    private BulletinService bulletinService;

    @ApiOperation("跳转公告中心页")
    @RequestMapping("/toBulletin")
    public String toBulletin(Model model){
        Bulletin bulletin = Bulletin.builder().status("E").build();
        List<Bulletin> bulletinList = bulletinService.queryList(bulletin);
        model.addAttribute("bulletinList",bulletinList);
        return "merchant/bulletin";
    }

}
