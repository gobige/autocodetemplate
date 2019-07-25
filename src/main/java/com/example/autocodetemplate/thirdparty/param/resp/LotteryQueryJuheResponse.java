package com.example.autocodetemplate.thirdparty.param.resp;

import java.io.Serializable;
import java.util.List;

/**
 * <p></p>
 * <p>Project: </p>
 * <p>ModuleID: thirdParty---juhe</p>
 * <p>Comments: 开奖结果查询</p>
 * <p>JDK version used JDK1.8</p>
 *
 * @version 1.0
 */
public class LotteryQueryJuheResponse extends JuheResponse implements Serializable {
    private static final long serialVersionUID = -1493500034562650656L;

    /**
     * 返回结果集
     */
    private LotteryQueryJuheResult result;

    public class LotteryQueryJuheResult implements Serializable {

        private static final long serialVersionUID = -7570745646590824440L;
        /**
         * 彩票ID
         */
        private String lottery_id;
        /**
         * 彩票名称
         */
        private String lottery_name;
        /**
         * 开奖结果
         */
        private String lottery_res;
        /**
         * 开奖期号
         */
        private String lottery_no;
        /**
         *	开奖日期
         */
        private String lottery_date;
        /**
         *	兑奖截止日期
         */
        private String lottery_exdate;
        /**
         *	本期销售额，可能为空
         */
        private String lottery_sale_amount;
        /**
         *	奖池滚存，可能为空
         */
        private String lottery_pool_amount;

        private List<LotteryPrize> lottery_prize;

        public class LotteryPrize implements Serializable {
            private static final long serialVersionUID = -4515932381450931719L;
            /**
             * 奖项名称
             */
            private String prize_name;
            /**
             * 中奖数量 公布数据可能延时可能为空或
             */
            private String prize_num;
            /**
             * 中奖金额
             */
            private String prize_amount;
            /**
             * 中奖条件
             */
            private String prize_require;

            public String getPrize_name() {
                return prize_name;
            }

            public void setPrize_name(String prize_name) {
                this.prize_name = prize_name;
            }

            public String getPrize_num() {
                return prize_num;
            }

            public void setPrize_num(String prize_num) {
                this.prize_num = prize_num;
            }

            public String getPrize_amount() {
                return prize_amount;
            }

            public void setPrize_amount(String prize_amount) {
                this.prize_amount = prize_amount;
            }

            public String getPrize_require() {
                return prize_require;
            }

            public void setPrize_require(String prize_require) {
                this.prize_require = prize_require;
            }
        }

        public String getLottery_id() {
            return lottery_id;
        }

        public void setLottery_id(String lottery_id) {
            this.lottery_id = lottery_id;
        }

        public String getLottery_name() {
            return lottery_name;
        }

        public void setLottery_name(String lottery_name) {
            this.lottery_name = lottery_name;
        }

        public String getLottery_res() {
            return lottery_res;
        }

        public void setLottery_res(String lottery_res) {
            this.lottery_res = lottery_res;
        }

        public String getLottery_no() {
            return lottery_no;
        }

        public void setLottery_no(String lottery_no) {
            this.lottery_no = lottery_no;
        }

        public String getLottery_date() {
            return lottery_date;
        }

        public void setLottery_date(String lottery_date) {
            this.lottery_date = lottery_date;
        }

        public String getLottery_exdate() {
            return lottery_exdate;
        }

        public void setLottery_exdate(String lottery_exdate) {
            this.lottery_exdate = lottery_exdate;
        }

        public String getLottery_sale_amount() {
            return lottery_sale_amount;
        }

        public void setLottery_sale_amount(String lottery_sale_amount) {
            this.lottery_sale_amount = lottery_sale_amount;
        }

        public String getLottery_pool_amount() {
            return lottery_pool_amount;
        }

        public void setLottery_pool_amount(String lottery_pool_amount) {
            this.lottery_pool_amount = lottery_pool_amount;
        }

        public List<LotteryPrize> getLottery_prize() {
            return lottery_prize;
        }

        public void setLottery_prize(List<LotteryPrize> lottery_prize) {
            this.lottery_prize = lottery_prize;
        }
    }

    public LotteryQueryJuheResult getResult() {
        return result;
    }

    public void setResult(LotteryQueryJuheResult result) {
        this.result = result;
    }
}
