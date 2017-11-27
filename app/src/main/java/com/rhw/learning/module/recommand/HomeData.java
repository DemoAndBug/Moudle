package com.rhw.learning.module.recommand;

import java.util.List;

/**
 * Author:renhongwei
 * Date:2017/11/27 on 14:43
 */
public class HomeData {
    /**
     * ecode : 0
     * emsg :
     * data : {"list":[{"type":2,"logo":"http://v1.qzone.cc/avatar/201305/17/22/06/519639762c4e6138.jpg%21200x200.jpg","title":"tb73399384","info":"1","price":"$1200","text":"Not all milk is called telunsu.","from":" 来自北京|新中关","zan":"5","url":["http://static.i3.xywy.com/cms/20150630/6f14ca84cc1b4eef17347c19220e938067792.jpg"]},{"type":0,"logo":"http://img5.imgtn.bdimg.com/it/u=392404473,139106390&fm=21&gp=0.jpg","title":"百事可乐","info":"2","text":"喝健康的饮料，百事是您最好的选择.","site":"http://www.crbeverage.com/","from":"来自南京","zan":"10","resource":"http://fairee.vicp.net:83/2016rm/0116/baishi160116.mp4","adid":"00000001112","clickUrl":"https://www.baidu.com","clickMonitor":[{"ver":"0","url":"http://imooc.com/click??click=1"},{"ver":"0","url":"http://imooc.com/click?click=2"}],"startMonitor":[{"ver":"0","url":"http://imooc.com/show?impression=1"},{"ver":"0","url":"http: //imooc.com/show?impression=2"}],"middleMonitor":[{"ver":"0","url":"http://imooc.com/show?SU=1","time":5},{"ver":"0","url":"http: //imooc.com/show?impression=2","time":5}],"endMonitor":[{"ver":"0","url":"http://imooc.com/show?end=1","time":5},{"ver":"0","url":"http: //imooc.com/show?end=2","time":5}]},{"type":1,"logo":"http://img3.duitang.com/uploads/item/201407/01/20140701222607_AnKfj.thumb.224_0.jpeg","title":"qndroid","info":"2","price":"$2400","text":"来慕课网,你可以学到你想学的知识.我们负责教,你只负责学","from":" 来自北京|德外大街","zan":"5","url":["http://i3.letvimg.com/lc01_yunzhuanma/201501/28/01/40/7bf797683a0bfb4f6f68f7f043e11f28_26451225/thumb/2_400_300.jpg","http://www.0512cixiu.com/images/goods/20100619/d01b623130f60d61.jpg","http://img.diyibu.cn/Scenery/2012/01/11/w_400x300_img6410_1.png"]},{"type":2,"logo":"http://img0.imgtn.bdimg.com/it/u=3266845821,3017593921&fm=21&gp=0.jpg","title":"臭脚0000","info":"1","price":"$1199","text":"360n4s换红鬼蓝,已用一周, 发票齐全 360系统和性价比有目共睹 比红米Note4还好 想试试新的系统 最好是本市当面交易.","from":"来自珠海","zan":"2","url":["http://img1.juimg.com/141125/330711-14112510312937.jpg"]},{"type":1,"logo":"http://p4.gexing.com/G1/M00/7A/CB/rBABFFHBNcXw7aeWAAAgN1Nr8U8904_200x200_3.jpg","title":"淡淡兰芝香","info":"20","price":"$40","text":"日本代购 西松公司正品 夏三角女衣宝宝 朋友日本代购回来的 一包原本有三件 自家宝宝用了一件还有两件全新的 有需要的话速度电话联系.","from":"来自无锡","zan":"1","url":["http://img4.imgtn.bdimg.com/it/u=78750511,2290522953&fm=21&gp=0.jpg","http://img3.imgtn.bdimg.com/it/u=2064559703,2831223266&fm=21&gp=0.jpg","http://img0.imgtn.bdimg.com/it/u=2871948638,2415344018&fm=21&gp=0.jpg"]},{"type":2,"logo":"http://awb.img.xmtbang.com/img/uploadnew/201510/23/ccaeb2d2abe94fa6b3efe014e9146e94.jpg","title":"xiaopangzi","info":"3","price":"$130","text":"周杰伦大连演唱会门票第五排好位置转让周杰伦演唱会门票两张3000+买的第五排的好位置不是黄牛买多了所以转让请大家放心最好本市.","from":"来自大连","zan":"3","url":["http://img2.imgtn.bdimg.com/it/u=1550844674,2435756484&fm=21&gp=0.jpg"]}]}
     */

    private String ecode;
    private String emsg;
    private DataBean data;

    public String getEcode() {
        return ecode;
    }

    public void setEcode(String ecode) {
        this.ecode = ecode;
    }

    public String getEmsg() {
        return emsg;
    }

