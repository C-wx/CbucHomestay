package cbuc.homestay.service;

import cbuc.homestay.bean.Image;
import cbuc.homestay.bean.ImageExample;
import cbuc.homestay.mapper.ImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Explain: 图片处理器
 * @Author: Cbuc
 * @Version: 1.0
 * @Date: 2020/1/11
 */
@Service
public class ImageService {

    @Autowired
    private ImageMapper imageMapper;

    public Image queryDetail(Long id, String value) {
        ImageExample imageExample = new ImageExample();
        imageExample.createCriteria()
                .andParentIdEqualTo(id)
                .andOriginEqualTo(value);
        List<Image> images = imageMapper.selectByExample(imageExample);
        return images.size() == 0 ? null : images.get(0);
    }
}
