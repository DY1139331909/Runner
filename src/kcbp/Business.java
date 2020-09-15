package kcbp;

import java.util.Map;

/**
 * Created by 程浩 on 2020/4/12
 */
public interface Business {
    public int connect(Map connectParameter);

    public int business(Map businessParameter);

    public int disConnect();

}
