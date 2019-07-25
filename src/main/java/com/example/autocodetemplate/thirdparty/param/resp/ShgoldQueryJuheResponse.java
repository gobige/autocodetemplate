package com.example.autocodetemplate.thirdparty.param.resp;

import java.io.Serializable;
import java.util.List;

public class ShgoldQueryJuheResponse extends JuheResponse implements Serializable {
    private static final long serialVersionUID = -1493500034562650656L;

    /**
     * 返回结果集
     */
    private ShgoldQueryJuheResult result;

    public class ShgoldQueryJuheResult implements Serializable {

        private static final long serialVersionUID = -7570745646590824440L;


        private List<LotteryPrize> lottery_prize;

        public class LotteryPrize implements Serializable {
            private static final long serialVersionUID = -4515932381450931719L;

        }


    }

}
