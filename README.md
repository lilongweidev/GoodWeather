![Good Weather](https://github.com/lilongweidev/GoodWeather/blob/master/download/icon_good_weather.png)<br>
<br>
## APP介绍与使用

&emsp;&emsp;本项目没有自己搭建后台，所以自然不会有什么服务器数据库，数据来源于和风天气API，不了解的可以看看我的博客文章，也可以先通过二维码或者下载链接去下载APK体验，使用过程中有问题可以在GitHub上提出，或者在博客中评论。我会在第一时间回复，感谢您的阅读，山高水长，后会有期 ~
   将项目导入到Android Studio 有如下两种方式：
1. 复制 https://github.com/lilongweidev/GoodWeather.git 在AS的导航栏中 VSC → Checkout from Version Control → Git .然后会弹出一个克隆项目的弹窗，粘贴刚才复制的地址，点击弹窗的Clone按钮就可以克隆GitHub上的项目到自己的AS中了。不过这个时候是比较看你的网速的，项目中的很多内容与你本地环境不同，所以需要下载和配置才行。
2. 点击绿色的Code按钮，点击Download ZIP，直接下载压缩包到电脑本地，然后解压，通过AS 的导航栏 File → Open 找到刚才解压之后的文件，然后就是同步和配置即可。

## 答疑

&emsp;&emsp;在Clone源码到本地Android Studio中DeBug运行时，进入地图页面时，发现没有定位或者是无反应，请检查Run下面的日志信息，看有无 "鉴权错误信息" 等字样，如果有的话则说明你需要使用自己项目生成的开发版SHA1,替换掉平台上的应用配置信息，当你看到这个源码之前，你肯定是从博客过来的，那么你可以点击[Android 天气APP（一）开发准备](https://blog.csdn.net/qq_38436214/article/details/105204552)进行百度开发者的账号注册并配置应用，文章里已经讲得很详细了，至于鉴权错误信息的处理你可以通过[Android 百度地图SDK 自动定位、标记定位](https://blog.csdn.net/qq_38436214/article/details/107604267)这篇文章，点击目录“疑问解决”来查看解决方案，祝您使用愉快。

&emsp;&emsp;Windows中GitHub图片显示异常解决方法：打开hosts文件所在目录，C:\Windows\System32\drivers\etc\hosts，默认我们对hosts文件是没有修改权限的，需要开启修改权限，开启修改权限后，用随便用一个编辑器打开，添加的内容请参考这个[hosts](https://github.com/lilongweidev/GoodWeather/blob/master/hosts)文件中最后的部分，对比你的本地进行添加即可。（注意这个开始和结束的标识）

## APP功能描述

&emsp;&emsp;15天天气预报、空气质量、生活建议、出行建议、灾害预警、分钟级降水、城市切换、城市搜索、常用城市、世界国家/地区的城市、壁纸切换、壁纸下载、地图天气、地图搜索定位、每日提醒、语音播报、语音搜索、快捷切换常用城市、应用自动更新、错误监控


## 运行效果图

<div style="width: 100%;
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    flex-wrap: wrap;">
<img title="演示GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/demo.gif" width = "25%" height = "480" alt="演示GIF"/>
<img title="每日提醒GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/everyday_tip.gif" width = "25%" height = "480" alt="每日GIF"/>
<img title="分钟级降水GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/precipitation.gif" width = "25%" height = "480" alt="分钟级降水GIF"/>
<img title="壁纸切换GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/wallpaper.gif" width = "25%" height = "480" alt="壁纸切换GIF"/>
<img title="地图搜索定位GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/map_search.gif" width = "25%" height = "480" alt="地图搜索定位GIF"/>
<img title="自动更新GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/auto_update.gif" width = "25%" height = "480" alt="自动更新GIF"/>
<img title="地图天气GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/map_weather.gif" width = "25%" height = "480" alt="地图天气GIF"/>
<img title="灾害预警GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/warn.gif" width = "25%" height = "480" alt="灾害预警GIF"/>
<img title="更多天气预报GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/more_daily.gif" width = "25%" height = "480" alt="更多天气预报GIF"/>
<img title="更多空气质量GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/more_air.gif" width = "25%" height = "480" alt="更多空气质量GIF"/>
<img title="更多生活指数GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/more_lifestyle.gif" width = "25%" height = "480" alt="更多生活指数GIF"/>
<img title="世界城市GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/world_city.gif" width = "25%" height = "480" alt="世界城市GIF"/>
<img title="常用城市GIF" src="https://github.com/lilongweidev/GoodWeather/blob/master/download/commonly_city.gif" width = "25%" height = "480" alt="常用城市GIF"/>
</div>


GIF看不了的，打开下面的视频地址进行观看<br>
[APP演示视频地址](http://m.v.qq.com/play.html?cid=&vid=m3139pttqgo&vuid24=xHi7q3LxeNTsapddk3QYFg%3D%3D&url_from=share&second_share=0&share_from=copy)<br>

## 网址下载

点击下载[好天气APP](https://www.pgyer.com/z4BytAv7)<br>

## 扫码下载
![下载图片](https://github.com/lilongweidev/GoodWeather/blob/master/download/code.png)<br>

## 天气APP 博客专栏
[天气APP](https://blog.csdn.net/qq_38436214/category_9880722.html)<br>

## 天气APP文章博客(一步一步开发天气APP)
[Android 天气APP（一）开发准备](https://blog.csdn.net/qq_38436214/article/details/105204552)<br>
[Android 天气APP（二）获取定位信息](https://blog.csdn.net/qq_38436214/article/details/105328603)<br>
[Android 天气APP（三）访问天气API与数据请求](https://blog.csdn.net/qq_38436214/article/details/105328657)<br>
[Android 天气APP（四）搭建MVP框架与使用](https://blog.csdn.net/qq_38436214/article/details/105328692)<br>
[Android 天气APP（五）天气预报、生活指数的数据请求与渲染](https://blog.csdn.net/qq_38436214/article/details/105328780)<br>
[Android 天气APP（六）旋转风车显示风力、风向](https://blog.csdn.net/qq_38436214/article/details/105328824)<br>
[Android 天气APP（七）城市切换 之 城市数据源](https://blog.csdn.net/qq_38436214/article/details/105328943)<br>
[Android 天气APP（八）城市切换 之 自定义弹窗与使用](https://blog.csdn.net/qq_38436214/article/details/105328968)<br>
[Android 天气APP（九）细节优化、必应每日一图](https://blog.csdn.net/qq_38436214/article/details/105331216)<br>
[Android 天气APP（十）继续优化、下拉刷新页面天气数据](https://blog.csdn.net/qq_38436214/article/details/105435574)<br>
[Android 天气APP（十一）未来七天的天气预报、逐小时预报、UI优化](https://blog.csdn.net/qq_38436214/article/details/105784966)<br>
[Android 天气APP（十二）空气质量、UI优化调整](https://blog.csdn.net/qq_38436214/article/details/105856012)<br>
[Android 天气APP（十三）仿微信弹窗(右上角加号点击弹窗效果)、自定义背景图片、UI优化调整](https://blog.csdn.net/qq_38436214/article/details/105941708)<br>
[Android 天气APP（十四）修复UI显示异常、优化业务代码逻辑、增加详情天气显示](https://blog.csdn.net/qq_38436214/article/details/106155207)<br>
[Android 天气APP（十五）增加城市搜索、历史搜索记录](https://blog.csdn.net/qq_38436214/article/details/106591788)<br>
[Android 天气APP（十六）热门城市 - 海外城市](https://blog.csdn.net/qq_38436214/article/details/106645543)<br>
[Android 天气APP（十七）热门城市 - 国内城市](https://blog.csdn.net/qq_38436214/article/details/106942710)<br>
[Android 天气APP（十八）常用城市](https://blog.csdn.net/qq_38436214/article/details/106993550)<br>
[Android 天气APP（十九）更换新版API接口（更高、更快、更强）](https://blog.csdn.net/qq_38436214/article/details/107327779)<br>
[Android 天气APP（二十）增加欢迎页及白屏黑屏处理、展示世界国家/地区的城市数据](https://blog.csdn.net/qq_38436214/article/details/107538839)<br>
[Android 天气APP（二十一）滑动改变UI、增加更多天气数据展示，最多未来15天天气预报](https://blog.csdn.net/qq_38436214/article/details/107705898)<br>
[Android 天气APP（二十二）改动些许UI、增加更多空气质量数据和生活建议数据展示](https://blog.csdn.net/qq_38436214/article/details/107867980)<br>
[Android 天气APP（二十三）增加灾害预警、优化主页面UI](https://blog.csdn.net/qq_38436214/article/details/108005938)<br>
[Android 天气APP（二十四）地图天气（上）自动定位和地图点击定位](https://blog.csdn.net/qq_38436214/article/details/108274437)<br>
[Android 天气APP（二十五）地图天气（下）嵌套滑动布局渲染天气数据](https://blog.csdn.net/qq_38436214/article/details/108274498)<br>
[Android 天气APP（二十六）增加自动更新（检查版本、通知栏下载、自动安装）](https://blog.csdn.net/qq_38436214/article/details/108335485)<br>
[Android 天气APP（二十七）增加地图天气的逐小时天气、太阳和月亮数据](https://blog.csdn.net/qq_38436214/article/details/108397121)<br>
[Android 天气APP（二十八）地图搜索定位](https://blog.csdn.net/qq_38436214/article/details/108407945)<br>
[Android 天气APP（二十九）壁纸设置、图片查看、图片保存](https://blog.csdn.net/qq_38436214/article/details/108469321)<br>
[Android 天气APP（三十）分钟级降水](https://blog.csdn.net/qq_38436214/article/details/110469064)<br>
[Android 天气APP（三十一）每日提醒弹窗](https://blog.csdn.net/qq_38436214/article/details/110647965)<br>
[Android 天气APP（三十二）快捷切换常用城市](https://blog.csdn.net/qq_38436214/article/details/113498027)<br>
[Android 天气APP（三十三）语音播报](https://blog.csdn.net/qq_38436214/article/details/113544891)<br>
[Android 天气APP（三十四）语音搜索](https://blog.csdn.net/qq_38436214/article/details/113699279)<br>
[Android 天气APP（三十五）修复BUG、升级网络请求框架](https://blog.csdn.net/qq_38436214/article/details/119295627)<br>
[Android 天气APP（三十六）运行到本地AS、更新项目版本依赖、去掉ButterKnife](https://blog.csdn.net/qq_38436214/article/details/127673536)<br>
[Android 天气APP（三十七）新版AS编译、更新镜像源、仓库源、修复部分BUG](https://blog.csdn.net/qq_38436214/article/details/143829196)<br>

文章代码是一篇一篇进行迭代的，也欢迎读者提供新的需求，适当采纳，更好的优化这个项目<br>

联系邮箱：lonelyholiday@qq.com<br>

博主：[初学者-Study](https://blog.csdn.net/qq_38436214)<br>

## 版本说明
### V 3.0 (最新版本)
* 新版AS正常编译、更新依赖仓库源，解决部分依赖库在新版AS上报错的问题、解决地图鉴权问题、搜索城市重名问题。
### V 2.9
* 新版AS正常编译、更新项目版本依赖、去掉ButterKnife，使用ViewBinding<br>
### V 2.8
* 增加友盟+性能监控SDK<br>
### V 2.7 
* 修复UI显示问题、资源图片处理、优化网络请求框架<br>
### V 2.6 
* 优化用户体验，新增快捷切换城市、语音播报、语音搜索功能<br>
### V 2.5
* 分钟级降水、每日提醒<br>
### V 2.4 
* 新版壁纸管理、支持网络图片下载、优化页面UI<br>
### V 2.3 
* 新增地图逐小时天气、日出日落、月升月落、地图搜索定位<br>
### V 2.2
* 新增应用自动更新<br>
### V 2.1
* 新增地图天气<br>
### V 2.0
* 新增灾害预警功能，主页面UI优化<br>
### V 1.9
* 新增更多天气预报、空气质量、生活建议的数据、优化主页UI <br>
### V 1.8
* 更新和风天气V7版API,删除热门城市、新增全球城市。启动页优化，黑白屏处理<br>
### V 1.7
* 增加常用城市列表<br>
### V 1.6 
* 增加国内热门城市<br>
### V 1.5
* 增加海外热门城市、新的天气UI效果<br>
### V 1.4
* 增加搜索城市功能、历史搜索记录<br>
### V 1.3
* 修复相关异常BUG,增加天气详情数据<br>
### V 1.2
* 增加用户体验，允许自己修改背景<br>
### V 1.1
* 七天天气预报、逐小时天气预报、界面UI优化<br>
### V 1.0
* 初始版本、三天天气预报、空气质量、城市切换、生活指数<br>
