window.addEventListener('load',function() {
    var s_ids = document.querySelectorAll('.s_id');
    var s_idps = document.querySelectorAll('.s_idp');
    var c_ids = document.querySelectorAll('.c_id');
    var c_idps = document.querySelectorAll('.c_idp');

    // 查询指定学生信息
    s_ids[0].addEventListener('focus',function() {
        s_idps[0].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[0].style.color = 'black';
    })
    s_ids[0].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[0].value)) {
            s_idps[0].innerHTML = '提示：输入正确';
            s_idps[0].style.color = 'green';
        } else {
            s_idps[0].innerHTML = '提示：输入错误';
            s_idps[0].style.color = 'red';
        }
    })
    
    // 查询学生成绩
    s_ids[1].addEventListener('focus',function() {
        s_idps[1].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[1].style.color = 'black';
    })
    s_ids[1].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[1].value)) {
            s_idps[1].innerHTML = '提示：输入正确';
            s_idps[1].style.color = 'green';
        } else {
            s_idps[1].innerHTML = '提示：输入错误';
            s_idps[1].style.color = 'red';

        }
    })

    // 查询课程成绩
    c_ids[0].addEventListener('focus',function() {
        c_idps[0].innerHTML = '提示：请输入3位数字的课程号，否则输入无效';
        c_idps[0].style.color = 'black';
    })
    c_ids[0].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(c_ids[0].value)) {
            c_idps[0].innerHTML = '提示：输入正确';
            c_idps[0].style.color = 'green';
        } else {
            c_idps[0].innerHTML = '提示：输入错误';
            c_idps[0].style.color = 'red';

        }
    })

    // 查询学生最好最差成绩排名
    s_ids[2].addEventListener('focus',function() {
        s_idps[2].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[2].style.color = 'black';
    })
    s_ids[2].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[2].value)) {
            s_idps[2].innerHTML = '提示：输入正确';
            s_idps[2].style.color = 'green';
        } else {
            s_idps[2].innerHTML = '提示：输入错误';
            s_idps[2].style.color = 'red';

        }
    })

    // 查询成绩差值
    s_ids[3].addEventListener('focus',function() {
        s_idps[3].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[3].style.color = 'black';
    })
    s_ids[3].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[3].value)) {
            s_idps[3].innerHTML = '提示：输入正确';
            s_idps[3].style.color = 'green';
        } else {
            s_idps[3].innerHTML = '提示：输入错误';
            s_idps[3].style.color = 'red';

        }
    })
    c_ids[1].addEventListener('focus',function() {
        c_idps[1].innerHTML = '提示：请输入3位数字的课程号号，否则输入无效';
        c_idps[1].style.color = 'black';
    })
    c_ids[1].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(c_ids[1].value)) {
            c_idps[1].innerHTML = '提示：输入正确';
            c_idps[1].style.color = 'green';
        } else {
            c_idps[1].innerHTML = '提示：输入错误';
            c_idps[1].style.color = 'red';

        }
    })

    // 学生信息录入
    s_ids[4].addEventListener('focus',function() {
        s_idps[4].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[4].style.color = 'black';
    })
    s_ids[4].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[4].value)) {
            s_idps[4].innerHTML = '提示：输入正确';
            s_idps[4].style.color = 'green';
        } else {
            s_idps[4].innerHTML = '提示：输入错误';
            s_idps[4].style.color = 'red';

        }
    })
    var s_name = document.querySelector('.s_name');
    var s_namep = document.querySelector('.s_namep');
    s_name.addEventListener('focus',function() {
        s_namep.innerHTML = '提示：请输入有效的学生姓名，否则输入无效';
        s_namep.style.color = 'black';
    })
    s_name.addEventListener('keyup',function() {
        var reg = /^[\u4e00-\u9fa5]{2,6}$/;
        if(reg.test(s_name.value)) {
            s_namep.innerHTML = '提示：输入正确';
            s_namep.style.color = 'green';
        } else {
            s_namep.innerHTML = '提示：输入错误';
            s_namep.style.color = 'red';
        }
    })
    var s_age = document.querySelector('.s_age');
    var s_agep = document.querySelector('.s_agep');
    s_age.addEventListener('focus',function() {
        s_agep.innerHTML = '提示：请输入有效的学生姓名，否则输入无效';
        s_agep.style.color = 'black';
    })
    s_age.addEventListener('keyup',function() {
        var reg = /^\d{1,2}$/;
        if(reg.test(s_age.value)) {
            s_agep.innerHTML = '提示：输入正确';
            s_agep.style.color = 'green';
        } else {
            s_agep.innerHTML = '提示：输入错误';
            s_agep.style.color = 'red';
        }
    })

    // 学生成绩录入
    var java = document.querySelector('.java');
    var grades = document.querySelectorAll('.grade');
    var gradeps = document.querySelectorAll('.gradep');
    s_ids[5].addEventListener('focus',function() {
        s_idps[5].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[5].style.color = 'black';
    })
    s_ids[5].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[5].value)) {
            s_idps[5].innerHTML = '提示：输入正确';
            s_idps[5].style.color = 'green';
        } else {
            s_idps[5].innerHTML = '提示：输入错误';
            s_idps[5].style.color = 'red';

        }
    })
    grades[0].addEventListener('focus',function() {
        gradeps[0].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[0].style.color = 'black';
    })
    grades[0].addEventListener('keyup',function() {
        var reg = /^(\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[0].value)) {
            gradeps[0].innerHTML = '提示：输入正确';
            gradeps[0].style.color = 'green';
        } else {
            gradeps[0].innerHTML = '提示：输入错误';
            gradeps[0].style.color = 'red';
        }
    })
    grades[1].addEventListener('focus',function() {
        gradeps[1].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[1].style.color = 'black';
    })
    grades[1].addEventListener('keyup',function() {
        var reg = /^(\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[1].value)) {
            gradeps[1].innerHTML = '提示：输入正确';
            gradeps[1].style.color = 'green';
        } else {
            gradeps[1].innerHTML = '提示：输入错误';
            gradeps[1].style.color = 'red';
        }
    })
    grades[2].addEventListener('focus',function() {
        gradeps[2].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[2].style.color = 'black';
    })
    grades[2].addEventListener('keyup',function() {
        var reg = /^(\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[2].value)) {
            gradeps[2].innerHTML = '提示：输入正确';
            gradeps[2].style.color = 'green';
        } else {
            gradeps[2].innerHTML = '提示：输入错误';
            gradeps[2].style.color = 'red';
        }
    })
    grades[3].addEventListener('focus',function() {
        gradeps[3].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[3].style.color = 'black';
    })
    grades[3].addEventListener('keyup',function() {
        var reg = /^(\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[3].value)) {
            gradeps[3].innerHTML = '提示：输入正确';
            gradeps[3].style.color = 'green';
        } else {
            gradeps[3].innerHTML = '提示：输入错误';
            gradeps[3].style.color = 'red';
        }
    })
    
    // 学生成绩维护
    var grades = document.querySelectorAll('.grade');
    var gradeps = document.querySelectorAll('.gradep');
    s_ids[6].addEventListener('focus',function() {
        s_idps[6].innerHTML = '提示：请输入3位数字的学号，否则输入无效';
        s_idps[6].style.color = 'black';
    })
    s_ids[6].addEventListener('keyup',function() {
        var reg = /^\d{3}$/;
        if(reg.test(s_ids[6].value)) {
            s_idps[6].innerHTML = '提示：输入正确';
            s_idps[6].style.color = 'green';
        } else {
            s_idps[6].innerHTML = '提示：输入错误';
            s_idps[6].style.color = 'red';

        }
    })
    grades[4].addEventListener('focus',function() {
    gradeps[4].innerHTML = '提示：请输入有效的成绩，否则输入无效';
    gradeps[4].style.color = 'black';
    })
    grades[4].addEventListener('keyup',function() {
        var reg = /^(-1|\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[4].value)) {
            gradeps[4].innerHTML = '提示：输入正确';
            gradeps[4].style.color = 'green';
        } else {
            gradeps[4].innerHTML = '提示：输入错误';
            gradeps[4].style.color = 'red';
        }
    })
    
    grades[5].addEventListener('focus',function() {
        gradeps[5].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[5].style.color = 'black';
    })
    grades[5].addEventListener('keyup',function() {
        var reg = /^(-1|\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[5].value)) {
            gradeps[5].innerHTML = '提示：输入正确';
            gradeps[5].style.color = 'green';
        } else {
            gradeps[5].innerHTML = '提示：输入错误';
            gradeps[5].style.color = 'red';
        }
    })
    grades[6].addEventListener('focus',function() {
        gradeps[6].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[6].style.color = 'black';
    })
    grades[6].addEventListener('keyup',function() {
        var reg = /^(-1|\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[6].value)) {
            gradeps[6].innerHTML = '提示：输入正确';
            gradeps[6].style.color = 'green';
        } else {
            gradeps[6].innerHTML = '提示：输入错误';
            gradeps[6].style.color = 'red';
        }
    })
    grades[7].addEventListener('focus',function() {
        gradeps[7].innerHTML = '提示：请输入有效的成绩，否则输入无效';
        gradeps[7].style.color = 'black';
    })
    grades[7].addEventListener('keyup',function() {
        var reg = /^(-1|\d{1}|[1-9]\d{1}|100)$/;
        if(reg.test(grades[7].value)) {
            gradeps[7].innerHTML = '提示：输入正确';
            gradeps[7].style.color = 'green';
        } else {
            gradeps[7].innerHTML = '提示：输入错误';
            gradeps[7].style.color = 'red';
        }
    })
})