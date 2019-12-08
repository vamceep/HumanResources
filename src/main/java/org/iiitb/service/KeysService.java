package org.iiitb.service;
import org.iiitb.bean.KeysVO;
import org.iiitb.service.impl.KeysServiceImpl;

import java.util.List;

public interface KeysService {
    static KeysService keysServiceImpl = new KeysServiceImpl();

    public List<KeysVO> getKeys();

    public KeysVO getKey(Integer id);

    public boolean update(KeysVO keyVO);
}
