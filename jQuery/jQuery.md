## JavaScript库
### 仓库
可以把很多东西都放到这个仓库里，当有需要的时候就可以直接到仓库查找使用即可
### JavaScript库
即library，是一个封装好的特定的集合(方法和函数)，在这个库里，封装了很多预先定义好的函数在里面，比如动画、获取元素等等，体现出来的就是一个JS文件，里面封装了一些原生js代码。
### 常见的JavaScript库
常见的有很多，分别有着不同的使用范围，比如
<ul>
    <li>jQuery</li>
    <li>Prototype</li>
    <li>YUI</li>
    <li>Dojo</li>
    <li>Ext JS</li>
    <li>移动端的zepto</li>
</ul>

这些库都是对原生JavaScript的封装，内部都是用JavaScript实现的
##　jQuery的概念
jQuery是一个快速、简洁的JavaScript库，设计宗旨是“write less，DoMore”，倡导写最少的代码，做最多的事
<br/>
j：JavaScript
<br/>
Query：查询
<br/>
总的意思就是查询js，将DOM操作封装起来，能够快速的查询和使用里面的功能
<br/>
jQuery封装了JavaScript常用的功能代码，优化了DOM操作、事件处理、动画设计和Ajax交互
<br/>
学习jQuery的本质：学习调用这些函数(方法)
### 优点
加快开发速度，能够很方便的调用和使用它，从而提高开发效率
<ul>
    <li>轻量级，核心文件几十KB，不影响页面加载速度</li>
    <li>跨浏览器兼容，基本兼容现在主流浏览器</li>
    <li>链式编程，隐式迭代</li>
    <li>对事件、样式、动画支持，大大简化了DOM操作</li>
    <li>支持插件扩展开发，有者丰富的第三方插件，比如树形菜单、日期控件、轮播图等</li>
    <li>免费，开源</li>
</ul>

## jQuery的安装
可以通过两种方法来添加jQuery
### 下载jQuery库
从官网下载jQuery库到本地进行使用
<br/>
官网有两种版本
<ul>
    <li>Production version - 用于实际的网站，已经被精简和压缩过，可读性差</li>
    <li>Development version - 用于测试和开发，未压缩，可读</li>
</ul>

