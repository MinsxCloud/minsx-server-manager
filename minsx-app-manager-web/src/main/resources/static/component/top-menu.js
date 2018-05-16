Vue.component('top-menu', {
    template: '<div id="topNav">' +
    '             <el-menu' +
    '                   :default-active="activeIndex"' +
    '                   class="el-menu-demo"' +
    '                   mode="horizontal"' +
    '                   @select="handleSelect"' +
    '                   background-color="#3C3F41"' +
    '                   text-color="#fff"' +
    '                   active-text-color="#ffd04b">' +
    '               <el-menu-item index="1"><i class="el-icon-menu"></i>应用概览</el-menu-item>' +
    '               <el-menu-item index="2"><i class="el-icon-date"></i>应用日志</el-menu-item>' +
    '               <el-menu-item index="3"><i class="el-icon-setting"></i>系统设置</el-menu-item>' +
    '               <el-menu-item index="4"><i class="el-icon-warning"></i>异常统计</el-menu-item>' +
    '             </el-menu>' +
    '          </div>',
    props: {
        activeIndex: {
            type: String,
            default: '1'
        }
    },
    methods: {
        handleSelect(key, keyPath) {
            console.log(key, keyPath);
        }
    }
});