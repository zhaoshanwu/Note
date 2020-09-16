<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>
    <link href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <title>Document</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/gather-css/gather.css">
    <script src="${pageContext.request.contextPath}/gather-js/gather.js"></script>
</head>
<body>
    <div class="container box">
        <div class="row">
            <div class="function col-md-2">
                <ul class="nav nav-pills nav-tabs option">
                    <li class="label nav-item active"><a href="#tab1" data-toggle="tab">全体学生信息</a></li>
                    <li class="label nav-item"><a href="#tab2" data-toggle="tab">指定学生信息</a></li>
                    <li class="label nav-item"><a href="#tab3" data-toggle="tab">学生成绩信息</a></li>
                    <li class="label nav-item"><a href="#tab4" data-toggle="tab">课程成绩信息</a></li>
                    <li class="label nav-item"><a href="#tab5" data-toggle="tab">成绩排名信息</a></li>
                    <li class="label nav-item"><a href="#tab6" data-toggle="tab">成绩差值信息</a></li>
                    <li class="label nav-item"><a href="#tab7" data-toggle="tab">学生信息录入</a></li>
                    <li class="label nav-item"><a href="#tab8" data-toggle="tab">学生成绩录入</a></li>
                    <li class="label nav-item"><a href="#tab9" data-toggle="tab">学生成绩维护</a></li>
                </ul>
            </div>
            <div class="content tab-content  col-md-10">
                <div id="tab1" class="page tab-pane active">
                    <div class="operation">
                            <div class="theme col-md-3">
                                查询全体学生信息
                            </div>
                            <div class="select col-md-5">
                                <form id="form0" action="student/findAll">
                                	<input type="hidden" value="0" name="active">
                                	<button type="submit" class="btn btn-default">查询</button>
                                </form>
                            </div>
                    </div>
                    <div class="show">
                        <table class="table table-bordered box">
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>年龄</th>
                                <th>操作</th>
                            </tr>
                            <c:forEach var="student" items="${students }">
                                <tr>
                                    <td>${student.s_id }</td>
                                    <td>${student.s_name }</td>
                                    <td>${student.s_sex }</td>
                                    <td>${student.s_age }</td>
                                    <td><a href="${pageContext.request.contextPath}/student/delete/${student.s_id }">删除</a>
                                </tr>
                            </c:forEach>
                        </table>
                    </div>
                </div>
                <div id="tab2" class="page tab-pane">
                    <div class="operation">
                        <div class="theme col-md-3">
                            查询指定学生信息
                        </div>
                        <div class="select col-md-9">
                            <form id="form1" action="student/findById" class="form-inline" onsubmit="return s_idsubmit(0);">
                                <div class="form-group">
                                    <label>学号:</label>
                                    <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <input type="hidden" value="1" name="active">
                                <button type="submit" class="btn btn-default">查询</button>
                                <p class="s_idp"></p>
                            </form>
                        </div>
                    </div>
                    <div class="show">
                        <table class="table table-bordered box">
                            <tr>
                                <th>学号</th>
                                <th>姓名</th>
                                <th>性别</th>
                                <th>年龄</th>
                            </tr>
                            <tr>
                                <td>${student.s_id }</td>
                                <td>${student.s_name }</td>
                                <td>${student.s_sex }</td>
                                <td>${student.s_age }</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="tab3" class="page tab-pane">
                    <div class="operation">
                        <div class="theme col-md-3">
                            查询学生成绩
                        </div>
                        <div class="select col-md-9">
                            <form id="form2" action="sc/findGrade" class="form-inline" onsubmit="return s_idsubmit(1);">
                                <div class="form-group">
                                    <label>学号:</label>
                                    <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <input type="hidden" value="2" name="active">
                                <button type="submit" class="btn btn-default">查询</button>
                                <p class="s_idp"></p>
                            </form>
                        </div>
                    </div>
                    <div class="show">
                        <table class="table table-bordered box">
                            <tr>
                            	<th>姓名</th>
                                <th>课程号</th>
                                <th>课程名</th>
                                <th>成绩</th>
                            </tr>
                            <tr>
                            	<td>${s_name }</td>
                                <td>${c_id[0] }</td>
                                <td>${c_name[0] }</td>
                                <td>${grade[0] }</td>
                            </tr>
                            <tr>
                            	<td>${s_name }</td>
                                <td>${c_id[1] }</td>
                                <td>${c_name[1] }</td>
                                <td>${grade[1] }</td>
                            </tr>
                            <tr>
                            	<td>${s_name }</td>
                                <td>${c_id[2] }</td>
                                <td>${c_name[2] }</td>
                                <td>${grade[2] }</td>
                            </tr>
                            <tr>
                            	<td>${s_name }</td>
                                <td>${c_id[3] }</td>
                                <td>${c_name[3] }</td>
                                <td>${grade[3] }</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="tab4" class="page tab-pane">
                    <div class="operation">
                        <div class="theme col-md-3">
                            查询课程成绩
                        </div>
                        <div class="select col-md-9">
                            <form id="form3" action="sc/MaxMin" class="form-inline" onsubmit="return c_idsubmit(0);">
                                <div class="form-group">
                                    <label>课程号:</label>
                                    <input type="text" class="form-control c_id" placeholder="课程号" name="c_id">
                                </div>
                                <input type="hidden" value="3" name="active">
                                <button type="submit" class="btn btn-default">查询</button>
                                <p class="c_idp"></p>
                            </form>
                        </div>
                    </div>
                    <div class="show">
                        <table class="table table-bordered box">
                            <tr>
                                <th>课程</th>
                                <th>最高分</th>
                                <th>最低分</th>
                                <th>平均分</th>
                            </tr>
                            <tr>
                                <td>${c_name2 }</td>
                                <td>${max }</td>
                                <td>${min }</td>
                                <td>${average }</td>
                            </tr>
                        </table>
                    </div>
                </div>
                <div id="tab5" class="page tab-pane">
                    <div class="operation">
                        <div class="theme col-md-3">
                            	查询学生最好最差排名
                        </div>
                        <div class="select col-md-9">
                            <form id="form4" action="sc/Ranking" class="form-inline" onsubmit="return s_idsubmit(2);">
                                <div class="form-group">
                                    <label>学号:</label>
                                    <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <input type="hidden" value="4" name="active">
                                <button type="submit" class="btn btn-default">查询</button>
                                <p class="s_idp"></p>
                            </form>
                        </div>
                    </div>
                    <div class="show">
                        <p>该学生的最好成绩排名为：${c_max } : ${max2 } </p>
                        <p>最差成绩排名为：${c_min } : ${min2 }</p>
                    </div>
                </div>
                <div id="tab6" class="page tab-pane">
                    <div class="operation">
                        <div class="theme">
                            <p>查询指定学生指定课程的成绩与平均分的差值</p>
                        </div>
                        <div class="select">
                            <form id="form5" action="sc/Minus" class="form-inline" onsubmit="return sc_idsubmit(3,1);">
                                <div class="form-group">
                                  <label>学 &nbsp; &nbsp;号</label>
                                    <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <span class="s_idp"></span>
                                <br/>
                                <div class="form-group">
                                    <label>课程号</label>
                                    <input type="text" class="form-control c_id" placeholder="课程号" name="c_id">
                                </div>
                                <span class="c_idp"></span>
                                <br/>
                                <input type="hidden" value="5" name="active">
                                <div class="form-group">
                                  <div class="col-sm-offset-2">
                                    <button type="submit" class="btn btn-default">查询</button>
                                  </div>
                                </div>
                              </form>
                        </div>
                    </div>
                    <div class="show">
                        <p>该学生的成绩与平均分的差值为：${minus }</p>
                    </div>
                </div>
                <div id="tab7" class="page tab-pane">
                    <div class="operation">
                        <p>学生信息录入</p>
                    </div>
                    <div class="show">
                    <!--  onsubmit="return studententering(4);" -->
                        <form id="form6" action="student/InsertStudent" class="form-horizontal">
                            <div class="form-group">
                              <label class="col-sm-2 control-label">学号</label>
                              <div class="col-sm-5">
                                <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                              </div>
                              <span class="col-sm-5 s_idp"></span>
                            </div>
                            <div class="form-group">
                              <label class="col-sm-2 control-label">姓名</label>
                              <div class="col-sm-5">
                                <input type="text" class="form-control s_name" placeholder="姓名" name="s_name">
                              </div>
                              <span class="col-sm-5 s_namep"></span>
                            </div>
                            <div class="form-group">
                              <label class="col-sm-2 control-label">性别</label>
                              <div class="radio col-sm-1">
                                <label>
                                  <input type="radio" name="s_sex" value="男" checked>男
                                </label>
                              </div>
                              <div class="radio col-sm-1">
                                <label>
                                  <input type="radio" name="s_sex" value="女">女
                                </label>
                              </div>
                            </div>
                            <div class="form-group">
                                <label class="col-sm-2 control-label">年龄</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control s_age" placeholder="年龄" name="s_age">
                                </div>
                                <span class="col-sm-5 s_agep"></span>
                            </div>
                            <input type="hidden" value="6" name="active">
                            <div class="form-group">
                              <div class="col-sm-offset-2 col-sm-10">
                                <button type="submit" class="btn btn-default">添加</button>
                              </div>
                            </div>
                          </form>
                    </div>
                </div>
                <div id="tab8" class="page tab-pane">
                    <div class="operation">
                        <p>学生成绩录入</p>
                    </div>
                    <div class="show">
                    <!--  onsubmit="return gradeentering(5);" -->
                        <form id="form8" action="sc/InsertGrade" class="form-horizontal">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学号</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <span class="col-sm-5 s_idp"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">java：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="java成绩" name="java">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">c++：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="c++成绩" name="c2">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">c语言：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="c语言成绩" name="c">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">python：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="python成绩" name="python">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <input type="hidden" value="7" name="active">
                              
                              <input type="hidden" value="王五" name="s_name">
                              <input type="hidden" value="男" name="s_sex">
                              <input type="hidden" value="20" name="s_age">
                              
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-default">录入</button>
                                </div>
                              </div>
                        </form>
                    </div>
                </div>
                <div id="tab9" class="page tab-pane">
                    <div class="operation">
                        <p>学生成绩维护</p>
                    </div>
                    <div class="show">
                        <form id="form9" action="sc/UpdateGrade" class="form-horizontal" onsubmit="return updategradeentering(6);">
                            <div class="form-group">
                                <label class="col-sm-2 control-label">学号</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control s_id" placeholder="学号" name="s_id">
                                </div>
                                <span class="col-sm-5 s_idp"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">java：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="java成绩" name="java" value="-1">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">c++：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="c++成绩" name="c2" value="-1">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">c语言：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="c语言成绩" name="c" value="-1">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <div class="form-group">
                                <label class="col-sm-2 control-label">python：</label>
                                <div class="col-sm-5">
                                  <input type="text" class="form-control grade" placeholder="python成绩" name="python" value="-1">
                                </div>
                                <span class="col-sm-5 gradep"></span>
                              </div>
                              <input type="hidden" value="8" name="active">
                              <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                  <button type="submit" class="btn btn-default">更改</button>
                                </div>
                              </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
        function s_idsubmit(s) {
            var s_ids = document.querySelectorAll('.s_id');
            var reg = /^\d{3}$/;
            if(reg.test(s_ids[s].value)) {
                return true;
            } else {
                return false;
            }
        }

        function c_idsubmit(c) {
            var c_ids = document.querySelectorAll('.c_id');
            var reg = /^\d{3}$/;
            if(reg.test(c_ids[c].value)) {
                return true;
            } else {
                return false;
            }
        }

        function sc_idsubmit(s,c) {
            var s_ids = document.querySelectorAll('.s_id');
            var c_ids = document.querySelectorAll('.c_id');
            var reg = /^\d{3}$/;
            if(reg.test(s_ids[s].value) && reg.test(c.ids[c].value)) {
                return true;
            } else {
                return false;
            }
        }

        function studententering(s) {
            var s_ids = document.querySelectorAll('.s_id');
            var reg1 = /^\d{3}$/;
            var reg2 = /^[\u4e00-\u9fa5]{2,6}$/;
            var reg3 = /^\d{1,2}$/;
            if(reg1.test(s_ids[s].value) && reg2.test(s_name.value) && reg3.test(s_age.value)) {
                return true;
            } else {
                return false;
            }
        }

        function gradeentering(s) {
            var s_ids = document.querySelectorAll('.s_id');
            var grades = document.querySelectorAll('.grade');
            var reg1 = /^\d{3}$/;
            var reg2 = /^(0|\d{1,2}|100)$/;
            if(reg1.test(s_ids[s].value) && reg2.test(grades[0].value) && reg2.test(grades[1].value) && reg2.test(grades[2].value) && reg2.test(grades[3].value)) {
                return true;
            } else {
                return false;
            }
        }
        function updategradeentering(s) {
            var s_ids = document.querySelectorAll('.s_id');
            var grades = document.querySelectorAll('.grade');
            var reg1 = /^\d{3}$/;
            var reg2 = /^(-1|0|\d{1,2}|100)$/;
            if(reg1.test(s_ids[s].value) && reg2.test(grades[4].value) && reg2.test(grades[5].value) && reg2.test(grades[6].value) && reg2.test(grades[7].value)) {
                return true;
            } else {
                return false;
            }
        }
     // 判断应该转向哪个标签页
     <%--${ active}--%>
    	var active = <%=session.getAttribute("active")%>;
        console.log(active);
    	if(active != null) {

         	var labels = document.querySelectorAll('.label');
         	var pages = document.querySelectorAll('.page');
         	// 清除所有的active类名
         	for(var i=0;i < labels.length;i++) {
         	    labels[i].classList.remove("active");
         	}
         	for(var i=0;i < pages.length;i++) {
         	    pages[i].classList.remove('active');
        	}
       	    // 给指定标签和标签页添加active类名
         	labels[active].classList.add('active');
         	pages[active].classList.add('active');

    		// var ul = document.querySelector('.function');
    	    // var as = ul.querySelectorAll('a');
    	    // console.log(as);
    	    // console.log(active);
    	    // as[active][0].click();
    	}
    </script>
</body>
</html>