### 通过CDN引用
Staticfile CDN、百度、又拍云、新浪、谷歌和微软的服务器都存有jQuery
<br/>
国内推荐百度、又拍云、新浪等，国外推荐谷歌和微软
#### Staticfile CDN
```
<head>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js">
</script>
</head>
```
#### 百度 CDN
```
<head>
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js">
</script>
</head>
```
#### 又拍云
```
<head>
<script src="https://upcdn.b0.upaiyun.com/libs/jquery/jquery-2.0.2.min.js">
</script>
</head>
```
#### 新浪
```
<head>
<script src="https://lib.sinaapp.com/js/jquery/2.0.2/jquery-2.0.2.min.js">
</script>
</head>
```
## jQuery的基本使用
两种入口函数
```
// 1、等着页面DOM加载完毕在执行js代码
$(document).ready(function() {
    $('div').hide();
})
```
```
// 2、等着页面DOM加载完毕在执行js代码
$(function() {
    $('div').hide();
})
```
都是等页面文档、外部js文件、css文件、图片加载完毕后再执行内部代码
<br/>
**一般用第二个**
## 顶级对象$
#### $是jQuery的别称
在代码中可以使用jQuery来代替$,但一般为了方便，都是使用$
#### jQuery的顶级对象
相当于原生JavaScript的window，把元素利用$ 包装成jQuery对象，就可以调用jQuery 的方法
## jQuery对象和DOM对象
用原生JS获取来的对象就是DOM对象
<br/>
jQuery方法获取的元素就是jQUery对象
<br/>
jQuery对象的本质是：利用$ 对DOM对象包装后产生的对象(以伪数组形式存储)
### 相互转换
DOM对象比jQUery对象之间是可以相互转化的
<br/>
因为原生JS比jQuery更大，所以原生的一些属性和方法在jQuery中没有进行封装，那么想要使用这些属性和方法就需要把jQuery对象转换为DOM对象才能使用
#### DOM对象 -> jQuery对象
```
$(DOM对象)
```
#### jQuery对象 -> DOM对象
index是索引号
```
$('div')[index]
```
```
$('div').get(index)
```
## jQuery选择器
### jQuery基础选择器
<table>
    <tr>
        <th>名称</th>
        <th>用法</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>ID选择器</td>
        <td>$("#id")</td>
        <td>获取指定ID的元素</td>
    </tr>
    <tr>
        <td>全选选择器</td>
        <td>$('*')</td>
        <td>匹配所有元素</td>
    </tr>
    <tr>
        <td>类选择器</td>
        <td>$(".class")</td>
        <td>获取同一类class的元素</td>
    </tr>
    <tr>
        <td>标签选择器</td>
        <td>$("div")</td>
        <td>获取同一类标签的所有元素</td>
    </tr>
    <tr>
        <td>并集选择器</td>
        <td>$(div,p,li")</td>
        <td>选取多个元素</td>
    </tr>
    <tr>
        <td>交集选择器</td>
        <td>$("li.current")</td>
        <td>交集元素</td>
    </tr>
</table>

### jQuery层级选择器
<table>
    <tr>
        <th>名称</th>
        <th>用法</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>子代选择器</td>
        <td>$("ul>li")</td>
        <td>使用>号，获取亲儿子层级的元素，但不会获取孙子层级的元素</td>
    </tr>
    <tr>
        <td>后代选择器</td>
        <td>$("ul li")</td>
        <td>使用空格，代表后代选择器，获取ul下的所有li元素，包括孙子等</td>
    </tr>
</table>

### 筛选选择器
<table>
    <tr>
        <th>语法</th>
        <th>用法</th>
        <th>描述</th>
    </tr>
    <tr>
        <td>:first</td>
        <td>$('li:first')</td>
        <td>获取第一个li元素</td>
    </tr>
    <tr>
        <td>:last</td>
        <td>$('li:last')</td>
        <td>获取最后一个li元素</td>
    </tr>
    <tr>
        <td>:eq(index)</td>
        <td>$("li:eq(2)")</td>
        <td>获取到的li元素中，徐娜则索引号为2的元素，索引号从0开始</td>
    </tr>
    <tr>
        <td>:odd</td>
        <td>$("li:odd")</td>
        <td>获取到的li元素中，选择索引号为奇数的元素</td>
    </tr>
    <tr>
        <td>:even</td>
        <td>$("li:even")</td>
        <td>获取到的li元素中，选择索引号为偶数的元素</td>
    </tr>
</table>

### jQUery筛选方法
<table>
    <tr>
        <th>语法</th>
        <th>用法</th>
        <th>说明</th>
    </tr>
    <tr>
        <td>parent()</td>
        <td>$("li").parent();</td>
        <td>查找父级</td>
    </tr>
    <tr>
        <td>children(selector)</td>
        <td>$("ul").children("li")</td>
        <td>相当于$("ul>li")，最近一级(亲儿子)</td>
    </tr>
    <tr>
        <td>find(selector)</td>
        <td>$("ul").find("li");</td>
        <td>相当于$("ul li")，后代选择器</td>
    </tr>
    <tr>
        <td>siblings(selector)</td>
        <td>$(".first").siblings("li");</td>
        <td>查找兄弟节点，不包括自己本身</td>
    </tr>
    <tr>
        <td>nextAll([expr])</td>
        <td>$(".first").nextAll()</td>
        <td>查找当前元素之后所有的同辈元素</td>
    </tr>
    <tr>
        <td>prevAll([expr])</td>
        <td>$("first").prevAll()</td>
        <td>查找当前元素之前所有的同辈元素</td>
    </tr>
    <tr>
        <td>hasClass(class)</td>
        <td>$('div').hasClass("protected")</td>
        <td>检查当前的元素是否含有某个特定的类，如果有，返回true</td>
    </tr>
    <tr>
        <td>eq(index)</td>
        <td>$("li").eq(2)</td>
        <td>相当于$("li:eq(2)")，index从0开始</td>
    </tr>
</table>

## 隐式迭代
编译内部DOM元素的过程就叫做隐式迭代
<br/>
简单理解：给匹配到的所有元素进行循环遍历，执行相应的方法，而不用我们再进行循环，能够简化操作
## jQuery操作样式
### 操作css方法
jQuery可以使用css方法来修改简单元素样式，也可以操作类，修改多个样式
<br/>
1、参数只写属性名，则是返回属性值
```
$(this).css("color");
```
2、参数是属性名，属性值，逗号分隔，是设置一组样式，属性必须加引号(不加会被认为是变量)，值如果是数组可以不加单位或引号
```
$(this).css("color","red");
```
3、参数可以是对象形式，方便设置多组样式，属性名和属性值用冒号隔开
```
$(this).css({"color":"white","font-size":"20px"});
```
### 设置类样式方法
前面改变css还是比较复杂，可以先在css中定义一个类样式，用到的时候直接对元素添加这个类样式
<br/>
1、添加类
```
$("div").addClass("current");
```
2、移除类
```
$("div").removeClass("current");
```
3、切换类
<br/>
如果没有这个类名就加上，如果有了就去掉
```
$("div").toggleClass("current");
```
#### 类操作和className的区别
原生JS中的className会覆盖元素原先的类名
<br/>
jQuery的类操作只对指定类进行操作，不会影响原先的类名
## jQuery动画效果
显示隐藏
<ul>
    <li>show()</li>
    <li>hide()</li>
    <li>toggle()</li>
</ul>
滑动
<ul>
    <li>slideDown()</li>
    <li>slideUp()</li>
    <li>slideToggle()</li>
</ul>
淡入淡出
<ul>
    <li>fadeIn()</li>
    <li>fadeOut()</li>
    <li>fadeToggle()</li>
    <li>fadeTo()</li>
</ul>
自定义动画
<ul>
    <li>animate()</li>
</ul>

**详情查看jQuery中文文档API**
### 事件切换
```
hover(function() {},function() {});
```
第一个函数是鼠标经过触发的函数，第二个函数是鼠标离开触发的函数，见案例‘下拉菜单’
### 问题1
#### 问题
动画效果队列，动画一旦触发就会执行，而且是必须执行完，所以如果多次触发就会造成多个动画排队依次执行
#### 解决
那么就让这个动画停止排队
```
stop()
```
用于停止动画或效果，需要写在动画或效果的前面，用来停止上一次执行的动画，如果上一次的动画正在执行，那么就会立即停止
## jQuery属性操作
### 设置或获取元素的固有属性  prop()
元素固有属性就是元素本身自带的属性
#### 获取属性语法
```
prop("属性")
```
#### 设置属性语法
```
prop("属性","属性值")
```
### 设置或获取元素的自定义属性   attr()
用户自定义给元素添加的属性，称为自定义属性
<br/>
该方法也可以获取H5的自定义属性(带前缀data-)
#### 获取属性语法
```
attr("属性")  // 类似原生getAttribute()
```
#### 设置属性语法
```
attr("属性","属性值")  //类似原生setAttribute()
```
### 数据缓存  data()
data()可以在指定元素上存取数据，并不会修改DOM元素结构，一旦页面刷新，存放的数据也消失
<br/>
也可以读取H5自定义属性data-index，得到的是数据类型
#### 附加数据语法
```
data("name","value");  // 向被选中元素添加数据
```
#### 获取数据语法
```
data("name") // 向被选中元素获取数据
```
## jQuery内容文本值
### 普通元素内容  html()
相当于原生innerHTML
```
html()  // 获取元素的内容
html("内容")  // 设置元素的内容
```
### 普通元素文本内容  text()
相当于原生  innerText
```
text()  // 获取元素的文本内容
text("内容")  // 设置元素的文本内容
```
### 获取设置表单值
```
val()  // 获取该表单元素的值
val("内容")  // 设置该表单元素的值
```
## jQuery元素操作
jQuery隐式迭代是对同一类元素做相同的操作，如果想要对同一类元素做不同操作，就需要用到遍历
### each()
语法1
```
$("div").each(function (index,domEle) { xxx; })
```
> 1、each方法遍历匹配的每一个元素，主要用DOM处理，each每一个 
<br/>
> 2、里面的回调函数有两个参数，index是每个元素的索引号，demEle是每个DOM元素对象，不是jQuery对象
<br/>
> 3、所以若干要使用jQuery方法，就需要将这个DOM元素对象转换为jQuery对象

语法2
```
$.each(object,function (index,domEle) { xxx; })
```
> 1、$.each()方法可用于遍历任何对象，主要用于数据处理，比如数组、对象
<br/>
> 2、里面的函数有两个参数，index是索引号，element是遍历内容

## jQuery事件
### jQuery事件注册
语法
```
element.事件(function() {})
```
```
$("div").click(function() {
    事件处理程序
    })
```
### jQuery事件处理
on()  方法在匹配元素上绑定一个或多个事件的事件处理函数
<br/>
语法
```
element.on(events,[selector],fn)
```
> 1、events：一个或多个用空格分隔的事件类型，比如click等
<br/>
> selector：元素的子元素选择器
<br/>
> fn：回调函数  即绑定在元素身上的侦听函数

#### 优点
可以绑定多个事件，多个处理事件处理程序
```
$("div").on({
mouseenter: function() {
    $(this).css("background","purple");
},
click: function() {
    $(this).css("background","skyblue");
},
mouseleave: function() {
    $(this).css("background","blue");
}
});
```
如果事件处理程序相同
```
$("div").on("mouseenter mouseleave",function() {
    $(this).toggleClass("current");
});
```
可以事件委派操作，事件委派就是：把原来加给子元素身上的事件绑定在父元素身上，就是把事件委派给父元素
```
$("ol").on("click","li",function() {
    alert(11);
})
```
第二个参数是子选择器，就可以将子元素的事件绑定给父元素
<br/>
click 是绑定在ul上，但是触发的是ul里的li
### jQuery事件解绑
off() 方法可以移除通过on()方法添加的事件处理程序
```
$("div").off()  // 解绑div元素的所有事件处理程序
$("div").off("click") // 解绑div元素上的点击事件
$("ul").off("click","li") // 解绑事件委托
```
#### one() 指触发一次的事件
用one()注册的事件只触发一次
```
$("p").one("click",function() {
    alert(11);
})
```
### jQuery自动触发事件
有些事件希望自动触发，比如轮播图的自动播放功能，可以利用定时器自动触发按钮点击事件。
<br/>
自动触发有三种方式
<br/>
1、element.事件()
```
element.click()
```
2、element.trigger("事件")
```
$("div").trigger("click");
```
3、element.triggerHandler(type)
```
$("div").triggerHandler("click");
```
第三种和第二种的区别是第三种不会触发元素的默认行为(比如点击文本框出现光标就是一个默认行为)
## jQuery事件对象
事件一旦被触发就会有事件对象(event)的产生
```
$("div").on("click",function(event) {
    console.log(event);
})
```
通过事件对象可以做很多事情，比如阻止默认行为，阻止冒泡等
<br/>
阻止默认行为
```
event.preventDefault() 或者 return false
```
阻止冒泡
```
event.stopPropagation()
```
## jQuery其他方法
### 拷贝对象
可以将某个对象拷贝给另外一个对象使用
#### 语法
```
$.extend([deep],target,object1,[objectN])
```
> deep：如果设为truw为深拷贝，默认位false  浅拷贝
<br/>
> target：要拷贝的目标对象
> object1：待拷贝到第一个对象的对象

#### 浅拷贝
是把被拷贝的对象复杂数据类型中的地址拷贝给目标对象，修改目标对象影响被拷贝对象
#### 深拷贝
完全克隆(拷贝的是对象，不是地址了)，修改目标对象不影响被拷贝对象，如果有不冲突的属性，会合并到一起
## jQuery多库共存
### 问题
jQuery使用$作为标识符，但其他的js库也会使用$作为标识符，这样就会引发冲突
### 需求
需要有一个解决方案，让jQuery与其他的js库不存在冲突，可以同时存在，这就叫做多库共存
### 解决
1、把$改成jQuery
<br/>
2、jQuery变量规定新的名称
<br/>
比如
```
$.noConflict()
var xx = $.noConfilict()
// 然后就可以用xx来代替$
```


