window.addEventListener('load',function() {
    // 显示或隐藏左右箭头
    // 1、获取元素
    var arrow_l = document.querySelector('.arrow-l');
    var arrow_r = document.querySelector('.arrow-r');
    var focus = document.querySelector('.focus');
    // 获取focus盒子宽度
    var focusWidth = focus.offsetWidth - 1;
    // 2、鼠标经过focus就显示或隐藏按钮
    focus.addEventListener('mouseenter',function() {
        arrow_l.style.display = 'block';
        arrow_r.style.display = 'block';
        clearInterval(timer);
        timer = null;
    })
    focus.addEventListener('mouseleave',function() {
        arrow_l.style.display = 'none';
        arrow_r.style.display = 'none';
        var timer = setInterval(function() {
            // 自动播放就相当于点击了右侧按钮
            arrow_r.click();
        },2000);
    })


    // 根据图片数确定下面的圆圈数
    var ul = focus.querySelector('ul');
    var ol = focus.querySelector('.circle');
    for(var i=0;i< ul.children.length;i++) {
        // 创建一个li
        var li = document.createElement('li');
        // 设置小圆圈的索引号
        li.setAttribute('index',i);
        // 往ol里插入ol
        ol.appendChild(li);
        // 可以在生成小圆圈的同时绑定一个点击事件，
        li.addEventListener('click',function() {
            // 清楚所有li的类名
            for(var i=0;i < ol.children.length;i++) {
                ol.children[i].className = '';
            }
            // 将自己的类名设置为current
            this.className = 'current';
            // 点击小圆圈，移动图片，移动的是ul而不是li
            // ul的移动距离就是小圆圈的索引号乘以图片宽度，而且是负值
            
            // 当点击了某个li，就拿到这个li的索引号
            var index = this.getAttribute('index');
            // 当点击了某个li，就把这个li的索引号给num
            num = index;
            // 也要把这个索引号给circle
            circle = index;
            animate(ul,- index * focusWidth);
        })
    }
    // 让第一个li设置类名为current
    ol.children[0].className = 'current';

    // 克隆第一张图片放到最后面，克隆最后一张图片放到最前面，便于后续的箭头移动图片
    var first = ul.children[0].cloneNode(true);
    ul.appendChild(first);


    // 点击箭头按钮，移动图片
    var num = 0;
    // circle 控制小圆圈
    var circle = 0;
    // flag 节流阀
    var flag = true;
    // 图片无缝滚动原理：
    // 把ul第一个li复制一份到最后面
    // 当图片滚动到克隆的最后一张时，让ul快速的、无动画的跳到最左侧，也就是left=0
    // 同时num=0，重新循环图片

    function circleChange() {
        // 先清楚其余小圆圈的current类名
        for(var i=0;i < ol.children.length;i++) {
            ol.children[i].className = '';
        }
        // 留下当前小圆圈的current类名
        ol.children[circle].className = 'current';
    }

    // 右侧按钮
    arrow_r.addEventListener('click',function() {
        if(flag) {
            flag = false; // 关闭节流阀
            // 如果走到了最后一张图片，这时，ul要快速回到第一张
            if(num == ul.children.length - 1) {
                ul.style.left = 0;
                num = 0;
            }
            num++;
            animate(ul,- num * focusWidth,function() {
                flag = true;
            });
            // 点击右侧按钮，小圆圈跟随着一起变化
            circle++;
            // 如果circle=3，说明走到了最后,应该复原为0
            if(circle == ol.children.length) {
                circle = 0;
            }
            circleChange();
        }
        
    });

    // 左侧按钮
    arrow_l.addEventListener('click',function() {
        if(flag) {
            flag = false;
            // 如果走到了最后一张图片，这时，ul要快速回到第一张
            if(num == 0) {
                num = ul.children.length - 1;
                ul.style.left = - num * focusWidth + 'px';
            
            }
            num--;
            animate(ul,- num * focusWidth,function() {
                flag = true;
            });
            // 点击右侧按钮，小圆圈跟随着一起变化
            circle--;
            // 如果circle<0，说明走到了第一个,应该复原为最后一个
            if(circle < 0) {
                circle = ol.children.length - 1;
            }
            circleChange();
        }
        
    });


    // 自动播放
    var timer = setInterval(function() {
        // 自动播放就相当于点击了右侧按钮
        arrow_r.click();
    },2000);
})