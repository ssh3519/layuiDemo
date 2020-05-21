layui.use(['jquery','table','form','layer'],function () {
    var $ = layui.jquery;
    var form = layui.form;
    var layer = layui.layer;
    var table = layui.table;

    //第一个实例
    var tableIns = table.render({
        elem: '#userTable'
        , url: 'user/list' //数据接口
        , title: '用户数据表'

        , toolbar: '#userToolBar'    //开启头部工具栏，并为其绑定左侧模板
        , defaultToolbar: ['filter', 'exports', 'print']     //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可

        , cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
        , totalRow: true     //是否开启合计行区域
        , loading: true      //是否显示加载效果
        , text: {none: '暂无数据'}       //自定义文本，如空数据时的异常提示等

        , request: {
            pageName: 'pageNum', //页码的参数名称，默认：pageNum
            limitName: 'pageSize' //每页数据量的参数名，默认：pageSize
        }
        , page: true //开启分页
        , limit: 5   //默认十条数据一页
        , limits: [5, 10, 20, 40]  //数据分页条

        , response: {
            statusName: 'status', //数据状态的字段名称，默认：code
            statusCode: 200, //成功的状态码，默认：0
            countName: 'total', //数据总数的字段名称，默认：count
            dataName: 'data' //数据列表的字段名称，默认：data
        }

        , cols: [[
            {type: 'checkbox', fixed: 'left'}
            , {field: 'id', title: 'ID', width: 80, sort: true, fixed: 'left'}
            , {field: 'username', title: '用户名', width: 80}
            , {field: 'age', title: '年龄', width: 80, sort: true}
            , {
                field: 'gender', title: '性别', width: 80,
                templet: function (data) {
                    if (data.gender == 1) {
                        return "<span>男</span>"
                    } else if (data.gender == 2) {
                        return "<span>女</span>"
                    }
                }
            }
            , {field: 'email', title: '邮箱', width: 177}
            , {field: 'right', title: '操作', toolbar: '#userBar', width: 180}
        ]]
    });
    //监听头部工具栏事件
    table.on('toolbar(userTable)', function (obj) {
        // var checkStatus = table.checkStatus(obj.config.id);
        switch (obj.event) {
            case 'add':
                // layer.msg('添加');
                openUserForm();
                break;
            case 'batchDelete':
                layer.msg('批量删除');

                var checkStatus = table.checkStatus('userTable'); //userTable 即为基础参数 id 对应的值

                console.log(checkStatus.data); //获取选中行的数据
                // console.log(checkStatus.data.length); //获取选中行数量，可作为是否有选中行的条件
                // console.log(checkStatus.isAll ); //表格是否全选
                batchDelete(checkStatus.data);
                break;
        }
    });

    //监听行工具条事件
    table.on('tool(userTable)', function (obj) { //注：tool 是工具条事件名，userTable 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

        if (layEvent === 'detail') { //查看
            getUser(obj.data)
        } else if (layEvent === 'del') { //删除
            del(data.id)
        } else if (layEvent === 'edit') { //编辑
            //do something
            update(obj.data)
        }
    });

    //添加用户显示表单
    function openUserForm() {
        layer.open(
            {
                type: 1,
                title: "添加用户",
                content: $("#addForm"),
                offset: 'auto', //居中
                area: ['600px', '400px'],    //宽高
                shade: [0.8, '#393D49'],
                /*success: function(layero, index){
                    console.log(layero, index);
                }*/
                /*success: function (index) {
                    //清空列表
                    $("#addForm")[0].reset();
                    form.render();
                }*/
            }
        )
    }

    //添加用户表单提交
    form.on('submit(addUser)', function (data) {
        console.log(data.elem); //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form);//被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
        //使用ajax提交
        $.ajax({
            type: "post",
            url: "/user/add",
            data: data.field, //请求的附加参数，用json对象
            success: function (res) {
                if (res.status == 200) {
                    table.reload('userTable');//重新加载表格
                    layer.alert(res.msg, function () {
                        layer.closeAll();//关闭弹出层
                    });
                } else {
                    layer.alert(res.msg);
                }
            }
        })
    });

    //扩展自定义验证规则
    form.verify({
        username: function (value, item) {   ////value：表单的值、item：表单的DOM对象
            var regName = /^[a-zA-Z0-9_-]{3,16}$/;
            if (!regName.test(value)) {
                return '用户名不合法';
            }
        },
        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        password: [
            /^[\S]{6,12}$/,
            '密码必须6到12位，且不能出现空格'
        ]
    });


    //删除单个用户
    function del(id) {
        layer.confirm('您确定要删除吗？', {
            btn: ['确认', '返回']
        }, function (index) {
            $.ajax(
                {
                    type: "delete",
                    url: "/user/delete/" + id,
                    // contentType: "application/json",//设置请求参数类型为json字符串,@RequestBody
                    success: function (res) {
                        // alert(res);
                        table.reload('userTable');//重新加载表格
                        if (res.status == 200) {
                            layer.alert(res.msg, function () {
                                layer.closeAll();//关闭弹出层
                            });
                        } else {
                            layer.alert(res.msg);
                        }
                    }
                }
            );
        }, function () {
            layer.closeAll();
        });
    }

    //批量删除
    function batchDelete(data){
        var str = "";
        var strName = "";
        for(var j = 0,len = data.length; j < len; j++){
            str += data[j].id+","
            strName += data[j].username+","
        }
        console.log(str.substr(0,str.length-1));
        idsStr = str.substr(0,str.length-1)
        namesStr = strName.substr(0,strName.length-1)
        layer.confirm('您确定要删除['+namesStr+']吗？', {
            btn: ['确认', '返回']
        }, function (index) {
            $.ajax(
                {
                    type: "delete",
                    url: "/user/delete?idsStr="+idsStr,
                    // contentType: "application/json",//设置请求参数类型为json字符串,@RequestBody
                    success: function (res) {
                        // alert(res);
                        table.reload('userTable');//重新加载表格
                        if (res.status == 200) {
                            layer.alert(res.msg, function () {
                                layer.closeAll();//关闭弹出层
                            });
                        } else {
                            layer.alert(res.msg);
                        }
                    }
                }
            );
        }, function () {
            layer.closeAll();
        });
    }

    //更新用户
    function update(user) {
        layer.open(
            {
                type: 1,
                title: "修改用户",
                content: $("#editForm"),
                offset: 'auto', //居中
                area: ['600px', '400px'],    //宽高
                shade: [0.8, '#393D49'],
                success: function () {
                    // console.log(layero, index);
                    // console.log(user)
                    //初始化表单
                    form.val("editUserFrm", user)
                }
            }
        )
    }

    /*$("#editReset").click(function () {
        var options = { jsonValue: dd, isDebug: false };
        $("#editForm").initForm(options);

    });*/
    //修改用户表单提交
    form.on('submit(editUser)', function (data) {
        console.log(data.elem); //被执行事件的元素DOM对象，一般为button对象
        console.log(data.form);//被执行提交的form对象，一般在存在form标签时才会返回
        console.log(data.field); //当前容器的全部表单字段，名值对形式：{name: value}
        //使用ajax提交
        $.ajax({
            type: "put",
            url: "/user/update/",
            data: data.field, //请求的附加参数，用json对象
            success: function (res) {
                table.reload('userTable');//重新加载表格
                if (res.status == 200) {
                    layer.alert(res.msg, function () {
                        layer.closeAll();//关闭弹出层
                    });
                } else {
                    layer.alert(res.msg);
                }
            }
        })
    });

    function getUser(user){
        console.log(user);
        layer.open(
            {
                type: 1,
                title: "查看用户",
                content: $("#getForm"),
                offset: 'auto', //居中
                area: ['600px', '400px'],    //宽高
                shade: [0.8, '#393D49'],
                success: function () {
                    // console.log(layero, index);
                    // console.log(user)
                    //初始化表单
                    form.val("getUserFrm", user)
                }
            }
        )
    }
})