    public void setEmsg(String emsg) {
        this.emsg = emsg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * type : 2
             * logo : http://v1.qzone.cc/avatar/201305/17/22/06/519639762c4e6138.jpg%21200x200.jpg
             * title : tb73399384
             * info : 1
             * price : $1200
             * text : Not all milk is called telunsu.
             * from :  来自北京|新中关
             * zan : 5
             * url : ["http://static.i3.xywy.com/cms/20150630/6f14ca84cc1b4eef17347c19220e938067792.jpg"]
             * site : http://www.crbeverage.com/
             * resource : http://fairee.vicp.net:83/2016rm/0116/baishi160116.mp4
             * adid : 00000001112
             * clickUrl : https://www.baidu.com
             * clickMonitor : [{"ver":"0","url":"http://imooc.com/click??click=1"},{"ver":"0","url":"http://imooc.com/click?click=2"}]
             * startMonitor : [{"ver":"0","url":"http://imooc.com/show?impression=1"},{"ver":"0","url":"http: //imooc.com/show?impression=2"}]
             * middleMonitor : [{"ver":"0","url":"http://imooc.com/show?SU=1","time":5},{"ver":"0","url":"http: //imooc.com/show?impression=2","time":5}]
             * endMonitor : [{"ver":"0","url":"http://imooc.com/show?end=1","time":5},{"ver":"0","url":"http: //imooc.com/show?end=2","time":5}]
             */

            private int type;
            private String logo;
            private String title;
            private String info;
            private String price;
            private String text;
            private String from;
            private String zan;
            private String site;
            private String resource;
            private String adid;
            private String clickUrl;
            private List<String> url;
            private List<ClickMonitorBean> clickMonitor;
            private List<StartMonitorBean> startMonitor;
            private List<MiddleMonitorBean> middleMonitor;
            private List<EndMonitorBean> endMonitor;

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getInfo() {
                return info;
            }

            public void setInfo(String info) {
                this.info = info;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getZan() {
                return zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public String getSite() {
                return site;
            }

            public void setSite(String site) {
                this.site = site;
            }

            public String getResource() {
                return resource;
            }

            public void setResource(String resource) {
                this.resource = resource;
            }

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public String getClickUrl() {
                return clickUrl;
            }

            public void setClickUrl(String clickUrl) {
                this.clickUrl = clickUrl;
            }

            public List<String> getUrl() {
                return url;
            }

            public void setUrl(List<String> url) {
                this.url = url;
            }

            public List<ClickMonitorBean> getClickMonitor() {
                return clickMonitor;
            }

            public void setClickMonitor(List<ClickMonitorBean> clickMonitor) {
                this.clickMonitor = clickMonitor;
            }

            public List<StartMonitorBean> getStartMonitor() {
                return startMonitor;
            }

            public void setStartMonitor(List<StartMonitorBean> startMonitor) {
                this.startMonitor = startMonitor;
            }

            public List<MiddleMonitorBean> getMiddleMonitor() {
                return middleMonitor;
            }

            public void setMiddleMonitor(List<MiddleMonitorBean> middleMonitor) {
                this.middleMonitor = middleMonitor;
            }

            public List<EndMonitorBean> getEndMonitor() {
                return endMonitor;
            }

            public void setEndMonitor(List<EndMonitorBean> endMonitor) {
                this.endMonitor = endMonitor;
            }

            public static class ClickMonitorBean {
                /**
                 * ver : 0
                 * url : http://imooc.com/click??click=1
                 */

                private String ver;
                private String url;

                public String getVer() {
                    return ver;
                }

                public void setVer(String ver) {
                    this.ver = ver;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class StartMonitorBean {
                /**
                 * ver : 0
                 * url : http://imooc.com/show?impression=1
                 */

                private String ver;
                private String url;

                public String getVer() {
                    return ver;
                }

                public void setVer(String ver) {
                    this.ver = ver;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }

            public static class MiddleMonitorBean {
                /**
                 * ver : 0
                 * url : http://imooc.com/show?SU=1
                 * time : 5
                 */

                private String ver;
                private String url;
                private int time;

                public String getVer() {
                    return ver;
                }

                public void setVer(String ver) {
                    this.ver = ver;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getTime() {
                    return time;
                }

                public void setTime(int time) {
                    this.time = time;
                }
            }

            public static class EndMonitorBean {
                /**
                 * ver : 0
                 * url : http://imooc.com/show?end=1
                 * time : 5
                 */

                private String ver;
                private String url;
                private int time;

                public String getVer() {
                    return ver;
                }

                public void setVer(String ver) {
                    this.ver = ver;
                }

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }

                public int getTime() {
                    return time;
                }

                public void setTime(int time) {
                    this.time = time;
                }
            }
        }
    }
}